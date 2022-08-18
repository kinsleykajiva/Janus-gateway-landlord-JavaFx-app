/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package africa.jopen.utils;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class Alerts {
	private static Scene scene;

	public static void initScene (Scene scene) {
		Alerts.scene = scene;

	}
	private Alerts(){}

	public static void warning (String title, String content) {

		var alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.setResizable(false);
		alert.setContentText(content);
		alert.initOwner(scene.getWindow());
		alert.initStyle(StageStyle.UNDECORATED);
		alert.showAndWait();
	}

	public static void confirmation (String title, String content, Runnable okCallBack, Runnable cancelCallBack) {


		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setResizable(false);
		alert.initOwner(scene.getWindow());
		alert.setContentText(content);

		Optional<ButtonType> result = alert.showAndWait();
		ButtonType           button = result.orElse(ButtonType.CANCEL);
		if (button == ButtonType.OK) {
			okCallBack.run();
		} else {
			cancelCallBack.run();

		}

	}



	public static void error (String title, String content) {

		var alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setContentText(content);
		alert.setResizable(false);
		alert.initOwner(scene.getWindow());
		alert.showAndWait();
	}

	@SafeVarargs
	public static void error (String title, String content, EventHandler<MouseEvent>... confirm) {
		Dialog.createAlert(Dialog.Type.ERROR, title, content, confirm);
	}

	public static void info (String title, String content) {
		var alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.setResizable(false);
		alert.initOwner(scene.getWindow());
		alert.initStyle(StageStyle.UNDECORATED);
		alert.showAndWait();
	}



	public static void success (String title, String content) {
		var alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setResizable(false);
		alert.setContentText(content);
		alert.initOwner(scene.getWindow());
		alert.initStyle(StageStyle.UNDECORATED);
		alert.showAndWait();
	}


}
