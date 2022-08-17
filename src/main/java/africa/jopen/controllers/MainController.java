package africa.jopen.controllers;

import africa.jopen.controllers.apis.JanusAPIControllers;
import africa.jopen.controllers.home.HomeController;
import africa.jopen.controllers.janus.SessionsController;
import africa.jopen.controllers.settings.SettingsController;
import africa.jopen.events.MessageEvent;
import africa.jopen.utils.XUtils;
import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoaderBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static africa.jopen.utils.ConstantReference.CONFIG_KEY_DEFAULT;
import static africa.jopen.utils.XUtils.getLocalCache;
import static africa.jopen.utils.XUtils.loadConf;

public class MainController implements Initializable {
	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	private       Stage           stage;

	public MainController () {
		EventBus.getDefault().register(this);
	}

	public @FXML JFXButton btnSessions, btnJanusConf, btnJanusSip, btnSettings, btnHome, btnJanusHttp, btnJanusWebsockets, btnLogOut;
	public @FXML VBox            mainNav;
	public @FXML ScrollPane      body;
	public @FXML Label           title;
	private      List<JFXButton> vBoxes;


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
		vBoxes = List.of(btnSessions, btnHome, btnJanusConf, btnJanusSip, btnJanusHttp, btnJanusWebsockets, btnSettings);

		onEvent(new MessageEvent(MessageEvent.MESSAGE_EVENT_UPDATE_CONFIGS));

		MFXLoader loader = new MFXLoader();
		loader.addView(MFXLoaderBean.of("Home", XUtils.loadURL(XUtils.NAVIGATION.get("Home"))).setControllerFactory(c -> new HomeController()).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of("Sessions", XUtils.loadURL(XUtils.NAVIGATION.get("Sessions"))).setControllerFactory(c -> new SessionsController()).setDefaultRoot(true).get());
		loader.addView(MFXLoaderBean.of("Settings", XUtils.loadURL(XUtils.NAVIGATION.get("Settings"))).setControllerFactory(c -> new SettingsController()).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of("JanusConfig", XUtils.loadURL(XUtils.NAVIGATION.get("JanusConfig"))).setControllerFactory(c -> new JanusAPIControllers("janus")).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of("btnJanusSip", XUtils.loadURL(XUtils.NAVIGATION.get("JanusConfig"))).setControllerFactory(c -> new JanusAPIControllers("sip")).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of("btnJanusHttp", XUtils.loadURL(XUtils.NAVIGATION.get("JanusConfig"))).setControllerFactory(c -> new JanusAPIControllers("http")).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of("btnJanusWebsockets", XUtils.loadURL(XUtils.NAVIGATION.get("JanusConfig"))).setControllerFactory(c -> new JanusAPIControllers("websocket")).setDefaultRoot(false).get());

		loader.setOnLoadedAction(beans -> beans.forEach(bean -> {
			switch (bean.getViewName()) {
				case "Sessions" -> btnSessions.setOnMouseClicked(event -> {
					body.setContent(bean.getRoot());
					setSelcted(btnSessions);
					title.setText("Sessions");
				});

				case "Home" -> btnHome.setOnMouseClicked(event -> {
					body.setContent(bean.getRoot());
					setSelcted(btnHome);
					title.setText("Home");
				});
				case "Settings" -> btnSettings.setOnMouseClicked(event -> {
					body.setContent(bean.getRoot());
					setSelcted(btnSettings);
					title.setText("App Settings");
				});
				case "JanusConfig" -> btnJanusConf.setOnMouseClicked(event -> {
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


			}
		}));

		loader.start();

	}
}
