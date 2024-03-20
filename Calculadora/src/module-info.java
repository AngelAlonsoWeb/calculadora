module Calculadora {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires java.scripting;
	requires jep;
	
	opens application to javafx.graphics, javafx.fxml;
}
