module africa.jopen {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.media;

    opens africa.jopen to javafx.fxml;
}