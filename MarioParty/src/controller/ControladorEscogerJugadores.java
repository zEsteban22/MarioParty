/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.MarioPartyUtils;
import model.SistemaDeJuego;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zEstebanCruz
 */
public class ControladorEscogerJugadores implements Initializable {

	private AudioClip jugadoresTheme;

	@FXML
	private ComboBox<Integer> comboBoxCantJugadores;
	@FXML
	private Button botonListoxd;

	@FXML
	void listoPresionado(MouseEvent mouseEvent) throws IOException {
		jugadoresTheme.stop();
		Parent siguiente = FXMLLoader.load(ClassLoader
			.getSystemClassLoader().getResource("view/VentanaEscogerPersonajes.fxml"));
		Scene personajes = new Scene(siguiente);
		Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
		window.setScene(personajes);
		window.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		comboBoxCantJugadores.setItems(FXCollections.observableArrayList(2, 3, 4, 5, 6));
		comboBoxCantJugadores.setValue(2);
		jugadoresTheme = new AudioClip(ClassLoader.getSystemClassLoader()
			.getResource("resources/Jugadores_Theme.wav").toExternalForm());
		jugadoresTheme.play();
	}

	@FXML
	private void abrirEscogerPersonajes(ActionEvent event) {
		MarioPartyUtils.cambiarAVentana("VentanaEscogerPersonajes.fxml", (Stage) botonListoxd.getScene().getWindow());
		SistemaDeJuego.cantJugadores = comboBoxCantJugadores.getValue();
	}

}
