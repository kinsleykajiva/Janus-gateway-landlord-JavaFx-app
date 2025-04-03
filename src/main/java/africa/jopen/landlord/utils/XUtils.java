package africa.jopen.landlord.utils;

import javafx.application.Platform;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static africa.jopen.landlord.configs.ConstantReference.CONFIG_KEY_DEFAULT;

public class XUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(XUtils.class);



    /**
     * Checks if a string is a valid numeric value.
     *
     * @param input The input string.
     * @return True if the input is a valid number, false otherwise.
     */
    public static boolean isNumeric(String input) {
        if (input == null || input.isBlank()) {
            return false;
        }

        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        char minusSign = symbols.getMinusSign();
        char decimalSeparator = symbols.getDecimalSeparator();

        boolean isNegative = input.charAt(0) == minusSign;
        if (isNegative && input.length() == 1) {
            return false;
        }

        boolean decimalFound = false;
        int startIndex = isNegative ? 1 : 0;

        for (int i = startIndex; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isDigit(c)) {
                if (c == decimalSeparator && !decimalFound) {
                    decimalFound = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a string is a valid URL.
     *
     * @param url The URL string.
     * @return True if the string is a valid URL, false otherwise.
     */
    public static boolean isURL(String url) {
        if (url == null || url.isBlank()) {
            return false;
        }
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Loads a resource URL from the classpath.
     *
     * @param path The resource path.
     * @return The URL, or null if not found.
     */
    public static URL loadURL(String path) {
        return XUtils.class.getResource(path);
    }
    /**
     * Splits a collection into batches of the specified size.
     *
     * @param collection The collection to split.
     * @param batchSize  The size of each batch.
     * @param <T>        The type of elements in the collection.
     * @return A list of batches.
     */
    public static <T> List<List<T>> getBatches(List<T> collection, int batchSize) {
        if (collection == null || batchSize <= 0) {
            return Collections.emptyList();
        }
        return IntStream.range(0, (collection.size() + batchSize - 1) / batchSize)
                .mapToObj(i -> collection.subList(i * batchSize, Math.min((i + 1) * batchSize, collection.size())))
                .collect(Collectors.toList());
    }

    /**
     * Runs a Runnable on the JavaFX Application Thread.
     *
     * @param runnable The Runnable to execute.
     */
    public static void invoke(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        try {
            if (Platform.isFxApplicationThread()) {
                runnable.run();
            } else {
                Platform.runLater(runnable);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to invoke runnable: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }




}
