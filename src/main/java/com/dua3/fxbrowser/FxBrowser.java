package com.dua3.fxbrowser;

import java.net.URL;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxBrowser extends Application {

    static final String APP_NAME = "FxBrowser";

    public static void main(String[] args) {
        System.setProperty("java.net.useSystemProxies", "true");
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
        // create a loader
        FXMLLoader loader = new FXMLLoader(getResource(getClass(), "FxBrowser.fxml"));

        // create the scene
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // setup stage
        stage.setTitle(APP_NAME);
        stage.setScene(scene);
        stage.show();
	}

    private static URL getResource(Class<?> clazz, String resource) {
        return Objects.requireNonNull(clazz.getResource(resource), "Resource not found: "+resource);
    }

}
