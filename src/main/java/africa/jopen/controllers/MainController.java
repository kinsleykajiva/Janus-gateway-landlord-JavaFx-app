package africa.jopen.controllers;

import africa.jopen.controllers.apis.JanusAPIControllers;
import africa.jopen.controllers.home.HomeController;
import africa.jopen.controllers.janus.SessionsController;
import africa.jopen.controllers.settings.SettingsController;
import africa.jopen.events.MessageEvent;
import africa.jopen.utils.Alerts;
import africa.jopen.utils.ConstantReference;
import africa.jopen.utils.XUtils;
import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoaderBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static africa.jopen.utils.ConstantReference.CONFIG_KEY_DEFAULT;
import static africa.jopen.utils.XUtils.*;

public class MainController implements Initializable {

	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	public @FXML JFXButton btnSessions, btnJanusConf, btnJanusSip, btnSettings, btnHome, btnJanusHttp, btnJanusWebsockets, btnLogOut, btnJanusSampleEvents;
	public @FXML  VBox            mainNav;
	public @FXML  ScrollPane      body;
	public @FXML  Label           title;
	private       Stage    stage;
	private final Logger   logger              = Logger.getLogger(MainController.class.getName());
	private final Runnable existingSessionsSet = () -> {
		saveLocalCache(CONFIG_KEY_DEFAULT, "username", null);
		saveLocalCache(CONFIG_KEY_DEFAULT, "password", null);
		saveLocalCache(CONFIG_KEY_DEFAULT, "janus_url", null);
		logger.info("Exiting Sessions .");
		stage = (Stage) body.getScene().getWindow();
		try {
			Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(NAVIGATION.get("Login"))));
			Scene  scene = new Scene(root1);
			stage.setScene(scene);
			stage.show();
		} catch (IOException ex) {
			logger.severe(ex.getMessage());
			throw new RuntimeException(ex);
		}

	};
	private final Runnable        CancelSessionsAttempt = () -> {
		saveLocalCache(CONFIG_KEY_DEFAULT, "username", null);
		saveLocalCache(CONFIG_KEY_DEFAULT, "password", null);
		logger.info("Cancelled Log out attempt");

	};
	private       List<JFXButton> vBoxes;
	public MainController () {
		EventBus.getDefault().register(this);
	}

	void setSelcted (JFXButton tagrget) {
		vBoxes.forEach(vBox -> {
			if (vBox.equals(tagrget)) {
				vBox.setStyle("-fx-background-color:   #4c9cb7");
			} else {
				vBox.setStyle("-fx-background-color: transparent");
			}
		});

	}

	@Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
	public void onEvent (MessageEvent event) {
		if (event.getEventType() == MessageEvent.MESSAGE_EVENT_UPDATE_CONFIGS) {
			loadConf();
		}
		var username = getLocalCache(CONFIG_KEY_DEFAULT, "username");
		if (username.isEmpty()) {
			btnJanusConf.setDisable(true);
			btnJanusSip.setDisable(true);
			btnJanusHttp.setDisable(true);
			btnJanusWebsockets.setDisable(true);
		}

	}

	@Override
	public void initialize (URL location, ResourceBundle resources) {

		loadConf();
		vBoxes = List.of(btnSessions, btnHome, btnJanusConf, btnJanusSip, btnJanusHttp, btnJanusWebsockets, btnSettings, btnJanusSampleEvents);

		onEvent(new MessageEvent(MessageEvent.MESSAGE_EVENT_UPDATE_CONFIGS));

		MFXLoader loader = new MFXLoader();
		loader.addView(MFXLoaderBean.of("Home", XUtils.loadURL(XUtils.NAVIGATION.get("Home"))).setControllerFactory(c -> new HomeController()).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of(ConstantReference.SESSIONS, XUtils.loadURL(XUtils.NAVIGATION.get(ConstantReference.SESSIONS))).setControllerFactory(c -> new SessionsController()).setDefaultRoot(true).get());
		loader.addView(MFXLoaderBean.of(ConstantReference.SETTINGS, XUtils.loadURL(XUtils.NAVIGATION.get(ConstantReference.SETTINGS))).setControllerFactory(c -> new SettingsController()).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of(ConstantReference.JANUS_CONFIG, XUtils.loadURL(XUtils.NAVIGATION.get(ConstantReference.JANUS_CONFIG))).setControllerFactory(c -> new JanusAPIControllers("janus")).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of("btnJanusSip", XUtils.loadURL(XUtils.NAVIGATION.get(ConstantReference.JANUS_CONFIG))).setControllerFactory(c -> new JanusAPIControllers("sip")).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of("btnJanusHttp", XUtils.loadURL(XUtils.NAVIGATION.get(ConstantReference.JANUS_CONFIG))).setControllerFactory(c -> new JanusAPIControllers("http")).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of("btnJanusWebsockets", XUtils.loadURL(XUtils.NAVIGATION.get(ConstantReference.JANUS_CONFIG))).setControllerFactory(c -> new JanusAPIControllers("websocket")).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of("btnJanusSampleEvents", XUtils.loadURL(XUtils.NAVIGATION.get(ConstantReference.JANUS_CONFIG))).setControllerFactory(c -> new JanusAPIControllers("sampleeventshandler")).setDefaultRoot(false).get());


		btnLogOut.setOnAction(action -> Alerts.confirmation("Logging out", "Are you sure you want to exit this session ?", existingSessionsSet, CancelSessionsAttempt));

		loader.setOnLoadedAction(beans -> beans.forEach(bean -> {
			switch (bean.getViewName()) {
				case ConstantReference.SESSIONS -> btnSessions.setOnMouseClicked(event -> {
					body.setContent(bean.getRoot());
					setSelcted(btnSessions);
					title.setText(ConstantReference.SESSIONS);
				});

				case "Home" -> btnHome.setOnMouseClicked(event -> {
					body.setContent(bean.getRoot());
					setSelcted(btnHome);
					title.setText("Home");
				});
				case ConstantReference.SETTINGS -> btnSettings.setOnMouseClicked(event -> {
					body.setContent(bean.getRoot());
					setSelcted(btnSettings);
					title.setText("App Settings");
				});
				case ConstantReference.JANUS_CONFIG -> btnJanusConf.setOnMouseClicked(event -> {
					body.setContent(bean.getRoot());
					setSelcted(btnJanusConf);
					title.setText("JanusConfig for janus.jcfg");
				});
				case "btnJanusSip" -> btnJanusSip.setOnMouseClicked(event -> {
					body.setContent(bean.getRoot());
					setSelcted(btnJanusSip);
					title.setText("JanusConfig for janus.plugin.sip.jcfg");
				});
				case "btnJanusHttp" -> btnJanusHttp.setOnMouseClicked(event -> {
					body.setContent(bean.getRoot());
					setSelcted(btnJanusHttp);
					title.setText("JanusConfig for janus.transport.http.jcfg");
				});
				case "btnJanusWebsockets" -> btnJanusWebsockets.setOnMouseClicked(event -> {
					body.setContent(bean.getRoot());
					setSelcted(btnJanusWebsockets);
					title.setText("JanusConfig for janus.transport.websockets.jcfg");
				});
				case "btnJanusSampleEvents" -> btnJanusSampleEvents.setOnMouseClicked(event -> {
					body.setContent(bean.getRoot());
					setSelcted(btnJanusSampleEvents);
					title.setText("JanusConfig for janus.eventhandler.sampleevh.jcfg.");
				});

				default -> throw new IllegalStateException("Unexpected value: " + bean.getViewName());
			}
		}));
		XUtils.invoke(() -> {
			loader.start();

		});

		//WebSockets webSockets = new WebSockets();

	}
}
