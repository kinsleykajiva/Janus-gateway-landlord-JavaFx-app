package africa.jopen.application;




import africa.jopen.events.MessageEvent;
import africa.jopen.janus.handles.HandleReq;
import africa.jopen.pojos.User;
import africa.jopen.utils.ViewManager;
import africa.jopen.utils.XUtils;
import com.dustinredmond.fxtrayicon.FXTrayIcon;
import com.google.gson.*;
import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import io.github.palexdev.materialfx.controls.cell.MFXNotificationCell;
import io.github.palexdev.materialfx.notifications.MFXNotificationCenterSystem;
import io.github.palexdev.materialfx.notifications.MFXNotificationSystem;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;
import java.util.logging.Logger;
import java.awt.*;
import java.io.*;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;


import static africa.jopen.janus.handles.HandleReq.LAST_HANDLESINFO_MAP;
import static africa.jopen.janus.settings.SettingsReq.adminReq;
import static africa.jopen.utils.XUtils.*;


public class BaseApplication extends Application {
    Logger  logger = Logger.getLogger( BaseApplication.class.getName() );
    private float increment = 0;
    private float progress = 0;

    private User user;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {

        logger.info("inti()");
        super.init();
        float total = 43; // the difference represents the views not loaded
        increment = 100f / total;
        EventBus.getDefault().register(this);

         /* load(XUtils.NAVIGATION.get("Home") );
         load(XUtils.NAVIGATION.get("Main") );*/


    }
    // UI updates must run on MainThread
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {

        System.out.println("Received event: " + event.getEventType());
        // textField.setText(event.message);
    }

    private static final String INPUT = "{\n" +
                                        "   \"janus\": \"success\",\n" +
                                        "   \"session_id\": 682486577610727,\n" +
                                        "   \"transaction\": \"UWkKkaOp8l1G\",\n" +
                                        "   \"handle_id\": 7656445411101973,\n" +
                                        "   \"info\": {\n" +
                                        "      \"session_id\": 682486577610727,\n" +
                                        "      \"session_last_activity\": 1188362769042,\n" +
                                        "      \"session_timeout\": 60,\n" +
                                        "      \"session_transport\": \"janus.transport.http\",\n" +
                                        "      \"handle_id\": 7656445411101973,\n" +
                                        "      \"opaque_id\": \"siptest-dM4dPqQSOXkh\",\n" +
                                        "      \"loop-running\": true,\n" +
                                        "      \"created\": 1180857458035,\n" +
                                        "      \"current_time\": 1188378551803,\n" +
                                        "      \"plugin\": \"janus.plugin.sip\",\n" +
                                        "      \"plugin_specific\": {\n" +
                                        "         \"username\": \"tafadzwa-sango2\",\n" +
                                        "         \"authuser\": \"tafadzwa-sango2\",\n" +
                                        "         \"secret\": \"(hidden)\",\n" +
                                        "         \"display_name\": \"\",\n" +
                                        "         \"identity\": \"sip:tafadzwa-sango2@prod-eu-voip.yootok.com\",\n" +
                                        "         \"registration_status\": \"registered\",\n" +
                                        "         \"call_status\": \"idle\",\n" +
                                        "         \"establishing\": 0,\n" +
                                        "         \"established\": 0,\n" +
                                        "         \"hangingup\": 0,\n" +
                                        "         \"destroyed\": 0\n" +
                                        "      },\n" +
                                        "      \"flags\": {\n" +
                                        "         \"got-offer\": false,\n" +
                                        "         \"got-answer\": false,\n" +
                                        "         \"negotiated\": false,\n" +
                                        "         \"processing-offer\": false,\n" +
                                        "         \"starting\": true,\n" +
                                        "         \"ice-restart\": false,\n" +
                                        "         \"ready\": false,\n" +
                                        "         \"stopped\": false,\n" +
                                        "         \"alert\": true,\n" +
                                        "         \"trickle\": true,\n" +
                                        "         \"all-trickles\": true,\n" +
                                        "         \"resend-trickles\": false,\n" +
                                        "         \"trickle-synced\": false,\n" +
                                        "         \"data-channels\": false,\n" +
                                        "         \"has-audio\": false,\n" +
                                        "         \"has-video\": false,\n" +
                                        "         \"new-datachan-sdp\": false,\n" +
                                        "         \"rfc4588-rtx\": false,\n" +
                                        "         \"cleaning\": false,\n" +
                                        "         \"e2ee\": false\n" +
                                        "      },\n" +
                                        "      \"sdps\": {},\n" +
                                        "      \"queued-packets\": 0,\n" +
                                        "      \"streams\": []\n" +
                                        "   }\n" +
                                        "}";

    private static final Image JSON_IMAGE = new Image("https://i.stack.imgur.com/1slrh.png");

    private static void prependString(TreeItem<Value> item, String string) {
        String val = item.getValue().text;
        item.getValue().text = (val == null ? string  : string + " : " + val);
    }

    private enum Type {
        OBJECT(new Rectangle2D(45, 52, 16, 18)),
        ARRAY(new Rectangle2D(61, 88, 16, 18)),
        PROPERTY(new Rectangle2D(31, 13, 16, 18));

        private final Rectangle2D viewport;

        private Type(Rectangle2D viewport) {
            this.viewport = viewport;
        }

    }

    private static final class Value {

        private String text;
        private final Type type;

        public Value(Type type) {
            this.type = type;
        }

        public Value(String text, Type type) {
            this.text = text;
            this.type = type;
        }

    }

    private static TreeItem<Value> createTree(JsonElement element) {
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
    public void start(Stage stage) throws IOException {


        // extracted(stage);

// saveLocalCache("default","janus_url","http://localhost:7088");

        saveLocalCache("default","admin_secret","janusoverlord");

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(XUtils.NAVIGATION.get("Main"))));
        Scene scene = new Scene(root);
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


        logger.info(" ,,," + adminReq());

     //   confirmationDialogButton(scene,stage);

    }

    private static void extracted (Stage stage) {
        JsonElement root1   = JsonParser.parseString(INPUT);

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
            protected void updateItem(Value item, boolean empty) {
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

    private final BooleanProperty showHeaderProperty = new SimpleBooleanProperty(true);
    private final BooleanProperty minDecorationsProperty = new SimpleBooleanProperty(true);
    private StageStyle getModality() {
        return minDecorationsProperty.get() ? StageStyle.UTILITY : StageStyle.DECORATED;
    }
    private void confirmationDialogButton(Scene scene,Stage stage) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Folder Location Access");
        alert.setHeaderText("Please select the default folder selection for the App to use");
        alert.setContentText(null);
        ButtonType cancelBtn = new ButtonType("Ok,Proceed", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelBtn);
        Button show = new Button("Select Folder");
        show.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(stage);

            if(selectedDirectory == null){
                //No Directory selected
                alert.close();
                confirmationDialogButton(scene,stage);
            }else{
                System.out.println(selectedDirectory.getAbsolutePath());
                alert.close();
            }
        });
        alert.setGraphic(show);

        alert.initOwner(scene.getWindow());
        alert.initStyle(getModality());
        alert.showAndWait();
    }

    private void initialScene() {



        String log = logged();
        assert log != null;

        if (log.equals("account") || log.equals("login")) {
           // decorator.setContent(ViewManager.getInstance().get(log).getParent());
        } else {
            //  BaseApplication.decorator.addCustom(userDetail);
            /*userDetail.setProfileAction(event -> {
                Main.ctrl.title.setText("Profile");
                Main.ctrl.body.setContent(ViewManager.getInstance().get("profile"));
                userDetail.getPopOver().hide();
            });*/

           /* userDetail.setSignAction(event -> {
                App.decorator.setContent(ViewManager.getInstance().get("login"));
                section.setLogged(false);
                SectionManager.save(section);
                userDetail.getPopOver().hide();
                if(Main.popConfig.isShowing()) Main.popConfig.hide();
                if(Main.popup.isShowing()) Main.popup.hide();
                App.decorator.removeCustom(userDetail);
            });
            decorator.setContent(ViewManager.getInstance().get("main"));*/
        }

        //decorator.stage.getScene().getsetStage().setOnCloseRequest(event -> {
        //// BaseApplication.getUserDetail().getPopOver().hide();
        //if(Main.popConfig.isShowing()) Main.popConfig.hide();
        //  if(Main.popup.isShowing()) Main.popup.hide();
        //  Platform.exit();
        //  });
        /*try {
            wait(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

       // ViewManager.getInstance().get("main");
    }
    @Override
    public void stop() throws Exception {
        super.stop();
        EventBus.getDefault().unregister(this);
    }

    private void load(String module) {
        logInfo(module);
        try {
            var name = XUtils.NAVIGATION.entrySet().stream().filter(entry -> module.equals(entry.getValue())).findFirst().map(Map.Entry::getKey).orElse("");// find the key from the map,default is empty

            ViewManager.getInstance().put(name, FXMLLoader.load(Objects.requireNonNull(getClass().getResource(module))));
            preloaderNotify();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void preloaderNotify() {
        progress += increment;
        // LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String logged() {
        try {
            File file = new File("dashboard.properties");
            Properties properties = new Properties();

            if (!file.exists()) {
                file.createNewFile();
                return "account";
            } else {
                FileInputStream fileInputStream = new FileInputStream(file);
                properties.load(fileInputStream);
                properties.putIfAbsent("logged", "false");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                properties.store(fileOutputStream, "Dashboard properties");


                File directory = new File("user/");
                properties.load(fileInputStream);
                if (directory.exists()) {
                    if (properties.getProperty("logged").equals("false"))
                        return "login";
                    else
                        return "main";
                } else
                    return "account";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
