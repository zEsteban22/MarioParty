/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.MarioPartyUtils;
import model.SistemaDeJuego;

/**
 * FXML Controller class
 *
 * @author zEstebanCruz
 */
public class ControladorEscogerJugadores implements Initializable {

	@FXML
	private ComboBox<Integer> comboBoxCantJugadores;
	@FXML
	private Button botonListoxd;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		comboBoxCantJugadores.setItems(FXCollections.observableArrayList(2, 3, 4, 5, 6));
		comboBoxCantJugadores.setValue(2);
	}

	@FXML
	private void abrirEscogerPersonajes(ActionEvent event) {
		MarioPartyUtils.cambiarAVentana("VentanaEscogerPersonajes.fxml", (Stage) botonListoxd.getScene().getWindow());
		SistemaDeJuego.cantJugadores = comboBoxCantJugadores.getValue();
	}

}
