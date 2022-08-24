package africa.jopen.utils;

import okhttp3.*;
import okio.ByteString;

import java.util.concurrent.TimeUnit;

public final class WebSocketEcho extends WebSocketListener {
	public static void main (String... args) {
		new WebSocketEcho().run();
	}

	private void run () {
		OkHttpClient client = new OkHttpClient.Builder()
				.readTimeout(0, TimeUnit.MILLISECONDS)
				.build();

		Request request = new Request.Builder()
				.url("ws://localhost:2087" + "/ws/janus/sip/feeds")
				.build();
		client.newWebSocket(request, this);

		// Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
		//	client.dispatcher().executorService().shutdown();
		//	client.connectionPool.evictAll();
	}

	@Override
	public void onOpen (WebSocket webSocket, Response response) {
		webSocket.send("Hello...");
		webSocket.send("...World!");
		webSocket.send("...World!");
		webSocket.send("...World!");
		webSocket.send("...World!");
		webSocket.send("...World!");
		webSocket.send("...World!");
		webSocket.send("...World!");
		webSocket.send("...World!");

		//	webSocket.send(ByteString.decodeHex("deadbeef"));
		//	webSocket.close(1000, "Goodbye, World!");
	}

	@Override
	public void onMessage (WebSocket webSocket, String text) {
		System.out.println("MESSAGE: " + text);
	}

	@Override
	public void onMessage (WebSocket webSocket, ByteString bytes) {
		System.out.println("MESSAGE: " + bytes.hex());
	}

	@Override
	public void onClosing (WebSocket webSocket, int code, String reason) {
		webSocket.close(1000, null);
		System.out.println("CLOSE: " + code + " " + reason);
	}

	@Override
	public void onFailure (WebSocket webSocket, Throwable t, Response response) {
		t.printStackTrace();
	}
}

