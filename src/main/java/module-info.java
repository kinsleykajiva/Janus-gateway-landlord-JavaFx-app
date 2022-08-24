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
	requires com.fasterxml.jackson.databind;
	requires atlantafx.base;


	requires com.google.gson;
	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires org.apache.commons.lang3;
	requires animated;
	requires AnimateFX;
	requires commons.exec;
	requires org.slf4j;
	requires okhttp3;
	requires okio;


	requires socket.io.client;
	requires engine.io.client;


	opens africa.jopen.application to javafx.fxml, javafx.graphics;
	opens africa.jopen.controllers to javafx.fxml, javafx.graphics;
	opens africa.jopen.utils to javafx.fxml, javafx.graphics;
	opens africa.jopen.controllers.janus to eventbus.java;
	opens africa.jopen.controllers.apis to eventbus.java;

	exports africa.jopen.controllers.home to javafx.fxml;
	exports africa.jopen.models.admin.handles to com.fasterxml.jackson.databind;
	exports africa.jopen.models.forms.janusconfig.janus to com.fasterxml.jackson.databind;
	exports africa.jopen.controllers.janus to javafx.fxml;
	exports africa.jopen.controllers.apis to javafx.fxml, eventbus.java;
	exports africa.jopen.controllers.settings to javafx.fxml;
	exports africa.jopen.controllers.auth to javafx.fxml;
	exports africa.jopen.application to eventbus.java;
	exports africa.jopen.controllers to eventbus.java;
	exports africa.jopen.events;
	exports africa.jopen.models;
}