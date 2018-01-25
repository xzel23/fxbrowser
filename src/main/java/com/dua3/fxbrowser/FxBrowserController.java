package com.dua3.fxbrowser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class FxBrowserController {

    private static final Logger LOG = LogManager.getLogger(FxBrowser.class);

    @FXML Button btnPrev;
    @FXML Button btnNext;
    @FXML Button btnStopReload;
    @FXML TextField inputURL;
    @FXML WebView webview;

    public void go(String location) {
        LOG.info("new location: {}", location);
        webview.getEngine().load(location);
    }

    public void navigateBack() {
        LOG.info("navigate back");
        WebHistory history = webview.getEngine().getHistory();
        if (history.getCurrentIndex()>0) {
            history.go(-1);
        }
    }

    public void navigateForward() {
        LOG.info("navigate forward");
        WebHistory history = webview.getEngine().getHistory();
        if (history.getCurrentIndex()<history.getEntries().size()-1) {
            history.go(1);
        }
    }

    public void reload() {
        LOG.info("reload");
        webview.getEngine().reload();
    }
}
