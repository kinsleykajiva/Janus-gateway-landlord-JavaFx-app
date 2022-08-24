package africa.jopen.utils;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.properties.EncryptableProperties;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static africa.jopen.utils.ConstantReference.CONFIG_KEY_DEFAULT;
import static java.util.Objects.isNull;

public class XUtils {
	public static final  String SYSTEM_APP_TITLE = "Janus-Gateway-LandLord App";
	public static final Map<String, String> NAVIGATION = Map.of(
			"Home", "/views/home/home.fxml",
			"Main", "/views/main.fxml",
			"Sessions", "/views/janus/sessions.fxml",
			"JanusConfig", "/views/api/janus-api.fxml",
			"Login", "/views/auth/login.fxml",
			"Settings", "/views/settings/settings.fxml"
	);
	private static final String PASS_WORD_PROPS  = "pB%@kOH@6130wED$y^ob7UUl@&k518J5r0pB%@kOH@6130wED$y^ob7UUl@&k518J5r0Yk7Vme0qjwas8%0WYh06I%@JLv34nuZ2^Yk7Vme0qjwas8%0WYh06I%@JLv34nuZ2^";
	public static String APP_FOLDER  = ".janus-landlord-jfx";
	public static String ROOT_FOLDER = "";
	static        Logger logger      = Logger.getLogger(XUtils.class.getName());

	public static String getLocalCache (String module, String key) {

		if (!isFilesHomeKeepingGood()) {
			logError("Failed to set cache");

		}
		var                        path      = ROOT_FOLDER + "/" + module + "-configuration.properties";
		StandardPBEStringEncryptor encryptor = getStandardPBEStringEncryptor(module);
		Properties                 props     = new EncryptableProperties(encryptor);
		try {
			props.load(new FileInputStream(path));
			return props.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isNumeric (final String input) {
		//Check for null or blank string
		if (input == null || input.isBlank()) return false;

		//Retrieve the minus sign and decimal separator characters from the current Locale
		final var localeMinusSign        = DecimalFormatSymbols.getInstance().getMinusSign();
		final var localeDecimalSeparator = DecimalFormatSymbols.getInstance().getDecimalSeparator();

		//Check if first character is a minus sign
		final var isNegative = input.charAt(0) == localeMinusSign;
		//Check if string is not just a minus sign
		if (isNegative && input.length() == 1) return false;

		var isDecimalSeparatorFound = false;

		//If the string has a minus sign ignore the first character
		final var startCharIndex = isNegative ? 1 : 0;

		//Check if each character is a number or a decimal separator
		//and make sure string only has a maximum of one decimal separator
		for (var i = startCharIndex; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				if (input.charAt(i) == localeDecimalSeparator && !isDecimalSeparatorFound) {
					isDecimalSeparatorFound = true;
				} else return false;
			}
		}
		return true;
	}

	public static boolean isURL (String url) {
		try {
			new URL(url);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static synchronized void loadConf () {

		ConstantReference.JANUS_SERVER_BASE_URL = getLocalCache(CONFIG_KEY_DEFAULT, "janus_url");

		ConstantReference.JANUS_SERVER_ADMIN_PORT = Integer.parseInt(getLocalCache(CONFIG_KEY_DEFAULT, "http_admin_port") == null ? "7088" : Objects.requireNonNull(getLocalCache(CONFIG_KEY_DEFAULT, "http_admin_port")));
		ConstantReference.LANDLORDWEBAPP_SERVER_PORT = Integer.parseInt(getLocalCache(CONFIG_KEY_DEFAULT, "landlord_web_app_port") == null || getLocalCache(CONFIG_KEY_DEFAULT, "landlord_web_app_port").isEmpty() ? "2087" : getLocalCache(CONFIG_KEY_DEFAULT, "landlord_web_app_port"));
		ConstantReference.JANUS_ADMIN_SESSION_INTERVAL_DELAY = Integer.parseInt(getLocalCache(CONFIG_KEY_DEFAULT, "session_intervals") == null || getLocalCache(CONFIG_KEY_DEFAULT, "session_intervals").isEmpty() ? "20" : getLocalCache(CONFIG_KEY_DEFAULT, "session_intervals"));
		ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME = getLocalCache(CONFIG_KEY_DEFAULT, "username") == null || getLocalCache(CONFIG_KEY_DEFAULT, "username").isEmpty() ? "" : getLocalCache(CONFIG_KEY_DEFAULT, "username");
		ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD = getLocalCache(CONFIG_KEY_DEFAULT, "password") == null || getLocalCache(CONFIG_KEY_DEFAULT, "password").isEmpty() ? "" : getLocalCache(CONFIG_KEY_DEFAULT, "password");
		ConstantReference.JANUS_SERVER_ADMIN_BASE_PATH = getLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path") == null || getLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path").isEmpty() ? "/admin" : getLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path");
		ConstantReference.JANUS_SERVER_HTTP_PORT = Integer.parseInt(getLocalCache(CONFIG_KEY_DEFAULT, "server_http_port") == null || getLocalCache(CONFIG_KEY_DEFAULT, "server_http_port").isEmpty() ? "8088" : getLocalCache(CONFIG_KEY_DEFAULT, "server_http_port"));
		ConstantReference.JANUS_SERVER_URL = ConstantReference.JANUS_SERVER_BASE_URL + ":" + ConstantReference.JANUS_SERVER_ADMIN_PORT;
		ConstantReference.LANDLORDWEBAPP_SERVER_URL = ConstantReference.JANUS_SERVER_BASE_URL + ":" + ConstantReference.LANDLORDWEBAPP_SERVER_PORT;

		ConstantReference.JANUS_SERVER__HTTP_URL = ConstantReference.JANUS_SERVER_BASE_URL + ":" + ConstantReference.JANUS_SERVER_HTTP_PORT;


		var adminBasePath = getLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path");
		if (adminBasePath == null || adminBasePath.isEmpty()) {
			saveLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path", "/admin");
		}


	}

	public static String testIfToQoute (String value) {
		return value.equals("true") || value.equals("false") || XUtils.isNumeric(value) ?
				(value) :
				"".concat("\"").concat(value).concat("\"");
	}

	public static void saveLocalCache (String module, String key, String value) {
		if (!isFilesHomeKeepingGood()) {
			logger.severe("Failed to set cache");
			return;
		}
		StandardPBEStringEncryptor encryptor = getStandardPBEStringEncryptor(module);


		try {
			var path = ROOT_FOLDER + "/" + module + "-configuration.properties";
			var file = new File(path);
			if (!file.exists()) {
				file.createNewFile();

			}
			Properties props = new EncryptableProperties(encryptor);
			props.load(Files.newInputStream(Path.of(path)));
			value = value == null ? "" : value;
			props.put(key, value);

			try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
				props.store(fileOutputStream, module + " properties");
			}


		} catch (IOException e) {
			logger.severe(e.getMessage());
		}

	}

	public static <T> List<List<T>> getBatches (List<T> collection, int batchSize) {
		return IntStream.iterate(0, i -> i < collection.size(), i -> i + batchSize)
				.mapToObj(i -> collection.subList(i, Math.min(i + batchSize, collection.size())))
				.collect(Collectors.toList());
	}

	@NotNull
	private static StandardPBEStringEncryptor getStandardPBEStringEncryptor (String module) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(PASS_WORD_PROPS + module);
		encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
		encryptor.setIvGenerator(new RandomIvGenerator());
		return encryptor;
	}

	private static boolean isFilesHomeKeepingGood () {
		var rootFolder = new File(System.getProperty("user.home") + "/" + APP_FOLDER);
		if (!rootFolder.exists()) {
			if (!rootFolder.mkdirs()) {
				logError("Failed to create folder: " + APP_FOLDER);
				return false;
			}

		}
		ROOT_FOLDER = rootFolder.getAbsolutePath();
		return true;
	}

	public static void openWebpage (String url) {
		try {
			new ProcessBuilder("x-www-browser", url).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void logInfo (String msg) {
		logger.info(nowTimestmap() + ": " + msg);
	}


	public static void logError (String msg) {
		logger.severe("%s: %s".formatted(nowTimestmap(), msg));

	}

	public static URL loadURL (String path) {
		return XUtils.class.getResource(path);
	}

	public static String nowTimestmap () {
		return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	}


	/**
	 * Adds automatic scrolling to last index in a table.
	 *
	 * @param <S>  the generic type
	 * @param view the view
	 */
	public static <S> void addAutoScrollToTableView (final TableView<S> view) {
		if (view == null) {
			throw new NullPointerException();
		}

		view.getItems().addListener((ListChangeListener<S>) (c ->
		{
			c.next();
			final int size = view.getItems().size();
			if (size > 0) {
				view.scrollTo(size - 1);
			}
		}));
	}


	/**
	 * Adds the filter_ windows explorer conform.
	 *
	 * @param field the field
	 */
	public static void addFilter_WindowsExplorerConform (TextField field) {
		field.addEventFilter(KeyEvent.ANY, keyEvent ->
		{
			if (!keyEvent.getCharacter().matches("^[a-zA-Z0-9_]*$") && !keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
				keyEvent.consume();
			}
		});
	}

	/**
	 * Adds the filter_ only numbers.
	 *
	 * @param field the field
	 */
	public static void addFilter_OnlyNumbers (TextField field) {
		field.addEventFilter(KeyEvent.ANY, keyEvent ->
		{
			if (!keyEvent.getCharacter().matches("^[0-9]*$") && !keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
				keyEvent.consume();
			}
		});
	}


	/**
	 * Adds the filter_ only alphanumeric.
	 *
	 * @param field the field
	 */
	public static void addFilter_OnlyAlphanumeric (TextField field) {
		field.addEventFilter(KeyEvent.ANY, keyEvent ->
		{
			if (!keyEvent.getCharacter().matches("^[\\p{L}0-9]*$") && !keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
				keyEvent.consume();
			}
		});
	}

	/**
	 * Adds the filter_ only alphabet.
	 *
	 * @param field the field
	 */
	public static void addFilter_OnlyAlphabet (TextField field) {
		field.addEventFilter(KeyEvent.ANY, keyEvent ->
		{
			if (!keyEvent.getCharacter().matches("\\A[^\\W\\d_]+\\z") && !keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
				keyEvent.consume();
			}
		});
	}


	/**
	 * Force list refresh on.
	 *
	 * @param <T> the generic type
	 * @param lsv the lsv
	 */
	public static <T> void forceListRefreshOn (ListView<T> lsv) {
		ObservableList<T> items = lsv.getItems();
		lsv.setItems(null);
		lsv.setItems(items);
	}

	/**
	 * Force table view refresh on.
	 *
	 * @param <T> the generic type
	 * @param tbv the tbv
	 */
	public static <T> void forceTableViewRefreshOn (TableView<T> tbv) {
		ObservableList<T> items = tbv.getItems();
		tbv.setItems(null);
		tbv.setItems(items);
	}

	/**
	 * Run this Runnable in the JavaFX Application Thread. This method can be
	 * called whether or not the current thread is the JavaFX Application
	 * Thread.
	 *
	 * @param runnable The code to be executed in the JavaFX Application Thread.
	 */
	public static void invoke (Runnable runnable) {
		if (isNull(runnable)) {
			return;
		}

		try {
			if (Platform.isFxApplicationThread()) {
				runnable.run();
			} else {
				Platform.runLater(runnable);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	public static String cleanUrlToGetJustDomainOrIp (String url) {


		url = url.replace("http://", "").replace("http:// www.", "").replace("www.", "");
		url = url.replaceFirst("^(http://)?(www\\.)?", "").replaceFirst("^(https://)?(www\\.)?", "");


		try {
			URL url1 = new URL(url);
			url1 = new URL(url1.getProtocol(), url1.getHost(), url1.getFile());
			return url;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
