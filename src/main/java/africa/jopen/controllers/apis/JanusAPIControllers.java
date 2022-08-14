package africa.jopen.controllers.apis;

import africa.jopen.application.BaseApplication;
import africa.jopen.models.forms.janusconfig.JanusModel;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class JanusAPIControllers implements Initializable {

	Logger logger = Logger.getLogger(JanusAPIControllers.class.getName());

	@FXML
	public  BorderPane root_pane;
	private JanusModel model;

	@Override
	public void initialize (URL location, ResourceBundle resources) {
		model = new JanusModel();

		VBox statusContent = new VBox();
		HBox formButtons   = new HBox();
		root_pane.getStyleClass().add("root-pane");
		GridPane   controls      = new GridPane();
		ScrollPane scrollContent = new ScrollPane();
		scrollContent.setContent(model.getFormInstance());
		scrollContent.setFitToWidth(true);
		Button save  = new Button("Save");
		Button reset = new Button("Reset");

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

		save.setOnAction(event -> {
			logger.info("Saving model");

		});

		statusContent.setPadding(new Insets(10));
		statusContent.setSpacing(10);
		statusContent.setPrefWidth(200);
		statusContent.getStyleClass().add("bordered");
		controls.add(statusContent, 0, 1);

		save.setMaxWidth(Double.MAX_VALUE);
		reset.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(save, Priority.ALWAYS);
		HBox.setHgrow(reset, Priority.ALWAYS);
		formButtons.getChildren().addAll(reset, save);
		formButtons.setPadding(new Insets(10));
		formButtons.setSpacing(10);
		formButtons.setPrefWidth(200);
		formButtons.getStyleClass().add("bordered");
		controls.add(formButtons, 0, 2);
		save.setStyle("-fx-background-color: #19a9cb");


		controls.setPrefWidth(200);

		root_pane.setCenter(scrollContent);
		root_pane.setRight(controls);


	}


}
