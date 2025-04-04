package africa.jopen.landlord.network;

import africa.jopen.landlord.configs.ConstantReference;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.io.IOException;

public class JanusAdminHttpClient {
    private final HttpClient httpClient;
    private final String baseUrl;
    private final String adminSecret;


    public JanusAdminHttpClient(String baseUrl, String adminSecret) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        this.adminSecret = adminSecret;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1) // Janus typically uses HTTP/1.1
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    // Synchronous POST request
    public String sendPostRequest(String endpoint, String jsonPayload) throws IOException, InterruptedException {
        HttpRequest request = buildPostRequest(endpoint, jsonPayload);
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Handle response
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new IOException("HTTP error: " + response.statusCode() + " - " + response.body());
        }
    }

    // Asynchronous POST request
    public CompletableFuture<String> sendPostRequestAsync(String endpoint, String jsonPayload) {
        HttpRequest request = buildPostRequest(endpoint, jsonPayload);
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    if (response.statusCode() >= 200 && response.statusCode() < 300) {
                        return response.body();
                    } else {
                        throw new RuntimeException("HTTP error: " + response.statusCode() + " - " + response.body());
                    }
                });
    }

    private  String basicAuth (String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

    // Helper method to build POST request
    private HttpRequest buildPostRequest(String endpoint, String jsonPayload) {
        String fullUrl = baseUrl + (endpoint.startsWith("/") ? endpoint.substring(1) : endpoint);
        return HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                /*.header("Authorization", "Bearer " + adminSecret)*/
                .header("Authorization", basicAuth(
                        ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME,
                        ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD
                ))
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();
    }



    // Example method for a specific Janus Admin API call (e.g., add a session)


    // Example async method for a specific Janus Admin API call
    public CompletableFuture<String> addSessionAsync(String sessionData) {
        String payload = String.format("{\"janus\": \"add_session\", \"admin_secret\": \"%s\", \"data\": %s}",
                adminSecret, sessionData);
        return sendPostRequestAsync("admin", payload);
    }

    // Close client (optional, as HttpClient manages resources automatically)
    public void close() {
        // HttpClient does not require explicit closing, but can implement if needed
    }
}