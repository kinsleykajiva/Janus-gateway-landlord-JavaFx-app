package africa.jopen.landlord.configs;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.properties.EncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static africa.jopen.landlord.configs.ConstantReference.*;
import static africa.jopen.landlord.configs.ConstantReference.CONFIG_FILE_SUFFIX;
import static africa.jopen.landlord.utils.XUtils.isURL;

public class CacheUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheUtil.class);

    public static final Map<String, StandardPBEStringEncryptor> ENCRYPTOR_CACHE = new ConcurrentHashMap<>();

    private static final Map<String, Lock> MODULE_LOCKS = new ConcurrentHashMap<>();


    /**
     * Parses an integer value with fallback to default.
     *
     * @param value        The value to parse.
     * @param defaultValue The default value.
     * @return The parsed integer.
     */
    private static int parseInt(String value, int defaultValue) {
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LOGGER.warn("Invalid integer value {}. Using default: {}", value, defaultValue);
            return defaultValue;
        }
    }
    /**
     * Gets or creates an encryptor for a module.
     *
     * @param module The module name.
     * @return The encryptor instance.
     */
    private static StandardPBEStringEncryptor getEncryptor(String module) {
        return ENCRYPTOR_CACHE.computeIfAbsent(module, k -> {
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(DEFAULT_ENCRYPTION_PASSWORD + module);
            encryptor.setAlgorithm(ENCRYPTION_ALGORITHM);
            encryptor.setIvGenerator(new RandomIvGenerator());
            return encryptor;
        });
    }
    /**
     * Ensures the root folder exists in a thread-safe manner.
     *
     * @return True if the folder exists or was created, false otherwise.
     */
    private static synchronized boolean ensureRootFolder() {
        if (!ROOT_FOLDER.isEmpty()) {
            return true;
        }
        File rootFolder = new File(System.getProperty("user.home"), APP_FOLDER);
        if (!rootFolder.exists() && !rootFolder.mkdirs()) {
            LOGGER.error("Failed to create root folder: {}", rootFolder.getAbsolutePath());
            return false;
        }
        ROOT_FOLDER = rootFolder.getAbsolutePath();
        return true;
    }
    /**
     * Parses a port value with fallback to default.
     *
     * @param value     The port value.
     * @param key       The configuration key for default lookup.
     * @return The parsed port.
     */
    private static int parsePort(String value, String key) {
        int defaultPort = Integer.parseInt(ConfigKeys.DEFAULTS.getOrDefault(key, "0"));
        int port = parseInt(value, defaultPort);
        if (port < 1 || port > 65535) {
            LOGGER.warn("Invalid port value {} for key {}. Using default: {}", value, key, defaultPort);
            return defaultPort;
        }
        return port;
    }
    /**
     * Loads properties from a file for a given module.
     *
     * @param module The module name.
     * @param file   The properties file.
     * @return The loaded Properties object.
     * @throws IOException If an I/O error occurs.
     */
    private static Properties loadProperties(String module, File file) throws IOException {
        StandardPBEStringEncryptor encryptor = getEncryptor(module);
        Properties props = new EncryptableProperties(encryptor);
        try (FileInputStream in = new FileInputStream(file)) {
            props.load(in);
        }
        return props;
    }

    /**
     * Retrieves a value from the local cache for a given module and key.
     * Creates the file if it doesn't exist.
     *
     * @param module The module name.
     * @param key    The configuration key.
     * @return The value, or null if not found or file cannot be accessed.
     */
    public static synchronized String getLocalCache(String module, String key) {
        if (!ensureRootFolder()) {
            LOGGER.error("Cannot access cache due to invalid root folder.");
            return null;
        }

        // Create the file if it doesn't exist
        File file = checkPropertiesFile(module, true);
        if (file == null) {
            LOGGER.error("Failed to access or create configuration file for module {}.", module);
            return null;
        }

        try {
            Properties props = loadProperties(module, file);
            return props.getProperty(key);
        } catch (IOException e) {
            LOGGER.error("Failed to read cache for module {} and key {}: {}", module, key, e.getMessage());
            return null;
        }
    }

    public static boolean isNotLoggedIn() {
        var janusUrl = getLocalCache(CONFIG_KEY_DEFAULT, "janus_url");
        return janusUrl == null || janusUrl.isEmpty();
    }

    /**
     * Checks if the properties file exists and optionally creates it.
     *
     * @param module The module name.
     * @param create Whether to create the file if it doesn't exist.
     * @return The File object, or null if it doesn't exist and create is false.
     */
    private static File checkPropertiesFile(String module, boolean create) {
        String path = ROOT_FOLDER + "/" + module + CONFIG_FILE_SUFFIX;
        File file = new File(path);
        if (file.exists()) {
            return file;
        }
        if (create) {
            try {
                if (file.createNewFile()) {
                    LOGGER.debug("Created new configuration file for module {}: {}", module, path);
                    return file;
                } else {
                    LOGGER.error("Failed to create configuration file: {}", path);
                    return null;
                }
            } catch (IOException e) {
                LOGGER.error("Error creating configuration file {}: {}", path, e.getMessage());
                return null;
            }
        }
        return null;
    }

    /**
     * Saves a value to the local cache for a given module and key.
     *
     * @param module The module name.
     * @param key    The configuration key.
     * @param value  The value to save.
     */
    public static void saveLocalCache(String module, String key, String value) {
        if (!ensureRootFolder()) {
            LOGGER.error("Cannot save cache due to invalid root folder.");
            return;
        }

        Lock lock = MODULE_LOCKS.computeIfAbsent(module, k -> new ReentrantLock());
        lock.lock();
        try {
            File file = checkPropertiesFile(module, true);
            if (file == null) {
                LOGGER.error("Failed to create configuration file for module {}.", module);
                return;
            }

            Properties props = loadProperties(module, file);
            props.setProperty(key, value == null ? "" : value);

            try (FileOutputStream out = new FileOutputStream(file)) {
                props.store(out, module + " properties");
                LOGGER.debug("Saved cache for module {}, key {}, value {}", module, key, value);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to save cache for module {} and key {}: {}", module, key, e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    static String getConfig(Properties props, String key) {
        return Optional.ofNullable(props.getProperty(key))
                .filter(s -> !s.isBlank())
                .orElse(ConfigKeys.DEFAULTS.getOrDefault(key, ""));
    }

    /**
     * Applies default configuration when loading fails and saves it to the properties file.
     */
    private static synchronized void applyDefaultConfiguration() {
        String module = CONFIG_KEY_DEFAULT;

        // Ensure we have the default config file
        if (!ensureRootFolder()) {
            LOGGER.error("Cannot apply default configuration due to invalid root folder.");
            return;
        }

        // Create the file if it doesn't exist
        File file = checkPropertiesFile(module, true);
        if (file == null) {
            LOGGER.error("Failed to create default configuration file.");
            return;
        }

        // Save all default values to the file
        for (Map.Entry<String, String> entry : ConfigKeys.DEFAULTS.entrySet()) {
            saveLocalCache(module, entry.getKey(), entry.getValue());
        }

        String janusUrl = ConfigKeys.DEFAULTS.get(ConfigKeys.JANUS_URL);
        int httpAdminPort = Integer.parseInt(ConfigKeys.DEFAULTS.get(ConfigKeys.HTTP_ADMIN_PORT));
        int landlordWebAppPort = Integer.parseInt(ConfigKeys.DEFAULTS.get(ConfigKeys.LANDLORD_WEB_APP_PORT));
        int sessionIntervals = 20;
        String username = ConfigKeys.DEFAULTS.get(ConfigKeys.USERNAME);
        String password = ConfigKeys.DEFAULTS.get(ConfigKeys.PASSWORD);
        String adminBasePath = ConfigKeys.DEFAULTS.get(ConfigKeys.ADMIN_BASE_PATH);
        int serverHttpPort = Integer.parseInt(ConfigKeys.DEFAULTS.get(ConfigKeys.SERVER_HTTP_PORT));

        ConstantReference.JANUS_SERVER_BASE_URL = janusUrl;
        ConstantReference.JANUS_SERVER_ADMIN_PORT = httpAdminPort;
        ConstantReference.LANDLORDWEBAPP_SERVER_PORT = landlordWebAppPort;
        ConstantReference.JANUS_ADMIN_SESSION_INTERVAL_DELAY = sessionIntervals;
        ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME = username;
        ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD = password;
        ConstantReference.JANUS_SERVER_ADMIN_BASE_PATH = adminBasePath;
        ConstantReference.JANUS_SERVER_HTTP_PORT = serverHttpPort;
        ConstantReference.JANUS_SERVER_URL = janusUrl.isEmpty() ? "" : janusUrl + ":" + httpAdminPort;
        ConstantReference.LANDLORDWEBAPP_SERVER_URL = janusUrl.isEmpty() ? "" : janusUrl + ":" + landlordWebAppPort;
        ConstantReference.JANUS_SERVER__HTTP_URL = janusUrl.isEmpty() ? "" : janusUrl + ":" + serverHttpPort;
    }

    public static synchronized void loadConf() {
        try {
            String module = CONFIG_KEY_DEFAULT;

            // Ensure we have the root folder
            if (!ensureRootFolder()) {
                LOGGER.error("Cannot load configuration due to invalid root folder.");
                applyDefaultConfiguration();
                return;
            }

            // Create the file if it doesn't exist
            File file = checkPropertiesFile(module, true);
            if (file == null) {
                LOGGER.error("Failed to create configuration file for module {}.", module);
                applyDefaultConfiguration();
                return;
            }

            Properties props = loadProperties(module, file);

            // If the file is empty, apply and save defaults
            if (props.isEmpty()) {
                LOGGER.info("Empty configuration file for module {}. Applying defaults.", module);
                applyDefaultConfiguration();
                return;
            }

            // Load and validate configurations
            String janusUrl = getConfig(props, ConfigKeys.JANUS_URL);
            if (!janusUrl.isEmpty() && !isURL(janusUrl)) {
                LOGGER.warn("Invalid Janus URL: {}. Falling back to default.", janusUrl);
                janusUrl = ConfigKeys.DEFAULTS.get(ConfigKeys.JANUS_URL);
                saveLocalCache(module, ConfigKeys.JANUS_URL, janusUrl);
            }

            int httpAdminPort = parsePort(getConfig(props, ConfigKeys.HTTP_ADMIN_PORT), ConfigKeys.HTTP_ADMIN_PORT);
            int landlordWebAppPort = parsePort(getConfig(props, ConfigKeys.LANDLORD_WEB_APP_PORT), ConfigKeys.LANDLORD_WEB_APP_PORT);
            int sessionIntervals = parseInt(getConfig(props, ConfigKeys.SESSION_INTERVALS), 20);
            String username = getConfig(props, ConfigKeys.USERNAME);
            String password = getConfig(props, ConfigKeys.PASSWORD);
            String adminBasePath = getConfig(props, ConfigKeys.ADMIN_BASE_PATH);
            if (adminBasePath.isEmpty()) {
                adminBasePath = ConfigKeys.DEFAULTS.get(ConfigKeys.ADMIN_BASE_PATH);
                saveLocalCache(module, ConfigKeys.ADMIN_BASE_PATH, adminBasePath);
            }
            int serverHttpPort = parsePort(getConfig(props, ConfigKeys.SERVER_HTTP_PORT), ConfigKeys.SERVER_HTTP_PORT);

            // Set configuration in ConstantReference
            synchronized (ConstantReference.class) {
                ConstantReference.JANUS_SERVER_BASE_URL = janusUrl;
                ConstantReference.JANUS_SERVER_ADMIN_PORT = httpAdminPort;
                ConstantReference.LANDLORDWEBAPP_SERVER_PORT = landlordWebAppPort;
                ConstantReference.JANUS_ADMIN_SESSION_INTERVAL_DELAY = sessionIntervals;
                ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME = username;
                ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD = password;
                ConstantReference.JANUS_SERVER_ADMIN_BASE_PATH = adminBasePath;
                ConstantReference.JANUS_SERVER_HTTP_PORT = serverHttpPort;
                ConstantReference.JANUS_SERVER_URL = janusUrl.isEmpty() ? "" : janusUrl + ":" + httpAdminPort;
                ConstantReference.LANDLORDWEBAPP_SERVER_URL = janusUrl.isEmpty() ? "" : janusUrl + ":" + landlordWebAppPort;
                ConstantReference.JANUS_SERVER__HTTP_URL = janusUrl.isEmpty() ? "" : janusUrl + ":" + serverHttpPort;
            }

            LOGGER.info("Configuration loaded successfully for module {}.", module);
        } catch (Exception e) {
            LOGGER.error("Failed to load configuration: {}", e.getMessage(), e);
            applyDefaultConfiguration();
        }
    }
}