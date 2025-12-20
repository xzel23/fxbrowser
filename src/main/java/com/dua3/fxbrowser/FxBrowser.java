package com.dua3.fxbrowser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxBrowser extends Application {

    static final String APP_NAME = "FxBrowser";

    public static void main(String[] args) {
        System.setProperty("java.net.useSystemProxies", "true");
        System.setProperty("javafx.allowjs", "true");
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // create the controller
        FxBrowserController controller = new FxBrowserController();

        // create the scene
        Scene scene = new Scene(controller.getRoot());

        // setup stage
        stage.setTitle(APP_NAME);
        stage.setScene(scene);
        stage.show();
    }

}
