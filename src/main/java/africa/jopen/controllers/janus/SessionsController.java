package africa.jopen.controllers.janus;

import africa.jopen.events.MessageEvent;
import africa.jopen.janus.handles.HandleReq;
import africa.jopen.utils.ConstantReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

import static africa.jopen.janus.handles.HandleReq.LAST_HANDLESINFO_MAP;
import static africa.jopen.utils.XUtils.loadConf;

public class SessionsController implements Initializable {

	private final BooleanProperty animatedProperty = new SimpleBooleanProperty(true);
	Logger logger = Logger.getLogger(SessionsController.class.getName());

	private final Set<Long>                           existingSessionsSet    = new HashSet<>();
	private final Set<Long>                           existingSessionsSetNew = new HashSet<>();
	public @FXML  Accordion                           accordionSessions;
	public @FXML  Label                               txtRefresh;
	public @FXML  HBox                                hBxOptions;
	public @FXML  org.controlsfx.control.ToggleSwitch autoRefreshSwitch;
	private Timeline cycleChecker;
	public @FXML ProgressIndicator progressIndicator;

	public SessionsController () {
		EventBus.getDefault().register(this);
	}

	private void loadCycleChecker () {
		cycleChecker = new Timeline(new KeyFrame(Duration.seconds(ConstantReference.JANUS_ADMIN_SESSION_INTERVAL_DELAY), event -> loadSessions()));
		cycleChecker.setCycleCount(Timeline.INDEFINITE);

	}

	@Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
	public void onEvent (MessageEvent event) {
		if (event.getEventType() == MessageEvent.MESSAGE_EVENT_UPDATE_CONFIGS) {
			loadConf();
		}

	}

	@Override
	public void initialize (URL location, ResourceBundle resources) {
		txtRefresh.setStyle("-fx-text-fill: #19A9CB");
		progressIndicator.setVisible(false);
		loadCycleChecker();
		txtRefresh.setOnMouseClicked(e -> loadSessions());
		loadSessions();
		autoRefreshSwitch.selectedProperty().addListener((ov, t1, t2) -> {
			if (autoRefreshSwitch.isSelected()) {
				logger.info("Fetching sessions from server");
				cycleChecker.stop();
				cycleChecker.play();
				txtRefresh.setDisable(true);
			} else {
				cycleChecker.stop();
				logger.info("stopped Fetching sessions from server");
				txtRefresh.setDisable(false);
			}
		});
	}


	synchronized void loadSessions () {
		progressIndicator.setVisible(true);
		ObjectMapper Obj = new ObjectMapper();
		HandleReq.getSessionsL().thenAccept((handlesInfoMap) -> Platform.runLater(() -> {

			Set<BigInteger> existingHandles = new HashSet<>();
			handlesInfoMap.forEach((key, value) -> {
				if (value != null) {
					try {
						existingSessionsSetNew.add(LAST_HANDLESINFO_MAP.get(key).getSession_id());
						if (!existingSessionsSet.contains(LAST_HANDLESINFO_MAP.get(key).getSession_id())) {
							String     jsonStr             = Obj.writeValueAsString(LAST_HANDLESINFO_MAP.get(key));
							TitledPane scrollableTextBlock = getTitledPane("Session - " + key + " -" + LAST_HANDLESINFO_MAP.get(key).getPlugin_specific().getUsername(), jsonStr, LAST_HANDLESINFO_MAP.get(key).getSession_id());
							scrollableTextBlock.setId(String.valueOf(LAST_HANDLESINFO_MAP.get(key).getSession_id()));
							existingSessionsSet.add(LAST_HANDLESINFO_MAP.get(key).getSession_id());
							accordionSessions.getPanes().add(scrollableTextBlock);
						} else {
							existingHandles.add(LAST_HANDLESINFO_MAP.get(key).getHandle_id());
						}
					} catch (IOException e) {
						e.printStackTrace();
						logger.severe("|Exception " + e.getMessage());
					}
				}
			});
			var diff = findCollectionsDifference(existingSessionsSet, existingSessionsSetNew);
			diff.forEach(d -> {
				if (!existingSessionsSetNew.contains(d)) {
					existingSessionsSet.remove(d);
					var node = accordionSessions.getPanes().stream().filter(e -> e.getId() != null && e.getId().equals(String.valueOf(d))).findFirst();
					node.ifPresent(titledPane -> accordionSessions.getPanes().remove(titledPane));

				}
			});
			diff = null;// give away for garbage collection
			existingHandles.forEach(handles -> flowList.stream()
					.filter(fl -> fl.getId().equals(handles + ""))
					.forEach(flow -> {
						flow.getChildren().clear();
						var obj = LAST_HANDLESINFO_MAP.get(handles);
						if (obj != null) {
							try {
								String jsonStr = Obj.writeValueAsString(obj);
								JSONObject jsonObject = new JSONObject(jsonStr);
								flow.getChildren().add(new TextFlow(new Text(jsonObject.toString(8))));
							} catch (JsonProcessingException e) {
								throw new RuntimeException(e);
							}
						}

					}));
			existingSessionsSetNew.clear();
			progressIndicator.setVisible(false);


		})).exceptionally(ex -> {
			logger.severe("Exception " + ex.getMessage());
			return null;
		});
	}

	public static <T> Set<T> findCollectionsDifference (final Set<T> setOne, final Set<T> setTwo) {
		Set<T> result = new HashSet<T>(setOne);
		result.removeIf(setTwo::contains);
		return result;
	}

	List<TextFlow> flowList = new ArrayList<>();

	@NotNull
	private TitledPane getTitledPane (String title, String json, long sessionId) {
		JSONObject jsonObject = new JSONObject(json);
		var        textFlow   = new TextFlow(new Text(jsonObject.toString(8)));
		textFlow.setId(String.valueOf(sessionId));
		textFlow.setPadding(new Insets(0, 10, 0, 0));
		flowList.add(textFlow);
		var scrollTextBlockContent = new ScrollPane(textFlow);
		scrollTextBlockContent.setMinHeight(700);
		scrollTextBlockContent.setFitToWidth(true);
		var scrollableTextBlock = new TitledPane("_" + title, scrollTextBlockContent);

		scrollableTextBlock.setMnemonicParsing(true);
		scrollableTextBlock.animatedProperty().bind(animatedProperty);
		scrollableTextBlock.setCollapsible(true);
		scrollableTextBlock.setExpanded(false);
		return scrollableTextBlock;
	}
}
