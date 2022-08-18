package africa.jopen.application;


import africa.jopen.events.MessageEvent;
import africa.jopen.utils.Alerts;
import africa.jopen.utils.XUtils;
import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import io.github.palexdev.materialfx.controls.cell.MFXNotificationCell;
import io.github.palexdev.materialfx.notifications.MFXNotificationCenterSystem;
import io.github.palexdev.materialfx.notifications.MFXNotificationSystem;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import static africa.jopen.utils.ConstantReference.CONFIG_KEY_DEFAULT;
import static africa.jopen.utils.XUtils.*;


public class BaseApplication extends Application {
	private final Logger logger = Logger.getLogger(BaseApplication.class.getName());


	private Pane        splashLayout;
	private ProgressBar loadProgress;
	private Label       progressText;

	private static final int SPLASH_WIDTH  = 476;
	private static final int SPLASH_HEIGHT = 394;

	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void init () throws Exception {
		logger.info("inti()");
		super.init();
		EventBus.getDefault().register(this);

		ImageView splash = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/wall.png"))));
		loadProgress = new ProgressBar();
		loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
		progressText = new Label(" . . .");
		splashLayout = new VBox();
		splashLayout.getChildren().addAll(splash, loadProgress, progressText);
		splashLayout.setPadding(new Insets(20));
		progressText.setAlignment(Pos.CENTER);
		splashLayout.setStyle(
				"-fx-padding: 5; " +
				"-fx-background-color: white; " +
				"-fx-border-width:5; " +
				"-fx-border-color: " +
				"linear-gradient(" +
				"to bottom, " +
				"#49a0ff, " +
				"derive(#49a0ff, 50%)" +
				");"
		);
		splashLayout.setEffect(new DropShadow());
	}

	// UI updates must run on MainThread
	@Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
	public void onEvent (MessageEvent event) {

	}

	@Override
	public void start (Stage stage) throws IOException {
		final Task<ObservableList<String>> loadModulesTasks = new Task<>() {
			@Override
			protected ObservableList<String> call () throws InterruptedException {
				ObservableList<String> loadedModules =FXCollections.observableArrayList();
				ObservableList<String> availableModules = FXCollections.observableArrayList("Janus", "Http", "WebSocket", "Sessions", "Admin Session");

				updateMessage("Reading . . .");

				var adminBasePath = getLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path");
				if (adminBasePath == null || adminBasePath.isEmpty()) {
					saveLocalCache(CONFIG_KEY_DEFAULT, "admin_base_path", "/admin");
				}
				loadConf();

				Thread.sleep(9400);
				for (int i = 0; i < availableModules.size(); i++) {
					Thread.sleep(3400);
					updateProgress(i + 1, availableModules.size());
					String nextModule = availableModules.get(i);
					loadedModules.add(nextModule);
					updateMessage("Loading  ... " + nextModule);
				}
				Thread.sleep(1400);
				updateMessage("Done Loading.");

				return loadedModules;
			}
		};
		showSplash(stage, loadModulesTasks, this::showMainStage);
		new Thread(loadModulesTasks).start();


	}

	private void showMainStage () {
		try {

			Stage stage = new Stage(StageStyle.DECORATED);
			var janusUrl  = getLocalCache(CONFIG_KEY_DEFAULT, "janus_url");
			var isLoggedIn = janusUrl == null || janusUrl.isEmpty();

			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(XUtils.NAVIGATION.get(isLoggedIn ? "Login" : "Main"))));

			Scene scene = new Scene(root);
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
		} catch (IOException e) {
			logger.severe(e.getMessage());

		}
	}

	private void showSplash (final Stage initStage, Task<?> task, InitCompletionHandler initCompletionHandler) {
		progressText.textProperty().bind(task.messageProperty());
		loadProgress.progressProperty().bind(task.progressProperty());
		task.stateProperty().addListener((observableValue, oldState, newState) -> {
			if (newState == Worker.State.SUCCEEDED) {
				loadProgress.progressProperty().unbind();
				loadProgress.setProgress(1);
				initStage.toFront();
				FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
				fadeSplash.setFromValue(1.0);
				fadeSplash.setToValue(0.0);
				fadeSplash.setOnFinished(actionEvent -> initStage.hide());
				fadeSplash.play();
				initCompletionHandler.complete();
			}
		});

		Scene             splashScene = new Scene(splashLayout, Color.TRANSPARENT);
		final Rectangle2D bounds      = Screen.getPrimary().getBounds();
		initStage.setScene(splashScene);
		initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
		initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
		initStage.initStyle(StageStyle.TRANSPARENT);
		initStage.setAlwaysOnTop(true);
		initStage.show();
	}

	@Override
	public void stop () throws Exception {
		super.stop();
		EventBus.getDefault().unregister(this);
	}

	public interface InitCompletionHandler {
		void complete ();
	}


}
