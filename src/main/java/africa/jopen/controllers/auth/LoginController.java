package africa.jopen.controllers.auth;

import africa.jopen.janus.plugins.LandLordWebAppReq;
import africa.jopen.utils.Alerts;
import africa.jopen.utils.ConstantReference;
import africa.jopen.utils.XUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import static africa.jopen.utils.ConstantReference.*;
import static africa.jopen.utils.XUtils.*;

public class LoginController implements Initializable {

	@FXML
	public TextField box_janus, username, password;
	@FXML
	public Button            login;
	@FXML
	public ProgressIndicator prgBar;

	@FXML
	public StackPane root;


	@FXML
	public Hyperlink linkWebApp, linkFxApp;

	@Override
	public void initialize (URL location, ResourceBundle resources) {
		saveLocalCache(CONFIG_KEY_DEFAULT, "username", null);
		saveLocalCache(CONFIG_KEY_DEFAULT, "password", null);

		prgBar.setVisible(false);

		linkWebApp.setOnAction(e -> {
			openWebpage("https://github.com/kinsleykajiva/Janus-gateway-landlord-Web-app");

		});

		linkFxApp.setOnAction(e -> {
			openWebpage("https://github.com/kinsleykajiva/Janus-gateway-landlord-JavaFx-app");

		});

		login.setOnAction(e -> {
			var janus_url    = box_janus.getText().trim();
			var username_txt = username.getText().trim();
			var password_txt = password.getText().trim();

			ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME = username_txt;
			ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD = password_txt;
			logError("tttttt" + ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME);
			logError("hhhhhhh" + ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD);

			if (janus_url.length() == 0) {
				Alerts.error("Input Error", "Please enter a Where Janus is Hosted");
				return;
			}

			if (!XUtils.isURL(janus_url)) {
				Alerts.error("Input Error", "Please enter valid URL");
				return;
			}

			prgBar.setVisible(true);

			final var info = ":8088/janus/info";
			LANDLORDWEBAPP_SERVER_URL = janus_url + ":" + LANDLORDWEBAPP_SERVER_PORT;

			FirstLineService service = new FirstLineService();
			service.setUrl(janus_url + info);

			service.setOnFailed(t -> {
				prgBar.setVisible(false);
				Alerts.error("Server Address Response ", "Failed to test- " + janus_url + info);
			});
			service.setOnSucceeded(t -> {
				prgBar.setVisible(false);

				/*result.put("janus_url", resultestUrl == null);
				result.put("basic_auth", resultAuth == null);*/
				if (t.getSource().getValue() == null) {
					Alerts.error("Server Address Response ", "Failed to test- " + janus_url + info);
					return;
				}
				if (t.getSource().getValue() instanceof HashMap) {

					HashMap<String, Boolean> result = (HashMap<String, Boolean>) t.getSource().getValue();


					if (!result.get("janus_url")) {
						saveLocalCache(CONFIG_KEY_DEFAULT, "janus_url", janus_url);


						if (!result.get("basic_auth")) {
							saveLocalCache(CONFIG_KEY_DEFAULT, "username", LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME);
							saveLocalCache(CONFIG_KEY_DEFAULT, "password", LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD);
						} else {
							ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME = "";
							ConstantReference.LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD = "";
							saveLocalCache(CONFIG_KEY_DEFAULT, "username", LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME);
							saveLocalCache(CONFIG_KEY_DEFAULT, "password", LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD);
						}

						Stage  stage = (Stage) root.getScene().getWindow();
						Parent root1 = null;
						try {
							root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(NAVIGATION.get("Main"))));
							Scene scene = new Scene(root1);
							stage.setScene(scene);
							stage.show();
						} catch (IOException ex) {
							throw new RuntimeException(ex);
						}
					} else {
						Alerts.error("Server Address Response ", "Failed to test- " + janus_url + info);
					}
				}
			});

			service.start();
		});


	}

	private static class FirstLineService extends Service<Map<String, Boolean>> {
		private final StringProperty url = new SimpleStringProperty();

		public final void setUrl (String value) {
			url.set(value);
		}

		public final String getUrl () {
			return url.get();
		}

		public final StringProperty urlProperty () {
			return url;
		}


		@Override
		protected Task<Map<String, Boolean>> createTask () {
			return new Task<>() {
				@Override
				protected Map<String, Boolean> call () {
					var resultestUrl = LandLordWebAppReq.getGenericRequest(getUrl());

					var resultAuth = LandLordWebAppReq.getRequest("/api/home/whats-going-on");
					logInfo("XXXRequestFocus" + resultAuth);
					HashMap<String, Boolean> result = new HashMap<>();
					result.put("janus_url", resultestUrl == null);
					result.put("basic_auth", resultAuth == null);

					return result;

				}
			};
		}

	}
}
