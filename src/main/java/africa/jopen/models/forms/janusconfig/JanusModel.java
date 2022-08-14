package africa.jopen.models.forms.janusconfig;

import africa.jopen.models.forms.janusconfig.janus.JanusObject;
import africa.jopen.utils.ConstantReference;
import africa.jopen.utils.UtilSampleBlock;
import africa.jopen.utils.XUtils;
import atlantafx.base.theme.Styles;
import com.dlsc.formsfx.model.structure.Form;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JanusModel {
	Logger logger = Logger.getLogger(JanusModel.class.getName());
	private static final int    COMBO_BOX_WIDTH = 150;
	private static final int    H_GAP           = 20;

	private final BooleanProperty animatedProperty = new SimpleBooleanProperty(true);
	private         String jsonJanus       = "";

	public JanusModel (String json) {
		jsonJanus = json;
	}

	public Accordion getFormInstance () {
		return createForm();
	}

	/**
	 * Creates a new form instance with the required information.
	 */
	private Accordion createForm () {
		final List<TitledPane> sectionsList = new ArrayList<>();
		JSONObject             data         = new JSONObject(jsonJanus);
		JSONObject             obj          = data.getJSONObject("data");

		for (String sectionName : obj.keySet()) {
			sectionsList.add(builderFormSection(obj.getJSONObject(sectionName), sectionName));
		}
		TitledPane[] myArray = new TitledPane[sectionsList.size()];
		sectionsList.toArray(myArray);
		return new Accordion(
				sectionsList.toArray(myArray)
		);
	}


	@SuppressWarnings ("unchecked")
	private TitledPane builderFormSection (JSONObject section, String sectionName) {
		List<Node> elements = new ArrayList<>();
		var        v        = new VBox();
		for (String target : section.keySet()) {
			var objStage  = section.getJSONObject(target);
			var commented = objStage.getBoolean("commented");
			var required  = objStage.getBoolean("required");

			var h = new HBox(H_GAP);
			if (objStage.get(ConstantReference.JSON_LINE_VALUE ) instanceof String) {
				var lineValue = objStage.getString(ConstantReference.JSON_LINE_VALUE);
				if (lineValue.equals("true") || lineValue.equals("false")) {
					var comboBoxChangeListener = new ChangeListener() {
						@Override
						public void changed (ObservableValue options, Object oldValue, Object newValue) {

							logger.fine(MessageFormat.format("{0} selected", newValue));
						}
					};
					var comboBox = new ComboBox<String>();
					comboBox.getSelectionModel().selectedItemProperty().addListener(comboBoxChangeListener);
					comboBox.getItems().setAll("true", "false");
					comboBox.getSelectionModel().selectFirst();
					comboBox.setId(sectionName + "@" + target);
					comboBox.setPrefWidth(COMBO_BOX_WIDTH);
					var basicBlock = new UtilSampleBlock(target, comboBox);
					basicBlock.getRoot().setId(sectionName + "$" + target);
					h.getChildren().add(basicBlock.getRoot());

				} else {
					ChangeListener<String> inputTextChangeListener = (observable, oldValue, newValue) -> logger.info("textfield changed from " + oldValue + " to " + newValue);
					var                    basicField              = new TextField(lineValue);
					basicField.textProperty().addListener(inputTextChangeListener);
					basicField.setId(sectionName + "@" + target);
					var basicBlock = new UtilSampleBlock(target, basicField);
					basicBlock.getRoot().setId(sectionName + "$" + target);
					h.getChildren().add(basicBlock.getRoot());
				}

			} else if (objStage.get(ConstantReference.JSON_LINE_VALUE) instanceof JSONArray) {
				var           lineValue = objStage.getJSONArray(ConstantReference.JSON_LINE_VALUE);
				StringBuilder builder   = new StringBuilder();
				for (int i = 0; i < lineValue.length(); i++) {
					String line = lineValue.getString(i);
					builder.append(line).append(",");
				}
				ChangeListener<String> inputTextChangeListener = (observable, oldValue, newValue) -> logger.info("textfield changed from " + oldValue + " to " + newValue);
				builder.deleteCharAt(builder.length() - 1);// remove the last comma
				var leftText = new TextField(builder.toString());
				leftText.textProperty().addListener(inputTextChangeListener);
				leftText.setId(sectionName + "@" + target);
				leftText.getStyleClass().add(Styles.LEFT_PILL);
				var box = new HBox(leftText);
				box.setAlignment(Pos.CENTER_LEFT);
				var basicBlock = new UtilSampleBlock(target, box);
				basicBlock.getRoot().setId(sectionName + "$" + target);
				h.getChildren().add(basicBlock.getRoot());
			}
			ChangeListener<Boolean> checkBoxListener = (ov, oldVal, newVal) -> logger.info("changed: " + newVal + " from value" + oldVal);
			CheckBox                basicCheck;
			basicCheck = new CheckBox("Comment/Disable");
			basicCheck.setMnemonicParsing(true);
			basicCheck.setSelected(commented);
			basicCheck.selectedProperty().addListener(checkBoxListener);
			basicCheck.setDisable(required);
			basicCheck.setId(sectionName + "#" + target);
			var basicBlock = new UtilSampleBlock("", basicCheck);
			basicBlock.getRoot().setId(sectionName + "$" + target);
			h.getChildren().add(basicBlock.getRoot());
			v.getChildren().add(h);
			elements.add(h);
		}

		var box     = new VBox(H_GAP * 2);
		var batches = XUtils.getBatches(elements, 2);
		batches.forEach(batch -> {
			var rowBox = new HBox(H_GAP);
			rowBox.setSpacing(H_GAP * 10);
			rowBox.setAlignment(Pos.CENTER);
			rowBox.getChildren().addAll(batch);
			box.getChildren().add(rowBox);
			if (batch.size() > 1) {
				box.getChildren().add(new Separator()); //this is just a hr line or horizontal line
			}
		});
		var scrollTextBlockContent = new ScrollPane(box);
		scrollTextBlockContent.setMinHeight(200);
		scrollTextBlockContent.setFitToWidth(true);
		var scrollableTextBlock = new TitledPane("_" + StringUtils.capitalize(sectionName), scrollTextBlockContent);
		scrollableTextBlock.setMnemonicParsing(true);
		scrollableTextBlock.animatedProperty().bind(animatedProperty);
		return scrollableTextBlock;
	}




}
