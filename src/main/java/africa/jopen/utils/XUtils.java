package africa.jopen.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;


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
            "Settings", "views/home/home.fxml11"
    );

    public static String getLocalCache(String module, String key) {
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
            props.putIfAbsent(key, value);

            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                props.store(fileOutputStream, module + " properties");
            }


        } catch (IOException e) {
            logger.severe(e.getMessage());
        }

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
