/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static java.lang.Math.abs;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.MarioPartyUtils;
import model.SistemaDeJuego;

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
	private Random random;
	private Map<String, Integer> resultados;

	private String getRandom() {
		return Integer.toString(new Random().nextInt(1000));
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		random = new Random();
		resultados = new HashMap<>(SistemaDeJuego.cantJugadores);
		imagenPersonajeActual.setImage(new Image(MarioPartyUtils.urlPersonajeActual().toString(), true));
		labelNumeroGenerado.setText(getRandom());
		labelNumeroAObtener.setText(getRandom());
	}

	@FXML
	private void generarAleatorio(ActionEvent event) {
		botonGenerarAleatorio.setDisable(true);
		int numero = Integer.parseInt(getRandom());
		int i = Integer.parseInt(labelNumeroGenerado.getText());
		resultados.put(SistemaDeJuego.getPersonajeActual(), numero);
		SistemaDeJuego.siguienteTurno();
		animarCambio(i, numero);
	}

	private void terminarRifa() {
		ArrayList<String> ordenJugadores = new ArrayList<>();
		Entry<String, Integer> min;
		int n = Integer.parseInt(labelNumeroAObtener.getText());
		Comparator<Entry<String, Integer>> c = (e1, e2) -> abs(e1.getValue() - n) - abs(e2.getValue() - n);
		while (!resultados.isEmpty()) {
			min = Collections.min(resultados.entrySet(), c);
			ordenJugadores.add(min.getKey());
			resultados.remove(min.getKey());
		}
		SistemaDeJuego.setJugadores(ordenJugadores);
		abrirVentanaDeJuego();
	}

	private void terminarAnimacion() {
		imagenPersonajeActual.setImage(new Image(MarioPartyUtils.urlPersonajeActual().toString(), true));
		botonGenerarAleatorio.setDisable(false);
		if (resultados.size() == SistemaDeJuego.cantJugadores)
			terminarRifa();
	}

	private void animarCambio(final int i, final int numero) {
		if (i == numero) {
			labelNumeroGenerado.setText(Integer.toString(numero));
			terminarAnimacion();
			return;
		}
		labelNumeroGenerado.setText(Integer.toString(i));
		try {
			Thread.sleep(f(i, numero));
		} catch (InterruptedException ex) {
		}
		Platform.runLater(() -> animarCambio(i < 999 ? i + 1 : 0, numero));
	}

	private void abrirVentanaDeJuego() {
		MarioPartyUtils.cambiarAVentana("VentanaDeJuego.fxml", (Stage) labelNumeroGenerado.getScene().getWindow());
	}

	private long f(int i, int numero) {
		return 10;//aquí iría el código para que la animación de cuando cae el número aleatorio se vea bonita
	}
}
