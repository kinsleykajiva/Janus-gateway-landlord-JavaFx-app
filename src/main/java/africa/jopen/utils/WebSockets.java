package africa.jopen.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.concurrent.CompletionStage;

public class WebSockets {


	public WebSockets () {
		String url = "18.192.45.244";
		if (url.contains("http")) {
			url = url.replace("http", "");
		}
		if (url.contains("https")) {
			url = url.replace("https", "");
		}
		url = url
				.replace(":", "")
				.replace("/", "")
				.replace("//", "")
		;
		String finalUrl = url;
		System.out.println("xxWebSockets - " + "ws://" + finalUrl + ":" + ConstantReference.LANDLORDWEBAPP_SERVER_PORT + "/ws/janus/room/four");

		var t = new Thread(() -> {
			WebSocket ws = HttpClient
					.newHttpClient()
					.newWebSocketBuilder()
					.buildAsync(URI.create("ws://" + finalUrl + ":" + ConstantReference.LANDLORDWEBAPP_SERVER_PORT + "/ws/janus/room/four"), new WebSocketClient())
					.join();

			while (true) {
			}
		});
		t.start();

	}

	public static void main (String[] args) throws Exception {
		new WebSockets();
	}

	private static final String getBasicAuthenticationHeader (String username, String password) {
		String valueToEncode = username + ":" + password;
		return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
	}

	private static class WebSocketClient implements WebSocket.Listener {
		@Override
		public void onOpen (WebSocket webSocket) {
			WebSocket.Listener.super.onOpen(webSocket);
			System.out.println("onOpen: " + webSocket);
			//ws.sendText("Hello, world!",true);
			//webSocket.sendText("j77777777777777", true);

		}

		@Override
		public CompletionStage<?> onText (WebSocket webSocket, CharSequence data, boolean last) {
			System.out.println("onText: " + webSocket);
			System.out.println("data  -----: " + data);
			System.out.println("onText received " + data);
			return WebSocket.Listener.super.onText(webSocket, data, last);
		}

		@Override
		public CompletionStage<?> onPing (WebSocket webSocket, ByteBuffer message) {
			return WebSocket.Listener.super.onPing(webSocket, message);
		}

		@Override
		public CompletionStage<?> onPong (WebSocket webSocket, ByteBuffer message) {
			return WebSocket.Listener.super.onPong(webSocket, message);
		}

		@Override
		public CompletionStage<?> onClose (WebSocket webSocket, int statusCode, String reason) {
			System.out.println("statusCode ! " + statusCode);
			return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
		}

		@Override
		public void onError (WebSocket webSocket, Throwable error) {
			System.out.println("Bad day! " + webSocket.toString());
			WebSocket.Listener.super.onError(webSocket, error);
		}


	}
}


