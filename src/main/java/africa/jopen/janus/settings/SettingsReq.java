package africa.jopen.janus.settings;


import africa.jopen.utils.ConstantReference;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.logging.Logger;

import static africa.jopen.utils.ConstantReference.CONFIG_KEY_DEFAULT;
import static africa.jopen.utils.XUtils.getLocalCache;

public class SettingsReq {
	private static final HttpClient client = HttpClient.newBuilder().build();
	static               Logger     logger = Logger.getLogger(SettingsReq.class.getName());
	private static final Random     rnd    = new Random();

	protected static String getRandomString () {
		String        saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt      = new StringBuilder();
		while (salt.length() < 12) { // length of the random string.
			int index = (int) (rnd.nextFloat() * saltChars.length());
			salt.append(saltChars.charAt(index));
		}
		return salt.toString();

	}


	public static String adminReq () {

		HttpRequest request = HttpRequest.newBuilder(URI.create(getLocalCache(CONFIG_KEY_DEFAULT, "janus_url") + ConstantReference.JANUS_SERVER_ADMIN_BASE_PATH))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(new JSONObject()
						.put("admin_secret", getLocalCache(CONFIG_KEY_DEFAULT, "admin_secret"))
						.put("transaction", getRandomString())
						.put("janus", "get_status")
						.toString()))
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (IOException e) {
			logger.severe(e.getMessage());
		} catch (InterruptedException e) {
			logger.severe(e.getMessage());
		}
		return null;
	}

	public static String postJanusRequest (String janusValue, String params) {
		//logger.info("XXXpostJanusRequest : " + ConstantReference.JANUS_SERVER_URL + ConstantReference.JANUS_SERVER_ADMIN_BASE_PATH+ params);


		HttpRequest request = HttpRequest.newBuilder(URI.create(ConstantReference.JANUS_SERVER_URL + ConstantReference.JANUS_SERVER_ADMIN_BASE_PATH + params))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(new JSONObject()
						.put("admin_secret", getLocalCache(CONFIG_KEY_DEFAULT, "admin_secret"))
						.put("transaction", getRandomString())
						.put("janus", janusValue)
						.toString()))
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			return response.body();
		} catch (IOException e) {
			logger.severe(e.getMessage());
		} catch (InterruptedException e) {
			logger.severe(e.getMessage());
		}

		return null;
	}
}
