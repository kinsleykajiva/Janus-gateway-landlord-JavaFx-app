package africa.jopen.controllers.apis;

import africa.jopen.models.forms.janusconfig.JanusModel;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class JanusAPIControllers implements Initializable {
	private GridPane     controls;
	private HBox         languageButtons;
	private VBox         statusContent;
	private Button       save;
	private Button       reset;
	private HBox         formButtons;
	private ScrollPane   scrollContent;
	//private Button editableToggle;
//	private Button sectionToggle;
	private FormRenderer displayForm;

	@FXML
	public BorderPane root_pane;

	@Override
	public void initialize (URL location, ResourceBundle resources) {
		JanusModel model = new JanusModel();
		displayForm = new FormRenderer(model.getFormInstance());
		languageButtons = new HBox();
		statusContent = new VBox();
		formButtons = new HBox();
		root_pane.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
		root_pane.getStyleClass().add("root-pane");
		controls = new GridPane();
		scrollContent = new ScrollPane();
		scrollContent.setContent(displayForm);
		scrollContent.setFitToWidth(true);
		save = new Button("Save");
		reset = new Button("Reset");
		//save.getStyleClass().add("save-button");
		reset.getStyleClass().add("reset-button");
		scrollContent.getStyleClass().add("scroll-pane");

		save.setStyle(" -fx-font-size: 14px;\n" +
		              "    -fx-font-weight: normal;\n" +
		              "    -fx-text-alignment: center;\n" +
		              "    -fx-cursor: hand;\n" +
		              "    -fx-background-radius: 4px;\n" +
		              "    -fx-background-insets: 0 0 0 0, 0, 1, 2;\n" +
		              "    -fx-border-width: 1px;\n" +
		              "    -fx-border-radius: 4px;");
		reset.setOnAction(event -> model.getFormInstance().reset());
		save.setOnAction(event -> model.getFormInstance().persist());

		statusContent.setPadding(new Insets(10));
		//statusContent.getChildren().addAll(validLabel, changedLabel, persistableLabel);
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
		//controls.getStyleClass().add("controls");

		root_pane.setCenter(scrollContent);
		root_pane.setRight(controls);
		model.sectionsList.forEach(e->{
			e.collapse(true);
		});

//		setCenter(scrollContent);
//		setRight(controls);

	}
}
