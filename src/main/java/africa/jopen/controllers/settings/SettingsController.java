package africa.jopen.controllers.settings;

import africa.jopen.application.BaseApplication;
import atlantafx.base.theme.Styles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE;

public class SettingsController implements Initializable {
	Logger logger = Logger.getLogger( SettingsController.class.getName() );
	@FXML public BorderPane rootPane;
	@FXML public TabPane tabPane;
	@FXML public Tab tabRemoteWebApp,tabSystem;
	private static final double TAB_MIN_HEIGHT = 60;
	private SystemsTabContent systemsTabContent = new SystemsTabContent();
	@Override
	public void initialize (URL location, ResourceBundle resources) {

		// var tabs = tabPane();
		// rootPane.setCenter(tabs);
		Styles.toggleStyleClass(tabPane, TabPane.STYLE_CLASS_FLOATING);
		tabPane.tabMinWidthProperty().unbind();

		tabPane.tabMinWidthProperty().bind(rootPane.widthProperty()
				.subtract(18) // .control-buttons-tab width
				.divide(tabPane.getTabs().size())
				.subtract(28) // .tab paddings
		);
		// tabs.setTabMinWidth(Region.USE_PREF_SIZE);
		tabSystem.setGraphic(new FontIcon(Feather.SETTINGS));
		tabRemoteWebApp.setGraphic(new FontIcon(Feather.UPLOAD_CLOUD));
		tabPane.setTabClosingPolicy(UNAVAILABLE);
		tabPane.setMinHeight(TAB_MIN_HEIGHT);

		tabSystem.setContent(systemsTabContent.content);

	}
	private TabPane tabPane() {
		var tabs = new TabPane();



		// NOTE: Individually disabled tab is still closeable even while it looks
		//       like disabled. To prevent it from closing one can use "black hole"
		//       event handler. #javafx-bug
		tabs.getTabs().addAll(
				SystemTab(),
				WebAppTab()
		);

		return tabs;
	}
	private Tab SystemTab() {
		var tab = new Tab("System");

		return tab;

	}
	private Tab WebAppTab() {
		var tab = new Tab("Remote Web App");

		return tab;

	}
}
