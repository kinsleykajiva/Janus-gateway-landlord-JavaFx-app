package africa.jopen.landlord.network;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class JanusAPIUtils {


    public static String addSession(JanusAdminHttpClient httpClient, String adminSecret, String sessionData)
            throws IOException, InterruptedException {
        String payload = String.format("{\"janus\": \"add_session\", \"admin_secret\": \"%s\", \"data\": %s}",
                adminSecret, sessionData);
        return httpClient.sendPostRequest("admin", payload);
    }


    public static CompletableFuture<String> addSessionAsync(JanusAdminHttpClient httpClient, String adminSecret,
                                                            String sessionData) {
        String payload = String.format("{\"janus\": \"add_session\", \"admin_secret\": \"%s\", \"data\": %s}",
                adminSecret, sessionData);
        return httpClient.sendPostRequestAsync("admin", payload);
    }
}