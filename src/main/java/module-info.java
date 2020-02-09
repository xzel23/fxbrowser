module fxbrowser {
	exports com.dua3.fxbrowser;
	opens com.dua3.fxbrowser;

    requires java.logging;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.web;

	requires dua3_utility;
	
	requires org.kordamp.iconli.core;
	requires org.kordamp.ikonli.fontawesome;
}
