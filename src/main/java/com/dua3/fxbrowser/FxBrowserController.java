package com.dua3.fxbrowser;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.util.logging.Logger;

public class FxBrowserController {

    private static final Logger LOG = Logger.getLogger(FxBrowserController.class.getName());
    private static final Logger LOG_WEB = Logger.getLogger("webview");

    @FXML
    Button btnPrev;
    @FXML
    Button btnNext;
    @FXML
    Button btnStopReload;
    @FXML
    TextField inputURL;
    @FXML
    WebView webview;
    @FXML
    Circle led;

    // LED fill styles depending on webview loadworker state
    private Paint ledFillSuccess;
    private Paint ledFillWorking;
    private Paint ledFillFailed;
    private Paint ledFillCancelled;

    private Paint createFill(Color color) {
        Bounds bounds = led.getBoundsInLocal();
        double focusAngle = 0;
        double focusDistance = 0;
        double centerX = -bounds.getWidth() / 8;
        double centerY = -bounds.getHeight() / 6;
        double radius = bounds.getWidth() / 2;
        boolean proportional = false;
        CycleMethod cycleMethod = CycleMethod.NO_CYCLE;
        Stop[] stops = {new Stop(0, color.interpolate(Color.WHITE, 0.8)), new Stop(1, color)};
        return new RadialGradient(focusAngle, focusDistance, centerX, centerY, radius, proportional, cycleMethod,
                stops);
    }

    @FXML
    private void initialize() {
        WebEngine engine = webview.getEngine();
        engine.getLoadWorker().exceptionProperty().addListener((ov, t, t1) -> {
            LOG_WEB.warning("loadworker exception - " + t1);
            engine.loadContent(generateErrorHtml(t1));
        });

        engine.onErrorProperty().set(evt -> LOG_WEB.warning(evt.getMessage()));

        // set LED styles
        ledFillSuccess = createFill(Color.DARKGRAY);
        ledFillWorking = createFill(Color.GREEN);
        ledFillFailed = createFill(Color.DARKRED);
        ledFillCancelled = createFill(Color.YELLOW.darker());

        // set LED to default style
        led.setFill(ledFillSuccess);

        engine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
            String location = engine.getLocation();

            LOG.fine("URL[" + location + "] - loadworker state: " + newState + " [" + oldState + "]");

            // try to
            switch (newState) {
                case SUCCEEDED:
                    led.setFill(ledFillSuccess);
                    break;
                case READY:
                    led.setFill(ledFillSuccess);
                    break;
                case FAILED:
                    led.setFill(ledFillFailed);
                    break;
                case SCHEDULED:
                case RUNNING:
                    led.setFill(ledFillWorking);
                    break;
                case CANCELLED:
                    led.setFill(ledFillCancelled);
                    break;
                default:
                    led.setFill(ledFillCancelled);
                    LOG.warning("unhandled loadworker state: " + newState);
            }
        });
    }

    private static String generateErrorHtml(Throwable t1) {
        StringBuilder buffer = new StringBuilder(4096);

        buffer.append("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                <meta charset="utf-8">
                </head><body>
                """);

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
        buffer.append("""
                  </body>
                </html>
                """);

        return buffer.toString();
    }

    public void goToInputUrl() {
        go(inputURL.getText());
    }

    public void go(String location) {
        if (!location.matches(".*://.*")) {
            if (location.startsWith("/") || location.matches("[a-zA-Z]:[/\\\\].*")) {
                location = "file://" + location;
            } else {
                location = "https://" + location;
            }
        }
        LOG.info("new location: " + location);
        webview.getEngine().load(location);
    }

    public void navigateBack() {
        LOG.info("navigate back");
        WebHistory history = webview.getEngine().getHistory();
        if (history.getCurrentIndex() > 0) {
            history.go(-1);
        }
    }

    public void navigateForward() {
        LOG.info("navigate forward");
        WebHistory history = webview.getEngine().getHistory();
        if (history.getCurrentIndex() < history.getEntries().size() - 1) {
            history.go(1);
        }
    }

    public void reload() {
        LOG.info("reload");
        webview.getEngine().reload();
    }
}
