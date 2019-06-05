/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author zEstebanCruz
 */
public class ControladorRifarPrimerJugador implements Initializable {

	@FXML
	private ImageView imagenPersonajeActual;
	@FXML
	private Label labelNumeroGenerado;
	@FXML
	private Label labelNumeroAObtener;
	@FXML
	private Button botonGenerarAleatorio;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void gernerarAleatorio(ActionEvent event) {
	}

}
