package africa.jopen.landlord.network;

import africa.jopen.landlord.configs.ConfigKeys;
import africa.jopen.landlord.configs.ConstantReference;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

import static africa.jopen.landlord.utils.XUtils.convertHttpToWebSocket;

public class JanusAdminClient extends WebSocketClient {
    private static final Logger logger = LoggerFactory.getLogger(JanusAdminClient.class);

    public JanusAdminClient() throws URISyntaxException {

        super(
                new URI(convertHttpToWebSocket(ConstantReference.JANUS_SERVER_URL)),
                new Draft_6455(),
                Collections.singletonMap("Sec-WebSocket-Protocol", "janus-admin-protocol"),
                0
        );
        System.out.println("JanusAdminClient constructor called " + ConstantReference.JANUS_SERVER_URL);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

        logger.info("Connected to Janus server");
    }

    @Override
    public void onMessage(String s) {
        logger.info("Received message: " + s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        logger.info("Connection closed");
    }

    @Override
    public void onError(Exception e) {
      logger.error(e.getMessage());
    }
}