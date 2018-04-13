/**
 *
 */
/**
 * @author axel
 *
 */
module com.dua3.fxbrowser {
	exports com.dua3.fxbrowser;
	opens com.dua3.fxbrowser;

    requires java.logging;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.web;

    requires transitive de.jensd.fx.glyphs.fontawesome;
}
