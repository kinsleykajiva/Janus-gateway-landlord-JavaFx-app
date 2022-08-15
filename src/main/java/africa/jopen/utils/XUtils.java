package africa.jopen.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import africa.jopen.application.BaseApplication;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.properties.EncryptableProperties;
import org.jetbrains.annotations.NotNull;

public class XUtils {
   static Logger logger = Logger.getLogger( XUtils.class.getName() );;
    public static String APP_FOLDER = ".janus-landlord-jfx";
    public static String ROOT_FOLDER = "";

    private static final String PASS_WORD_PROPS = "pB%@kOH@6130wED$y^ob7UUl@&k518J5r0pB%@kOH@6130wED$y^ob7UUl@&k518J5r0Yk7Vme0qjwas8%0WYh06I%@JLv34nuZ2^Yk7Vme0qjwas8%0WYh06I%@JLv34nuZ2^";
    public static final String SYSTEM_APP_TITLE = "Janus-Gateway-LandLord App";


    public static final Map<String, String> NAVIGATION = Map.of(
            "Home", "/views/home/home.fxml",
            "Main", "/views/main.fxml",
            "Sessions", "/views/janus/sessions.fxml",
            "JanusConfig", "/views/api/janus-api.fxml",
            "Settings", "/views/settings/settings.fxml"
    );

    public static String getLocalCache(String module, String key) {

        if (!isFilesHomeKeepingGood()) {
            logError("Failed to set cache");

        }
        var path = ROOT_FOLDER + "/" + module + "-configuration.properties";
        StandardPBEStringEncryptor encryptor = getStandardPBEStringEncryptor(module);
        Properties props = new EncryptableProperties(encryptor);
        try {
            props.load( new FileInputStream(path) );
            return props.getProperty(key);
        } catch (IOException e) {
           e.printStackTrace();
        }
        return null;
    }
    public static boolean isNumeric(final String input) {
        //Check for null or blank string
        if (input == null || input.isBlank()) return false;

        //Retrieve the minus sign and decimal separator characters from the current Locale
        final var localeMinusSign = DecimalFormatSymbols.getInstance().getMinusSign();
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

    public static boolean isURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String testIfToQoute(String value) {
        return value.equals("true") || value.equals("false") || XUtils.isNumeric(value) ?
                (value) :
                "".concat("\"").concat(value).concat("\"");
    }
    public static void saveLocalCache(String module, String key, String value) {
        if (!isFilesHomeKeepingGood()) {
            logError("Failed to set cache");
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
    private static StandardPBEStringEncryptor getStandardPBEStringEncryptor(String module) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(PASS_WORD_PROPS + module);
        encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        encryptor.setIvGenerator(new RandomIvGenerator());
        return encryptor;
    }

    private static boolean isFilesHomeKeepingGood() {
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


    public static void logInfo(String msg) {
        logger.info( nowTimestmap()+": " +msg);
    }


    public static void logError(String msg) {
        logger.severe("%s: %s".formatted(nowTimestmap(), msg));

    }

    public static URL loadURL(String path) {
        return XUtils.class.getResource(path);
    }

    public static String nowTimestmap(){
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    }

}
