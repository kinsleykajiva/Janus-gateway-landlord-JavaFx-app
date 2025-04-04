package africa.jopen.landlord.configs;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.properties.EncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

import static africa.jopen.landlord.configs.ConstantReference.*;


public class ConfigKeys {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigKeys.class);
    static final String JANUS_URL = "janus_url";
    static final String HTTP_ADMIN_PORT = "janus_http_admin_port";
    static final String LANDLORD_WEB_APP_PORT = "landlord_web_app_port";
    static final String SESSION_INTERVALS = "session_intervals";
    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    static final String ADMIN_BASE_PATH = "admin_base_path";
    static final String SERVER_HTTP_PORT = "server_http_port";



    static final Map<String, String> DEFAULTS;

    static {
        Map<String, String> defaults = new HashMap<>();
        defaults.put(JANUS_URL, "http://102.37.58.226");
        defaults.put(HTTP_ADMIN_PORT, "7188");
        defaults.put(LANDLORD_WEB_APP_PORT, "2087");
        defaults.put(SESSION_INTERVALS, "20");
        defaults.put(USERNAME, "");
        defaults.put(PASSWORD, "");
        defaults.put(ADMIN_BASE_PATH, "/admin");
        defaults.put(SERVER_HTTP_PORT, "8088");
        DEFAULTS = Collections.unmodifiableMap(defaults);
    }


}