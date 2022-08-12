package africa.jopen.controllers.janus;

import africa.jopen.application.BaseApplication;
import africa.jopen.janus.handles.HandleReq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import atlantafx.base.controls.ToggleSwitch;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;
//import com.cedarsoftware.util.io.MetaUtils;

import static africa.jopen.janus.handles.HandleReq.LAST_HANDLESINFO_MAP;
import static atlantafx.base.theme.Styles.*;

public class SessionsController  implements Initializable {
	private final    BooleanProperty expandedProperty = new SimpleBooleanProperty(true);
	private final BooleanProperty animatedProperty = new SimpleBooleanProperty(true);
	Logger logger = Logger.getLogger( SessionsController.class.getName() );


	public @FXML Accordion accordionSessions;
	public @FXML Button btnRefresh;
	public @FXML HBox      hBxOptions;



	@Override
	public void initialize (URL location, ResourceBundle resources) {
		btnRefresh.getStyleClass().add(SUCCESS);

		btnRefresh.setOnAction(e->{
			loadSessions();
		});
		loadSessions();




		/*accordionSessions.expandedPaneProperty().addListener((obs, old, val) -> {
			// make sure the accordion can never be completely collapsed
			boolean hasExpanded = accordionSessions.getPanes().stream().anyMatch(TitledPane::isExpanded);
			if (expandedProperty.get() && !hasExpanded && old != null) {
				Platform.runLater(() -> accordionSessions.setExpandedPane(old));
			}
		});*/

//		accordionSessions.setExpandedPane(accordionSessions.getPanes().get(0));


	}
	void loadSessions(){
		accordionSessions.getPanes().clear();
		HandleReq.getSessionsL().thenAccept((handlesInfoMap)->  Platform.runLater(() ->
				handlesInfoMap.forEach((key, value) -> {
			if (value != null) {

				try {
					ObjectMapper Obj = new ObjectMapper();
					String jsonStr = Obj.writeValueAsString(LAST_HANDLESINFO_MAP.get(key));
					TitledPane scrollableTextBlock = getTitledPane("Session - " + key + " -" +LAST_HANDLESINFO_MAP.get(key).getPlugin_specific().getUsername() ,jsonStr);
					accordionSessions.getPanes().add(scrollableTextBlock);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}

		}))).exceptionally(ex->{
			logger.severe("Exception "+ ex.getMessage());
			return null;
		});
	}

	@NotNull
	private TitledPane getTitledPane (String title,String json) {
		JSONObject jsonObject = new JSONObject(json);
	var textFlow = new TextFlow(new Text(jsonObject.toString(8)));

		textFlow.setPadding(new Insets(0, 10, 0, 0));
		var scrollTextBlockContent = new ScrollPane(textFlow);
		scrollTextBlockContent.setMinHeight(900);
		scrollTextBlockContent.setFitToWidth(true);
		var scrollableTextBlock = new TitledPane("_"+title, scrollTextBlockContent);
		scrollableTextBlock.setMnemonicParsing(true);
		scrollableTextBlock.animatedProperty().bind(animatedProperty);
		scrollableTextBlock.setCollapsible(true);
		scrollableTextBlock.setExpanded(false);
		return scrollableTextBlock;
	}
}
