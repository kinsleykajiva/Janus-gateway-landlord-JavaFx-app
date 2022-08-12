package africa.jopen.janus.settings;



import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.logging.Logger;

import static africa.jopen.utils.XUtils.getLocalCache;

public class SettingsReq {
    private static HttpClient client = HttpClient.newBuilder().build();
    static Logger logger = Logger.getLogger( SettingsReq.class.getName() );
    protected static String getRandomString() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        return salt.toString();

    }







    public static String adminReq(){

        HttpRequest request = HttpRequest.newBuilder(URI.create(getLocalCache("default" ,"janus_url") + "/admin"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new JSONObject()
                                .put("admin_secret",getLocalCache("default" ,"admin_secret"))
                                .put("transaction" , getRandomString())
                                .put("janus" , "get_status")
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
       // logger.info("postJanusRequest" + getLocalCache("default" ,"janus_url") + "/admin"+params);

        HttpRequest request = HttpRequest.newBuilder(URI.create(getLocalCache("default" ,"janus_url") + "/admin"+params))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new JSONObject()
                                .put("admin_secret",getLocalCache("default" ,"admin_secret"))
                                .put("transaction" , getRandomString())
                                .put("janus" , janusValue)
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
