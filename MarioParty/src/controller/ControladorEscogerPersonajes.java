/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.media.AudioClip;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.MarioPartyUtils;
import model.SistemaDeJuego;

/**
 * FXML Controller class
 *
 * @author zEstebanCruz
 */
public class ControladorEscogerPersonajes implements Initializable {

	private AudioClip personajesTheme;

	@FXML
	private Label labelNumeroJugador;
	@FXML
	private Label labelFicha1;
	@FXML
	private Label labelFicha2;
	@FXML
	private Label labelFicha3;
	@FXML
	private Label labelFicha4;
	@FXML
	private Label labelFicha5;
	@FXML
	private Label labelFicha6;
	@FXML
	private Label labelFicha7;
	@FXML
	private Label labelFicha8;
	@FXML
	private Label labelFicha9;
	@FXML
	private Label labelFichaA;
	@FXML
	private Label labelFichaB;
	@FXML
	private Label labelFichaC;
	@FXML
	private Label labelFichaD;
	@FXML
	private Label labelFichaE;
	@FXML
	private Label labelFichaF;
	@FXML
	private Button botonAleatorio;

	private Map<Label, String> restantes;
	private int jugadorActual = 0;

	public ControladorEscogerPersonajes() {
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		personajesTheme = new AudioClip(ClassLoader.getSystemClassLoader()
			.getResource("resources/Jugadores_Theme.wav").toExternalForm());
		personajesTheme.setCycleCount(Integer.MAX_VALUE);
		personajesTheme.play();

		restantes = new HashMap<>();
		restantes.put(labelFicha1, "daisy");
		restantes.put(labelFicha2, "luigi");
		restantes.put(labelFicha3, "mario");
		restantes.put(labelFicha4, "peach");
		restantes.put(labelFicha5, "toad");
		restantes.put(labelFicha6, "birdo");
		restantes.put(labelFicha7, "yoshi");
		restantes.put(labelFicha8, "donkey_kong");
		restantes.put(labelFicha9, "waluigi");
		restantes.put(labelFichaA, "wario");
		restantes.put(labelFichaB, "bowser");
		restantes.put(labelFichaC, "diddy_kong");
		restantes.put(labelFichaD, "goomba");
		restantes.put(labelFichaE, "king_boo");
		restantes.put(labelFichaF, "bowsy");
	}

	@FXML
	private void escogerFicha(MouseEvent event) {
		Label label = (Label) event.getSource();
		escogerFicha(label);
	}

	private void escogerFicha(Label label) {
		SistemaDeJuego.aniadirFicha(restantes.get(label));
		label.setDisable(true);
		label.setBackground(new Background(new BackgroundFill(Color.web("#ff0000"), CornerRadii.EMPTY,
																													Insets.EMPTY)));
		restantes.remove(label);
		if (++jugadorActual == SistemaDeJuego.cantJugadores)
			abrirVentanaRifarPrimerJugador();
		labelNumeroJugador.setText(Integer.toString(jugadorActual + 1));
	}

	private void abrirVentanaRifarPrimerJugador() {
		MarioPartyUtils.cambiarAVentana("VentanaRifarPrimerJugador.fxml", (Stage) labelNumeroJugador.getScene().getWindow());
	}

	@FXML
	private void aleatorioActual(ActionEvent event) {
		int numero = new Random().nextInt(restantes.size());
		escogerFicha((Label) restantes.keySet().toArray()[numero]);
	}

}
