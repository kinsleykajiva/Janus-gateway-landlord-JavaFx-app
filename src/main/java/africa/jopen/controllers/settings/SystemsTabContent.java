package africa.jopen.controllers.settings;

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
import javafx.scene.layout.*;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.Notifications;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static africa.jopen.utils.ConstantReference.CONFIG_KEY_DEFAULT;
import static africa.jopen.utils.XUtils.getLocalCache;
import static africa.jopen.utils.XUtils.saveLocalCache;
import static atlantafx.base.theme.Styles.*;
import static atlantafx.base.theme.Styles.ELEVATED_2;

public class SystemsTabContent {
	private static final String ELEVATED_PREFIX = "elevated-";
	Logger logger       = Logger.getLogger(SystemsTabContent.class.getName());
	String jsonSettings = "";
	public  VBox              content  = new VBox(10);
	private Map<String, Node> nodesMap = new HashMap<String, Node>();

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
		var accentBtn = new Button("Save", new FontIcon(Feather.CHECK));
		accentBtn.getStyleClass().addAll(BUTTON_OUTLINED, ACCENT);
		accentBtn.setMnemonicParsing(true);
		accentBtn.setOnAction(ev -> {
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
			if (admin_base_path_text.isEmpty() || admin_base_path_text.contains("/")) {
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

			Alerts.success("Default Configs Options","Updated Configs Options");


		});


		var successBtn = new Button("Cancel");
		successBtn.getStyleClass().addAll(BUTTON_OUTLINED);
		successBtn.setMnemonicParsing(true);
		var box = new HBox(20, accentBtn, successBtn);

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
		var session_intervals = (getLocalCache(CONFIG_KEY_DEFAULT, "session_intervals"));
		var editableSpin      = new Spinner(10, 100, session_intervals == null ? 20 : Integer.parseInt(session_intervals));
		IntegerStringConverter.createFor(editableSpin);
		editableSpin.setId("session_intervals");
		editableSpin.setPrefWidth(120);
		editableSpin.setEditable(true);
		var editableBlock = new UtilSampleBlock("Sessions Interval Seconds", editableSpin);
		nodesMap.put("session_intervals", editableSpin);

		var readonlyField = new Label("App Folder: " +XUtils.ROOT_FOLDER+"/"+XUtils.APP_FOLDER);
		readonlyField.setId("folder");
		var readonlyBlock = new UtilSampleBlock("", readonlyField);
		nodesMap.put("folder", readonlyField);
		var root = new FlowPane(20, 20);
		root.getChildren().addAll(editableBlock.getRoot(), readonlyBlock.getRoot());

		return root;
	}

	private FlowPane rowJanusSettings () {


		var basicField = new TextField(getLocalCache(CONFIG_KEY_DEFAULT, "janus_url"));
		basicField.setPrefWidth(200 * 2);
		basicField.setId("janus_url");
		basicField.setPromptText("http://localhost or http://0.0.0.0");
		var basicBlock = new UtilSampleBlock("Janus Server Address", basicField);
		nodesMap.put("janus_url", basicField);

		var promptField = new TextField(getLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path"));
		promptField.setId("admin_base_path");
		promptField.setPromptText("/admin");
		var promptBlock = new UtilSampleBlock("Admin Rest API Base path (Remember this is the same as whats found under the  http transport config)", promptField);
		nodesMap.put("admin_base_path", basicField);

		var readonlyField = new TextField(getLocalCache(CONFIG_KEY_DEFAULT, "admin_secret"));
		readonlyField.setId("admin_secret");
		var readonlyBlock = new UtilSampleBlock("Admin Rest API admin_secret  (Remember this is the same as whats found under the   janus config)", readonlyField);
		nodesMap.put("admin_secret", readonlyField);

		var http_admin_port = (getLocalCache(CONFIG_KEY_DEFAULT, "http_admin_port"));
		var editableSpin    = new Spinner(1000, 9090, http_admin_port == null ? 7088 : Integer.parseInt(http_admin_port));
		IntegerStringConverter.createFor(editableSpin);
		editableSpin.setId("http_admin_port");
		editableSpin.setPrefWidth(120);
		editableSpin.setEditable(true);
		var editableBlock = new UtilSampleBlock("HTTP Admin PORT", editableSpin);
		nodesMap.put("http_admin_port", editableSpin);

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
