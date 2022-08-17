package africa.jopen.application;


import africa.jopen.events.MessageEvent;

import africa.jopen.utils.Alerts;
import africa.jopen.utils.XUtils;
import com.google.gson.*;
import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import io.github.palexdev.materialfx.controls.cell.MFXNotificationCell;
import io.github.palexdev.materialfx.notifications.MFXNotificationCenterSystem;
import io.github.palexdev.materialfx.notifications.MFXNotificationSystem;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static africa.jopen.utils.ConstantReference.CONFIG_KEY_DEFAULT;
import static africa.jopen.utils.XUtils.*;


public class BaseApplication extends Application {
	Logger logger = Logger.getLogger(BaseApplication.class.getName());
	private float increment = 0;
	private float progress  = 0;



	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void init () throws Exception {

		logger.info("inti()");
		super.init();
		float total = 43; // the difference represents the views not loaded
		increment = 100f / total;
		EventBus.getDefault().register(this);

         /* load(XUtils.NAVIGATION.get("Home") );
         load(XUtils.NAVIGATION.get("Main") );*/


	}

	// UI updates must run on MainThread
	@Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
	public void onEvent (MessageEvent event) {

		System.out.println("Received event: " + event.getEventType());
		// textField.setText(event.message);
	}

	private static final String INPUT = "";

	private static final Image JSON_IMAGE = new Image("https://i.stack.imgur.com/1slrh.png");

	private static void prependString (TreeItem<Value> item, String string) {
		String val = item.getValue().text;
		item.getValue().text = (val == null ? string : string + " : " + val);
	}

	private enum Type {
		OBJECT(new Rectangle2D(45, 52, 16, 18)),
		ARRAY(new Rectangle2D(61, 88, 16, 18)),
		PROPERTY(new Rectangle2D(31, 13, 16, 18));

		private final Rectangle2D viewport;

		Type (Rectangle2D viewport) {
			this.viewport = viewport;
		}

	}

	private static final class Value {

		private       String text;
		private final Type   type;

		public Value (Type type) {
			this.type = type;
		}

		public Value (String text, Type type) {
			this.text = text;
			this.type = type;
		}

	}

	private static TreeItem<Value> createTree (JsonElement element) {
		if (element.isJsonNull()) {
			return new TreeItem<>(new Value("null", Type.PROPERTY));
		} else if (element.isJsonPrimitive()) {
			JsonPrimitive primitive = element.getAsJsonPrimitive();
			return new TreeItem<>(new Value(primitive.isString() ? '"' + primitive.getAsString() + '"' : primitive.getAsString(), Type.PROPERTY));
		} else if (element.isJsonArray()) {
			JsonArray       array = element.getAsJsonArray();
			TreeItem<Value> item  = new TreeItem<>(new Value(Type.ARRAY));
			for (int i = 0, max = array.size(); i < max; i++) {
				TreeItem<Value> child = createTree(array.get(i));
				prependString(child, Integer.toString(i));
				item.getChildren().add(child);
			}
			return item;
		} else {
			JsonObject      object = element.getAsJsonObject();
			TreeItem<Value> item   = new TreeItem<>(new Value(Type.OBJECT));
			for (Map.Entry<String, JsonElement> property : object.entrySet()) {
				TreeItem<Value> child = createTree(property.getValue());
				prependString(child, property.getKey());
				item.getChildren().add(child);
			}
			return item;
		}
	}

	@Override
	public void start (Stage stage) throws IOException {

		var admin_base_path = getLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path");
		if (admin_base_path == null || admin_base_path.isEmpty()) {
			saveLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path", "/admin");
		}
		loadConf();


		var janus_url  = getLocalCache(CONFIG_KEY_DEFAULT, "janus_url");
		var isLoggedIn = janus_url == null || janus_url.isEmpty();

		Parent root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(XUtils.NAVIGATION.get(isLoggedIn ? "Login" : "Main"))));
		Scene  scene = new Scene(root);
		Alerts.initScene(scene);
		stage.setTitle(SYSTEM_APP_TITLE);
		stage.setScene(scene);
		stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/default-192x192.png"))));
		stage.show();

		Platform.runLater(() -> {
			MFXNotificationSystem.instance().initOwner(stage);
			MFXNotificationCenterSystem.instance().initOwner(stage);

			MFXNotificationCenter center = MFXNotificationCenterSystem.instance().getCenter();
			center.setCellFactory(notification -> new MFXNotificationCell(center, notification) {
				{
					setPrefHeight(100);
				}
			});
		});


	}

	private static void extracted (Stage stage) {
		JsonElement root1 = JsonParser.parseString(INPUT);

		TreeItem<Value> treeRoot = createTree(root1);
		TreeView<Value> treeView = new TreeView<>(treeRoot);
		treeView.setCellFactory(tv -> new TreeCell<Value>() {
			private final ImageView imageView;

			{
				imageView = new ImageView(JSON_IMAGE);
				imageView.setFitHeight(18);
				imageView.setFitWidth(16);
				imageView.setPreserveRatio(true);
				setGraphic(imageView);
			}

			@Override
			protected void updateItem (Value item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null) {
					setText("");
					imageView.setVisible(false);
				} else {
					setText(item.text);
					imageView.setVisible(true);
					imageView.setViewport(item.type.viewport);
				}
			}

		});

		final Scene scene1 = new Scene(treeView);

		stage.setScene(scene1);
		stage.show();
	}

	private final BooleanProperty minDecorationsProperty = new SimpleBooleanProperty(true);

	private StageStyle getModality () {
		return minDecorationsProperty.get() ? StageStyle.UTILITY : StageStyle.DECORATED;
	}

	private void confirmationDialogButton (Scene scene, Stage stage) {
		var alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Folder Location Access");
		alert.setHeaderText("Please select the default folder selection for the App to use");
		alert.setContentText(null);
		ButtonType cancelBtn = new ButtonType("Ok,Proceed", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(cancelBtn);
		Button show = new Button("Select Folder");
		show.setOnAction(e -> {
			DirectoryChooser directoryChooser  = new DirectoryChooser();
			File             selectedDirectory = directoryChooser.showDialog(stage);

			if (selectedDirectory == null) {
				//No Directory selected
				alert.close();
				confirmationDialogButton(scene, stage);
			} else {
				System.out.println(selectedDirectory.getAbsolutePath());
				alert.close();
			}
		});
		alert.setGraphic(show);

		alert.initOwner(scene.getWindow());
		alert.initStyle(getModality());
		alert.showAndWait();
	}


	@Override
	public void stop () throws Exception {
		super.stop();
		EventBus.getDefault().unregister(this);
	}


	private synchronized void preloaderNotify () {
		progress += increment;
		// LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
	}


}
