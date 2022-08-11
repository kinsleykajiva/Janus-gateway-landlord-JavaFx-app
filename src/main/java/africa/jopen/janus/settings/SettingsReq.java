package africa.jopen.janus.settings;



import africa.jopen.application.BaseApplication;
import africa.jopen.utils.XUtils;

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
    static Logger logger = Logger.getLogger( SettingsReq.class.getName() );
    protected static String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }





    public static String adminReq(){
logger.info("xxxx=>" + getLocalCache("default" ,"admin_secret"));

        HttpRequest request = HttpRequest.newBuilder(URI.create(getLocalCache("default" ,"janus_url") + "/admin"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new JSONObject()
                                .put("admin_secret",getLocalCache("default" ,"admin_secret"))
                                .put("transaction" , getRandomString())
                                .put("janus" , "get_status")
                        .toString()))
                .build();
        HttpClient client = HttpClient.newBuilder().build();
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
