package com.dua3.fxbrowser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class FxBrowserController {

    private static final Logger LOG = LogManager.getLogger(FxBrowser.class);
    private static final Logger LOG_WEB = LogManager.getLogger("webview");

    @FXML Button btnPrev;
    @FXML Button btnNext;
    @FXML Button btnStopReload;
    @FXML TextField inputURL;
    @FXML WebView webview;

    @FXML private void initialize() {
        WebEngine engine = webview.getEngine();
        engine .getLoadWorker().exceptionProperty().addListener((ChangeListener<Throwable>) (ov, t, t1) -> {
            LOG_WEB.warn("loadworker exception - "+String.valueOf(t1), t1);
            engine.loadContent(generateErrorHtml(t1));
        });

        engine.onErrorProperty().set(evt -> {
            LOG_WEB.warn(evt.getMessage());
        });
    }

    private String generateErrorHtml(Throwable t1) {
        StringBuilder buffer = new StringBuilder(4096);

        buffer.append("<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "<meta charset=\"utf-8\">\n"
                + "</head>" + "<body>\n");

        // message
        buffer.append("<h1>Error</h1>\n");
        buffer.append("<p>");
        buffer.append(t1 != null ? t1.getMessage() : "(null)");
        buffer.append("</p>\n");

        // stack trace
        if (Boolean.getBoolean("debug") && t1 != null) {
            buffer.append("<h2>Stacktrace</h2>");
            buffer.append("<pre><code>");
            for (StackTraceElement line : t1.getStackTrace()) {
                buffer.append(line.toString()).append("\n");
            }
            buffer.append("</code></pre>\n");
        }
        buffer.append("  </body>\n" + "</html>\n");

        return buffer.toString();
    }

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
