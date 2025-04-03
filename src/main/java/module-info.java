module africa.jopen.landlord {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.slf4j;
    requires jasypt;
    requires eventbus.java;

    opens africa.jopen.landlord.app to javafx.fxml;
    exports africa.jopen.landlord.app;
}