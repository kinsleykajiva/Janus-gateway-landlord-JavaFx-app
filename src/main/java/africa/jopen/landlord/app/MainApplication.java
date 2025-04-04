package africa.jopen.landlord.app;

import africa.jopen.landlord.configs.ConstantReference;
import africa.jopen.landlord.network.JanusAdminClient;
import africa.jopen.landlord.utils.MessageEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static africa.jopen.landlord.configs.CacheUtil.isNotLoggedIn;
import static africa.jopen.landlord.configs.CacheUtil.loadConf;

public class MainApplication extends Application {
    @Override
    public void init() throws Exception {
        super.init();
        EventBus.getDefault().register(this);
    }

    @Override
    public void stop() throws Exception {
        EventBus.getDefault().unregister(this);
        super.stop();
    }



    @Override
    public void start(Stage stage) throws IOException {
        loadConf();
        boolean isNotLoggedIn = isNotLoggedIn();
        System.out.println("Is not logged in: " + isNotLoggedIn);

        JanusAdminClient client = null;

        try {
            client = new JanusAdminClient();
            client.connect();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent (MessageEvent event) {

    }
}