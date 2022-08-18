package africa.jopen.janus.plugins;

import africa.jopen.utils.ConstantReference;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.logging.Logger;

import static africa.jopen.utils.ConstantReference.LANDLORDWEBAPP_SERVER_URL;

public class LandLordWebAppReq {
	static               Logger     logger = Logger.getLogger(LandLordWebAppReq.class.getName());
	private static final HttpClient client = HttpClient.newBuilder().build();

	private LandLordWebAppReq () {
	}

	public static String getRequest (String param) {

		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.timeout(Duration.ofSeconds(30))
				.uri(URI.create(LANDLORDWEBAPP_SERVER_URL + param))
				.header("Authorization", basicAuth(
						ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME,
						ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD
						))
				.build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() != 200) {
				return null;
			}
			return response.body();
		} catch (IOException | InterruptedException e) {
			logger.severe(" Error sending request: " + e.getMessage());
			Thread.currentThread().interrupt();
		}
		return null;
	}


	public static String getGenericRequest (final String url) {
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.timeout(Duration.ofSeconds(30))
				.uri(URI.create(url))
				.build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() != 200) {
				return null;
			}
			return response.body();
		} catch (IOException | InterruptedException e) {
			logger.severe("Error sending request: " + e.getMessage());
			Thread.currentThread().interrupt();
		}
		return null;

	}


	private static String basicAuth (String username, String password) {
		return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
	}

	public static String postRequest (String params, JSONObject data) {

		HttpRequest request = HttpRequest.newBuilder(URI.create(LANDLORDWEBAPP_SERVER_URL + params))
				.header("Content-Type", "application/json")
				.header("Authorization", basicAuth(/*"no_money1"*/
						ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME,
						ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD
						/*"SkillS@Home#ArePretty!"*/))
				.POST(HttpRequest.BodyPublishers.ofString(data.toString()))
				.build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				return null;
			}
			return response.body();
		} catch (IOException e) {
			logger.severe(e.getMessage());
		} catch (InterruptedException e) {
			logger.severe(e.getMessage());
			Thread.currentThread().interrupt();
		}
		return null;
	}


}
