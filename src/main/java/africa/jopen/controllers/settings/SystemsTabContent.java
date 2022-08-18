package africa.jopen.controllers.settings;

import africa.jopen.events.MessageEvent;
import africa.jopen.utils.Alerts;
import africa.jopen.utils.ConstantReference;
import africa.jopen.utils.UtilSampleBlock;
import africa.jopen.utils.XUtils;
import atlantafx.base.controls.Spacer;
import atlantafx.base.controls.ToggleSwitch;
import atlantafx.base.util.IntegerStringConverter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.greenrobot.eventbus.EventBus;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static africa.jopen.utils.ConstantReference.CONFIG_KEY_DEFAULT;
import static africa.jopen.utils.XUtils.getLocalCache;
import static africa.jopen.utils.XUtils.saveLocalCache;
import static atlantafx.base.theme.Styles.*;

public class SystemsTabContent {
	Logger logger       = Logger.getLogger(SystemsTabContent.class.getName());
	String jsonSettings = "";
	public        VBox              content  = new VBox(10);
	private final Map<String, Node> nodesMap = new HashMap<>();

	public SystemsTabContent () {
		content.getChildren().add(rowPlayground());
		content.getChildren().add(playgroundClient());
		content.getChildren().add(spaceGenerator(11));
		content.getChildren().add(outlinedSamples().getContent());
		/*content.getChildren().add(samples());
		content.getChildren().add(new Spacer());
		content.getChildren().add(basicSamples());
		content.getChildren().add(new Spacer());

		content.getChildren().add(playground());
		content.getChildren().add(spaceGenerator(16));

		content.getChildren().add(new Spacer());*/
		content.setPadding(new Insets(20, 0, 0, 10));

	}

	private VBox spaceGenerator (final int lines) {
		var box = new VBox(10);
		for (int i = 0; i < lines; i++) {
			box.getChildren().add(new Spacer());
		}
		return box;
	}

	private UtilSampleBlock outlinedSamples () {
		var saveBtn = new Button("Save", new FontIcon(Feather.CHECK));
		saveBtn.getStyleClass().addAll(BUTTON_OUTLINED, ACCENT);
		saveBtn.setMnemonicParsing(true);
		saveBtn.setOnAction(ev -> {
			logger.info("accentBtn clicked");
			var janus_url         = (TextField) nodesMap.get("janus_url");
			var admin_base_path   = (TextField) nodesMap.get("admin_base_path");
			var admin_secret      = (TextField) nodesMap.get("admin_secret");
			var http_admin_port   = (Spinner) nodesMap.get("http_admin_port");
			var session_intervals = (Spinner) nodesMap.get("session_intervals");

			var session_intervals_text = session_intervals.getValue().toString();
			var http_admin_port_text   = http_admin_port.getValue().toString();
			var admin_secret_text      = admin_secret.getText();
			var admin_base_path_text   = admin_base_path.getText();
			var janus_url_text         = janus_url.getText();
			if (janus_url_text.isEmpty() || !XUtils.isURL(janus_url_text)) {
				Alerts.warning("Validation Error", "Please enter a valid url");
				return;
			}
			if (admin_base_path_text.isEmpty() || !admin_base_path_text.contains("/")) {
				Alerts.warning("Validation Error", "Please enter a already saved  Base path to bind to in the admin/monitor web server");
				return;
			}
			if (admin_secret_text.isEmpty()) {
				Alerts.warning("Validation Error", "Please enter already saved janus secret in the admin");
				return;
			}

			saveLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path", admin_base_path_text);
			saveLocalCache(CONFIG_KEY_DEFAULT, "janus_url", janus_url_text);
			saveLocalCache(CONFIG_KEY_DEFAULT, "admin_secret", admin_secret_text);
			saveLocalCache(CONFIG_KEY_DEFAULT, "http_admin_port", http_admin_port_text);
			saveLocalCache(CONFIG_KEY_DEFAULT, "session_intervals", session_intervals_text);

			ConstantReference.JANUS_SERVER_BASE_URL = janus_url_text;
			ConstantReference.JANUS_SERVER_ADMIN_PORT = Integer.parseInt(http_admin_port_text);

			Alerts.success("Default Configs Options", "Updated Configs Options");
			EventBus.getDefault().post(new MessageEvent(MessageEvent.MESSAGE_EVENT_UPDATE_CONFIGS));


		});


		var successBtn = new Button("Cancel");
		successBtn.getStyleClass().addAll(BUTTON_OUTLINED);
		successBtn.setMnemonicParsing(true);
		var box = new HBox(20, saveBtn, successBtn);

		return new UtilSampleBlock("", box);
	}

	private VBox row () {
		var toggle = new ToggleSwitch();
		toggle.selectedProperty().addListener((obs, old, val) -> toggle.setText(val ? "Disable" : "Enable"));
		toggle.setSelected(true);

		var box = new VBox(20, new Label("Nothing fancy here."), toggle);
		box.setAlignment(Pos.CENTER);
		return box;
	}

	private HBox basicSamples () {
		CheckBox basicCheck = new CheckBox("_Check Me");
		basicCheck.setMnemonicParsing(true);

		var basicBlock = new UtilSampleBlock("Basic", basicCheck);


		var root = new HBox(20);
		root.getChildren().addAll(
				basicBlock.getRoot()

		);

		return root;
	}

	private FlowPane rowSessionIntervalRow () {
		var sessionIntervals = (getLocalCache(CONFIG_KEY_DEFAULT, "session_intervals"));
		var spinnerSessionsIntaval      = new Spinner<Integer>(10, 100, sessionIntervals == null ? 20 : Integer.parseInt(sessionIntervals));
		IntegerStringConverter.createFor(spinnerSessionsIntaval);
		spinnerSessionsIntaval.setId("session_intervals");
		spinnerSessionsIntaval.setPrefWidth(120);
		spinnerSessionsIntaval.setEditable(true);
		var editableBlock = new UtilSampleBlock("Sessions Interval Seconds", spinnerSessionsIntaval);
		nodesMap.put("session_intervals", spinnerSessionsIntaval);

		var labelAppFolder = new Label("App Folder: " + XUtils.ROOT_FOLDER );
		labelAppFolder.setId("folder");
		var readonlyBlock = new UtilSampleBlock("", labelAppFolder);
		nodesMap.put("folder", labelAppFolder);
		var root = new FlowPane(20, 20);
		root.getChildren().addAll(editableBlock.getRoot(), readonlyBlock.getRoot());

		return root;
	}

	private FlowPane rowJanusSettings () {


		var txtFieldJanusUrl = new TextField(getLocalCache(CONFIG_KEY_DEFAULT, "janus_url"));
		txtFieldJanusUrl.setPrefWidth(200 * 2);
		txtFieldJanusUrl.setId("janus_url");
		txtFieldJanusUrl.setPromptText("http://localhost or http://0.0.0.0");
		var basicBlock = new UtilSampleBlock("Janus Server Address", txtFieldJanusUrl);
		nodesMap.put("janus_url", txtFieldJanusUrl);

		var txtfieldAdminBasePath = new TextField(getLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path"));
		txtfieldAdminBasePath.setId("admin_base_path");
		txtfieldAdminBasePath.setPromptText("/admin");
		var promptBlock = new UtilSampleBlock("Admin Rest API Base path (Remember this is the same as whats found under the  http transport config)", txtfieldAdminBasePath);
		nodesMap.put("admin_base_path", txtfieldAdminBasePath);

		var txtFieldAdminSecrete = new TextField(getLocalCache(CONFIG_KEY_DEFAULT, "admin_secret"));
		txtFieldAdminSecrete.setId("admin_secret");
		var readonlyBlock = new UtilSampleBlock("Admin Rest API admin_secret  (Remember this is the same as whats found under the   janus config)", txtFieldAdminSecrete);
		nodesMap.put("admin_secret", txtFieldAdminSecrete);

		var http_admin_port = (getLocalCache(CONFIG_KEY_DEFAULT, "http_admin_port"));
		var spinnerAdminPort    = new Spinner(1000, 9090, http_admin_port == null ? 7088 : Integer.parseInt(http_admin_port));
		IntegerStringConverter.createFor(spinnerAdminPort);
		spinnerAdminPort.setId("http_admin_port");
		spinnerAdminPort.setPrefWidth(120);
		spinnerAdminPort.setEditable(true);
		var editableBlock = new UtilSampleBlock("HTTP Admin PORT", spinnerAdminPort);
		nodesMap.put("http_admin_port", spinnerAdminPort);

		var flowPane = new FlowPane(20, 20);
		flowPane.getChildren().setAll(
				basicBlock.getRoot(),
				promptBlock.getRoot(),
				readonlyBlock.getRoot(),
				editableBlock.getRoot()
		);

		return flowPane;
	}


	private TitledPane rowPlayground () {
		var playground = new TitledPane();
		playground.setText("Janus Server");
		playground.setMnemonicParsing(true);
		playground.getStyleClass().add(ELEVATED_2);
		var node    = rowJanusSettings();
		var content = new VBox(20, node);
		VBox.setVgrow(node, Priority.ALWAYS);
		playground.setContent(content);

		return playground;
	}

	private TitledPane playgroundClient () {
		var playground = new TitledPane();
		playground.setText("Client Settings");
		playground.setMnemonicParsing(true);
		playground.getStyleClass().add(ELEVATED_2);
		var node    = rowSessionIntervalRow();
		var content = new VBox(20, node);
		VBox.setVgrow(node, Priority.ALWAYS);
		playground.setContent(content);

		return playground;
	}


}
