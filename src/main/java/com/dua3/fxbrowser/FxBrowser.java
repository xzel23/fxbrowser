package com.dua3.fxbrowser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxBrowser extends Application {

    private static final Logger LOG = LogManager.getLogger(FxBrowser.class);

    static final String APP_NAME = "FxBrowser";

    public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
        // create a loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FxBrowser.fxml"));

        // create the scene
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // setup stage
        stage.setTitle(APP_NAME);
        stage.setScene(scene);
        stage.show();
	}

}
