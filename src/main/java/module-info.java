module africa.jopen.landlord {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires jasypt;
    requires eventbus.java;
    requires org.java_websocket;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires jakarta.annotation;

    opens africa.jopen.landlord.app to javafx.fxml;
    exports africa.jopen.landlord.app;
}