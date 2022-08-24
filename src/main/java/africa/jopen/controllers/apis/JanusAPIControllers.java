package africa.jopen.controllers.apis;

import africa.jopen.events.MessageEvent;
import africa.jopen.janus.plugins.LandLordWebAppReq;
import africa.jopen.models.forms.janusconfig.JanusModel;
import africa.jopen.utils.ConstantReference;
import africa.jopen.utils.UtilSampleBlock;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.Notifications;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static africa.jopen.janus.plugins.LandLordWebAppReq.getRequest;
import static africa.jopen.utils.ConstantReference.CONFIG_KEY_DEFAULT;
import static africa.jopen.utils.XUtils.getLocalCache;
import static africa.jopen.utils.XUtils.loadConf;

public class JanusAPIControllers implements Initializable {
	public static Map<String, Map<String, String>> CONFIG_MODULES_                = Map.ofEntries(
			Map.entry("janus", Map.of("/api/access/janus/current-ssettings", "/api/access/janus/update")),
			Map.entry("sip", Map.of("/api/access/sip/current-ssettings", "/api/access/sip/update")),
			Map.entry("http", Map.of("/api/access/http/current-ssettings", "/api/access/http/update")),
			Map.entry("websocket", Map.of("/api/access/websockets/current-ssettings", "/api/access/websockets/update")),
			Map.entry("sampleeventshandler", Map.of("/api/janus/events/current-ssettings", "/api/janus/events/update"))


	);
	public static Map<String, String>              CONFIG_MODULES_CACHE_RESPONSES = new HashMap<>();
	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	@FXML
	public  BorderPane root_pane;
	Logger logger = Logger.getLogger(JanusAPIControllers.class.getName());
	String result = "";
	String type   = "";
	private JanusModel model;
	//private       String                           urlDestinations                = "";
	private       String                           username                       = "";
	private       Node                             progressBar;
	//private final Map<String, String>              navigateUrlMap                 = new HashMap<>();
	private       Accordion                        form;
	private       Label                            codeLabel;
	private       ScrollPane                       scrollContent;

	public JanusAPIControllers (String type) {
		EventBus.getDefault().register(this);
		this.type = type;
		username = getLocalCache(CONFIG_KEY_DEFAULT, "username");

	}

	private ProgressIndicator progressIndicator (double progress, boolean disabled) {
		var indicator = new ProgressIndicator(progress);
		indicator.setMinSize(50, 50);
		indicator.setMaxSize(50, 50);
		indicator.setDisable(disabled);
		indicator.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
		return indicator;
	}

	private UtilSampleBlock basicIndicatorSamples () {
		var flowPane = new FlowPane(20, 20);
		flowPane.getChildren().addAll(
				progressIndicator(0.5, false)
		);
		flowPane.setAlignment(Pos.CENTER);

		return new UtilSampleBlock("Updating Janus Server", flowPane);
	}


	@Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
	public void onEvent (MessageEvent event) {
		if (event.getEventType() == MessageEvent.MESSAGE_EVENT_UPDATE_CONFIGS) {
			loadConf();
		}

		if (event.getEventType() == MessageEvent.MESSAGE_EVENT_REFRESH_CONFIGS) {
			CONFIG_MODULES_CACHE_RESPONSES.put(type, null);
			fetchRemoteData();
		}


	}


	@Override
	public void initialize (URL location, ResourceBundle resources) {
		if (username.isEmpty()) {
			return;

		}


		VBox statusContent = new VBox();
		HBox formButtons   = new HBox();
		root_pane.getStyleClass().add("root-pane");
		GridPane controls = new GridPane();
		scrollContent = new ScrollPane();

		final boolean[] isShowingForm = {true};
		codeLabel = new Label("--");
		codeLabel.setTextAlignment(TextAlignment.LEFT);
		codeLabel.wrapTextProperty().set(true);// making it selectable by default
		codeLabel.setTextAlignment(TextAlignment.LEFT);
		codeLabel.setWrapText(true);


		scrollContent.setFitToWidth(true);
		Button save  = new Button("Save");
		Button reset = new Button("JCFG", new FontIcon(Feather.CODE));

		reset.getStyleClass().add("reset-button");
		scrollContent.getStyleClass().add("scroll-pane");

		save.setStyle("""
				-fx-font-size: 14px;
				-fx-font-weight: normal;
				-fx-text-alignment: center;
				-fx-cursor: hand;
				-fx-background-radius: 4px;
				-fx-background-insets: 0 0 0 0, 0, 1, 2;
				-fx-border-width: 1px;
				-fx-border-radius: 4px;
				""");
		progressBar = basicIndicatorSamples().getRoot();
		progressBar.setVisible(true);
		reset.setOnAction(ev -> {
			new animatefx.animation.FadeOut(scrollContent).play(); //hide
			new animatefx.animation.FadeIn(scrollContent).play();// show
			if (isShowingForm[0]) {
				scrollContent.setContent(codeLabel);
				reset.setGraphic(new FontIcon(Feather.COLUMNS));
				reset.setText("Form");
				save.setDisable(true);
			} else {
				scrollContent.setContent(form);
				reset.setGraphic(new FontIcon(Feather.CODE));
				reset.setText("JCFG");
				save.setDisable(false);
			}

			isShowingForm[0] = !isShowingForm[0];
		});
		save.setOnAction(event -> {
			CONFIG_MODULES_CACHE_RESPONSES.put(type, null);
			new animatefx.animation.BounceIn(progressBar).play();
			Task<String> communicateWithServerTask = new Task<>() {

				@Override
				protected String call () {
					var result = LandLordWebAppReq.postRequest((String) CONFIG_MODULES_.get(type).values().toArray()[0], model.getCurrentObject());
					logger.info("Post request: " + result);
					return result;
				}

			};

			communicateWithServerTask.setOnFailed(eventw -> {
				new animatefx.animation.BounceOut(progressBar).play();
				Notifications.create()
						.title(StringUtils.capitalize(this.type) + ConstantReference.PROCESS_RESULT)
						.text("Failed to update,Please try again or check for server response")
						.showError();

			});
			communicateWithServerTask.setOnSucceeded(eventw -> {

				new animatefx.animation.BounceOut(progressBar).play();
				var result = communicateWithServerTask.getValue();
				if (result == null) {
					Notifications.create()
							.title(StringUtils.capitalize(this.type) + ConstantReference.PROCESS_RESULT)
							.text("Failed to update,Please try again or check for server response")
							.showError();
				} else {
					JSONObject resultObj = new JSONObject(result);
					if (resultObj.getBoolean("success")) {
						Notifications.create()
								.title(StringUtils.capitalize(this.type) + ConstantReference.PROCESS_RESULT)
								.text("Update Successfully")
								.show();
						fetchRemoteData();
					} else {
						Notifications.create()
								.title(StringUtils.capitalize(this.type) + ConstantReference.PROCESS_RESULT)
								.text("Failed to update ")
								.showError();
					}

				}
			});

			execute2(communicateWithServerTask);

		});


		statusContent.setPadding(new Insets(10));
		statusContent.setSpacing(10);
		statusContent.setPrefWidth(200);
		statusContent.getStyleClass().add("bordered");
		controls.add(statusContent, 0, 2);
		statusContent.getChildren().addAll(progressBar);

		save.setMaxWidth(Double.MAX_VALUE);
		reset.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(save, Priority.ALWAYS);
		HBox.setHgrow(reset, Priority.ALWAYS);
		formButtons.getChildren().addAll(reset, save);
		formButtons.setPadding(new Insets(10));
		formButtons.setSpacing(10);
		formButtons.setPrefWidth(200);
		formButtons.getStyleClass().add("bordered");
		controls.add(formButtons, 0, 1);
		save.setStyle("-fx-background-color: #19a9cb");

		controls.setPrefWidth(200);

		root_pane.setCenter(scrollContent);
		root_pane.setRight(controls);
		root_pane.setPadding(new Insets(20, 0, 0, 20));
		new animatefx.animation.BounceOut(progressBar).play();// hide it first

		fetchRemoteData();

		/*model = new JanusModel(CONFIG_MODULES_CACHE_RESPONSES.get(type), executor);
		form = model.getFormInstance();
		Platform.runLater(() -> {
			scrollContent.setContent(form);
			codeLabel.setText(model.jcfg);
		});*/

	}

	private void fetchRemoteData () {
		new Thread(() -> {

			if (CONFIG_MODULES_CACHE_RESPONSES.get(type) == null) {
				var reqResult = getRequest((String) CONFIG_MODULES_.get(type).keySet().toArray()[0]);
				CONFIG_MODULES_CACHE_RESPONSES.put(type, reqResult);
			}


			model = new JanusModel(CONFIG_MODULES_CACHE_RESPONSES.get(type), executor);
			form = model.getFormInstance();
			Platform.runLater(() -> {
				scrollContent.setContent(form);
				codeLabel.setText(model.jcfg);
			});
		}).start();
	}


	private void executeAndWait (Runnable runnable) {
		try {
			executor.submit(runnable).get();
		} catch (Exception e) {
			logger.severe(ConstantReference.EXECUTE_TASK_FAILED + e);
		}
	}

	private void execute (Runnable runnable) {
		try {
			executor.submit(runnable);
		} catch (Exception e) {
			logger.severe(ConstantReference.EXECUTE_TASK_FAILED + e);
		}
	}

	private void execute2 (Runnable runnable) {
		try {
			executor.execute(runnable);
		} catch (Exception e) {
			logger.severe(ConstantReference.EXECUTE_TASK_FAILED + e);
		}
	}

	private <T> T executeAndGet (Callable<T> callable) {
		try {
			return executor.submit(callable).get();
		} catch (Exception e) {
			logger.severe(ConstantReference.EXECUTE_TASK_FAILED + e);
		}

		return null;
	}


}
