package africa.jopen.landlord.network;

import africa.jopen.landlord.configs.ConstantReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
    private final ObjectMapper objectMapper;

    public JanusAdminHttpClient(String baseUrl, String adminSecret) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        this.adminSecret = adminSecret;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(60))
                .build();
        this.objectMapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    // Synchronous POST request with raw JSON
    public String sendPostRequest(String endpoint, String jsonPayload) throws IOException, InterruptedException {
        HttpRequest request = buildPostRequest(endpoint, jsonPayload);
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new IOException("HTTP error: " + response.statusCode() + " - " + response.body());
        }
    }



    // Synchronous POST request with record payload
    public String sendPostRequest(String endpoint, Object payload) throws IOException, InterruptedException {
        String jsonPayload = objectMapper.writeValueAsString(payload);
        return sendPostRequest(endpoint, jsonPayload);
    }



    // Deserialize response to a specific type
    public <T> T deserializeResponse(String responseBody, Class<T> responseType) throws IOException {
        return objectMapper.readValue(responseBody, responseType);
    }

    private String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

    private HttpRequest buildPostRequest(String endpoint, String jsonPayload) {
        String fullUrl = baseUrl + (endpoint.startsWith("/") ? endpoint.substring(1) : endpoint);
        return HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", basicAuth(
                        ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME,
                        ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD
                ))
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();
    }


}