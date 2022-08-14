package africa.jopen.controllers;

import africa.jopen.controllers.apis.JanusAPIControllers;
import africa.jopen.controllers.home.HomeController;
import africa.jopen.controllers.janus.SessionsController;
import africa.jopen.events.MessageEvent;
import africa.jopen.utils.XUtils;
import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoaderBean;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.stage.StageStyle;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.kordamp.ikonli.feather.Feather;

import java.util.concurrent.ScheduledExecutorService;

import static africa.jopen.utils.XUtils.*;

public class MainController implements Initializable {
	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	private       Stage           stage;

	public MainController () {
		EventBus.getDefault().register(this);
	}

	public @FXML JFXButton btnSessions, btnJanusConf, btnJanusSip, btnHome , btnJanusHttp ,btnJanusWebsockets  ;
	public @FXML VBox       mainNav;
	public @FXML ScrollPane body;
	public @FXML Label      title;
	List<JFXButton> vBoxes;

	@Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
	public void onEvent (MessageEvent event) {

	}

	void setSelcted (JFXButton tagrget) {


		vBoxes.forEach(vBox -> {
			if (vBox.equals(tagrget)) {
				vBox.setStyle("-fx-background-color:   #4c9cb7");
			} else {
				vBox.setStyle("-fx-background-color: transparent");
			}
		});
		//tagrget.setStyle("-fx-border-color: red");
	}

	@Override
	public void initialize (URL location, ResourceBundle resources) {
		MFXLoader loader = new MFXLoader();
		loader.addView(MFXLoaderBean.of("Home", XUtils.loadURL(XUtils.NAVIGATION.get("Home"))).setControllerFactory(c -> new HomeController()).setDefaultRoot(false).get());
		loader.addView(MFXLoaderBean.of("Sessions", XUtils.loadURL(XUtils.NAVIGATION.get("Sessions"))).setControllerFactory(c -> new SessionsController()).setDefaultRoot(true).get());
		loader.addView(MFXLoaderBean.of("JanusConfig", XUtils.loadURL(XUtils.NAVIGATION.get("JanusConfig"))).setControllerFactory(c -> new JanusAPIControllers("janus")).setDefaultRoot(true).get());
		loader.addView(MFXLoaderBean.of("btnJanusSip", XUtils.loadURL(XUtils.NAVIGATION.get("JanusConfig"))).setControllerFactory(c -> new JanusAPIControllers("sip")).setDefaultRoot(true).get());
		loader.addView(MFXLoaderBean.of("btnJanusHttp", XUtils.loadURL(XUtils.NAVIGATION.get("JanusConfig"))).setControllerFactory(c -> new JanusAPIControllers("http")).setDefaultRoot(true).get());
		loader.addView(MFXLoaderBean.of("btnJanusWebsockets", XUtils.loadURL(XUtils.NAVIGATION.get("JanusConfig"))).setControllerFactory(c -> new JanusAPIControllers("websocket")).setDefaultRoot(true).get());
		vBoxes = List.of(btnSessions, btnHome, btnJanusConf, btnJanusSip ,btnJanusHttp ,btnJanusWebsockets );
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

		saveLocalCache("Main", "house1", "locations2");

		logInfo(getLocalCache("default", "admin_secret"));

	}
}
