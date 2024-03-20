package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.nfunk.jep.JEP;

import javafx.event.ActionEvent;

import javafx.scene.input.KeyEvent;

public class calculadoraController {
	@FXML
	private Label labelResultado;
	@FXML
	private TextField fieldPantalla;
	@FXML
	private Button btnAC;
	@FXML
	private Button btnDEL;
	@FXML
	private Button btn7;
	@FXML
	private Button btn8;
	@FXML
	private Button btn4;
	@FXML
	private Button btnPunto;
	@FXML
	private Button btn0;
	@FXML
	private Button btnSuma;
	@FXML
	private Button btnResta;
	@FXML
	private Button btn3;
	@FXML
	private Button btn6;
	@FXML
	private Button btn5;
	@FXML
	private Button btn9;
	@FXML
	private Button btnDivi;
	@FXML
	private Button btn1;
	@FXML
	private Button btn2;
	@FXML
	private Button btnMulti;
	@FXML
	private Button btnParentesisAbierto;
	@FXML
	private Button btnPorcentaje;
	@FXML
	private Button btnParentesisCerrado;
	@FXML
	private Button btnCerrar;
	@FXML
	private Button btnRaiz;
	@FXML
	private Button btnPotencia;
	@FXML
	private Button btnLogaritmo;
	@FXML
	private Button btnIgual;
	double resultado = 0;

	@FXML
	public void ingresarDatosDesdeTeclado(KeyEvent event) {
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
	}

	@FXML
	public void funcionAC(ActionEvent event) {
		if (!fieldPantallaisEmpty()) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Borrar todo");
			alert.setHeaderText("¿Estás seguro de que deseas borrar todo?");
			ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

			if (result == ButtonType.OK) {
				fieldPantalla.setText("");
			}
		}
		labelResultado.setText("");
		resultado = 0;
	}

	@FXML
	public void funcionDEL(ActionEvent event) {
		int tamañoLetrasEnPantalla = fieldPantalla.getText().length();
		if (!fieldPantallaisEmpty()) {
			fieldPantalla.setText(fieldPantalla.getText().substring(0, tamañoLetrasEnPantalla - 1));
			labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
		}
	}

	@FXML
	public void funcionNumerico(ActionEvent event) {
		Button button = (Button) event.getSource();
		String buttonText = button.getText();
		fieldPantalla.setText(fieldPantalla.getText() + buttonText);
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
	}

	@FXML
	public void funcionPunto(ActionEvent event) {
		String pantallaTexto = fieldPantalla.getText();
		fieldPantalla.setText(pantallaTexto + ".");
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
	}

	@FXML
	public void funcionSuma(ActionEvent event) {
		String pantallaTexto = fieldPantalla.getText();
		fieldPantalla.setText(pantallaTexto + "+");
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));

	}

	@FXML
	public void funcionResta(ActionEvent event) {
		String pantallaTexto = fieldPantalla.getText();
		fieldPantalla.setText(pantallaTexto + "-");
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
	}

	@FXML
	public void funcionDivi(ActionEvent event) {
		String pantallaTexto = fieldPantalla.getText();
		fieldPantalla.setText(pantallaTexto + "/");
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
	}

	@FXML
	public void funcionMulti(ActionEvent event) {
		String pantallaTexto = fieldPantalla.getText();
		fieldPantalla.setText(pantallaTexto + "*");
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
	}

	@FXML
	public void funcionRaiz(ActionEvent event) {
		String pantallaTexto = fieldPantalla.getText();
		fieldPantalla.setText(pantallaTexto + "sqrt(");
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
	}

	@FXML
	public void funcionPotencia(ActionEvent event) {
		String pantallaTexto = fieldPantalla.getText();
		fieldPantalla.setText(pantallaTexto + "^");
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
	}

	@FXML
	public void funcionLogaritmo(ActionEvent event) {
		String pantallaTexto = fieldPantalla.getText();
		fieldPantalla.setText(pantallaTexto + "log(");
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
	}

	@FXML
	public void funcionigual(ActionEvent event) {
		verificarSiEsUnNumero();
	}

	@FXML
	public void funcionParentesisAbierto(ActionEvent event) {
		String pantallaTexto = fieldPantalla.getText();
		fieldPantalla.setText(pantallaTexto + "(");
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
	}

	@FXML
	public void funcionParentesisCerrado(ActionEvent event) {
		String pantallaTexto = fieldPantalla.getText();
		fieldPantalla.setText(pantallaTexto + ")");
		labelResultado.setText(Double.toString(evaluarExpresion(fieldPantalla.getText())));
	}

	@FXML
	public void funcionCerrar(ActionEvent event) {
		if (fieldPantalla.getText().isEmpty()) {
			javafx.application.Platform.exit();
		} else {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Confirmar cierre");
			alert.setHeaderText("¿Estás seguro de que deseas cerrar la ventana?");
			alert.setContentText("Si cierras la ventana, perderás todos los cambios no guardados.");

			ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

			if (result == ButtonType.OK) {
				javafx.application.Platform.exit();

			}
		}

	}
	public void verificarSiEsUnNumero() {
		String resultado = labelResultado.getText();
		if(resultado.equals("NaN") || resultado.equals("Infinity")){
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Advertencia");
			alerta.setHeaderText("Error");
			alerta.setContentText("Para usar el resultado, el valor debe ser numérico");
			alerta.showAndWait();
		}else {
			fieldPantalla.setText(resultado);
		}
	}
	public boolean fieldPantallaisEmpty() {
		if (fieldPantalla.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public double evaluarExpresion(String expresion) {
		JEP jep = new JEP();

		try {
			jep.addStandardFunctions();
			jep.addStandardConstants();
			jep.parseExpression(expresion);
			double result = jep.getValue();
			return result;
		} catch (IllegalArgumentException e) {
			labelResultado.setText("Expresión no valida");
			throw new IllegalArgumentException("Error evaluating expression: " + jep.getErrorInfo());
		}
	}
}
