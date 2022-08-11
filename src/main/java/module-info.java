module africa.jopen {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.media;

    requires com.jfoenix;
    requires de.jensd.fx.glyphs.fontawesome;
    requires eventbus.java;
    requires MaterialFX;
    requires FXTrayIcon;
    requires org.json;
    requires jasypt;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.feather;
    requires java.net.http;
    requires org.jetbrains.annotations;
    requires java.logging;


    opens africa.jopen.application to javafx.fxml,javafx.graphics;
    opens africa.jopen.controllers to javafx.fxml, javafx.graphics;
    opens africa.jopen.utils to javafx.fxml, javafx.graphics;
    opens africa.jopen.libs.decorator to javafx.fxml, javafx.graphics;
    exports africa.jopen.controllers.home to javafx.fxml;

}