package africa.jopen.models.forms.janusconfig;

import africa.jopen.utils.ConstantReference;
import africa.jopen.utils.UtilSampleBlock;
import africa.jopen.utils.XUtils;
import atlantafx.base.theme.Styles;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

public class JanusModel {
	private final ExecutorService executor;
	Logger logger = Logger.getLogger(JanusModel.class.getName());
	private static final int COMBO_BOX_WIDTH = 150;
	private static final int H_GAP           = 20;

	private final BooleanProperty animatedProperty = new SimpleBooleanProperty(true);
	private       String          jsonJanus        = "";

	public JanusModel (String json, ExecutorService executor) {
		jsonJanus = json;
		this.executor = executor;
	}

	public Accordion getFormInstance () {
		return createForm();
	}

	private JSONObject obj;
	public  String     jcfg = "";

	/**
	 * Creates a new form instance with the required information.
	 */
	private Accordion createForm () {
		final List<TitledPane> sectionsList = new ArrayList<>();
		if(jsonJanus.isEmpty()) {
			return new Accordion();

		}
		JSONObject             data         = new JSONObject(jsonJanus);
		obj = data.getJSONObject("data");
		if (data.has("jcfg")) {
			jcfg = data.getString("jcfg");
		}

		for (String sectionName : obj.keySet()) {
			sectionsList.add(builderFormSection(obj.getJSONObject(sectionName), sectionName));
		}
		TitledPane[] myArray = new TitledPane[sectionsList.size()];
		sectionsList.toArray(myArray);
		return new Accordion(
				sectionsList.toArray(myArray)
		);
	}

	public JSONObject getCurrentObject () {
		return obj;
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
			if (objStage.get(ConstantReference.JSON_LINE_VALUE) instanceof String) {
				var lineValue = objStage.getString(ConstantReference.JSON_LINE_VALUE);
				if (lineValue.equals("true") || lineValue.equals("false")) {
					var id = sectionName + "@" + target;
					var comboBoxChangeListener = new ChangeListener() {
						@Override
						public void changed (ObservableValue options, Object oldValue, Object newValue) {
							execute(() -> {
								//logger.info(MessageFormat.format("{0} selected", newValue));
								var sectionObject = obj.getJSONObject(sectionName);
								sectionObject.getJSONObject(target).put(ConstantReference.JSON_LINE_VALUE, newValue + "");
							});
						}
					};

					var comboBox = new ComboBox<String>();
					comboBox.getSelectionModel().selectedItemProperty().addListener(comboBoxChangeListener);
					comboBox.getItems().setAll("true", "false");
					if (lineValue.equals("true")) {
						comboBox.getSelectionModel().select(0);
					} else {
						comboBox.getSelectionModel().select(1);
					}
					comboBox.setId(id);
					comboBox.setPrefWidth(COMBO_BOX_WIDTH);
					var basicBlock = new UtilSampleBlock(target, comboBox);
					basicBlock.getRoot().setId(sectionName + "$" + target);
					h.getChildren().add(basicBlock.getRoot());

				} else {
					var id = sectionName + "@" + target;
					ChangeListener<String> inputTextChangeListener = (observable, oldValue, newValue) -> {
						execute(() -> {
							//logger.info("textfield changed from " + oldValue + " to " + newValue);
							var sectionObject = obj.getJSONObject(sectionName);
							sectionObject.getJSONObject(target).put(ConstantReference.JSON_LINE_VALUE, newValue + "");
						});
					};
					var basicField = new TextField(lineValue);
					basicField.textProperty().addListener(inputTextChangeListener);

					basicField.setId(id);
					var basicBlock = new UtilSampleBlock(target, basicField);
					basicBlock.getRoot().setId(sectionName + "$" + target);
					h.getChildren().add(basicBlock.getRoot());
				}

			} else if (objStage.get(ConstantReference.JSON_LINE_VALUE) instanceof JSONArray) {
				var           lineValue = objStage.getJSONArray(ConstantReference.JSON_LINE_VALUE);
				var           id        = sectionName + "@" + target;
				StringBuilder builder   = new StringBuilder();
				for (int i = 0; i < lineValue.length(); i++) {
					String line = lineValue.getString(i);
					builder.append(line).append(",");
				}
				ChangeListener<String> inputTextChangeListener = (observable, oldValue, newValue) -> {
					//logger.info("textfield changed from " + oldValue + " to " + newValue);
					execute(() -> {
						var sectionObject = obj.getJSONObject(sectionName);
						//:ToDo may need more validation if the split s valid as much
						sectionObject.getJSONObject(target).put(ConstantReference.JSON_LINE_VALUE, new JSONArray(newValue.split(",")));
					});

				};
				builder.deleteCharAt(builder.length() - 1);// remove the last comma
				var leftText = new TextField(builder.toString());
				leftText.textProperty().addListener(inputTextChangeListener);
				leftText.setId(id);
				leftText.getStyleClass().add(Styles.LEFT_PILL);
				var box = new HBox(leftText);
				box.setAlignment(Pos.CENTER_LEFT);
				var basicBlock = new UtilSampleBlock(target, box);
				basicBlock.getRoot().setId(sectionName + "$" + target);
				h.getChildren().add(basicBlock.getRoot());
			}
			var id = sectionName + "#" + target;
			ChangeListener<Boolean> checkBoxListener = (ov, oldVal, newVal) -> {
				execute(() -> {
					//logger.info("changed: " + newVal + " from value" + oldVal);
					var sectionObject = obj.getJSONObject(sectionName);
					sectionObject.getJSONObject(target).put("commented", newVal);
				});
			};
			CheckBox basicCheck;
			basicCheck = new CheckBox("Disable");
			basicCheck.setMnemonicParsing(true);
			basicCheck.setSelected(commented);
			basicCheck.selectedProperty().addListener(checkBoxListener);
			basicCheck.setDisable(required);
			basicCheck.setId(id);
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

	private void executeAndWait (Runnable runnable) {
		try {
			executor.submit(runnable).get();
		} catch (Exception e) {
			logger.severe("Execute task failed " + e);
		}
	}

	private void execute (Runnable runnable) {
		try {
			executor.submit(runnable);
		} catch (Exception e) {
			logger.severe("Execute task failed " + e);
		}
	}

	private <T> T executeAndGet (Callable<T> callable) {
		try {
			return executor.submit(callable).get();
		} catch (Exception e) {
			logger.severe("Execute task failed " + e);
		}

		return null;
	}


}
