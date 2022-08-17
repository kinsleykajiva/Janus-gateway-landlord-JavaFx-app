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
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  30/01/2019
 */
public class Alerts {
   private static Scene scene;
    public static void initScene(Scene scene) {
        Alerts.scene =scene;

    }

    public static void warning(String title, String content){

        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.initOwner(scene.getWindow());
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }

   /* @SafeVarargs
    public static void warning(String title, String content, EventHandler<MouseEvent>... confirm){
        Dialog.createAlert(Dialog.Type.WARNING, title, content, confirm);
    }*/

    public static void error(String title, String content){

        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText(content);
        alert.initOwner(scene.getWindow());
        alert.showAndWait();
    }

    @SafeVarargs
    public static void error(String title, String content, EventHandler<MouseEvent>... confirm){
        Dialog.createAlert(Dialog.Type.ERROR, title, content, confirm);
    }

    public static void info(String title, String content){
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.initOwner(scene.getWindow());
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }

  //  @SafeVarargs
    /*public static void info(String title, String content, EventHandler<MouseEvent>... confirm){
        Dialog.createAlert(Dialog.Type.INFO, title, content, confirm);
    }*/

    public static void success(String title, String content){
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.initOwner(scene.getWindow());
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }

   /* @SafeVarargs
    public static void success(String title, String content, EventHandler<MouseEvent>... confirm){
        Dialog.createAlert(Dialog.Type.SUCCESS, title, content, confirm);
    }*/
}
