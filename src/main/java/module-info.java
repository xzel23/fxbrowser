module fxbrowser {
    exports com.dua3.fxbrowser;
    opens com.dua3.fxbrowser;

    requires java.logging;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;

    requires com.dua3.utility;
    requires com.dua3.utility.fx;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.fontawesome;
}
