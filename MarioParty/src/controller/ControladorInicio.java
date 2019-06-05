/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.Tablero;

/**
 * FXML Controller class
 *
 * @author zEstebanCruz
 */
public class ControladorInicio implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@FXML
	private Button boton;

	@FXML
	void holaxd(MouseEvent mouseEvent) {
		Tablero.crearJugadores();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

}
