/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Minijuegos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.MarioPartyUtils;
import model.SistemaDeJuego;

/**
 * FXML Controller class
 *
 * @author zEstebanCruz
 */
public class MarioCardsController implements Initializable {

	Boolean resultado = false;
	@FXML
	private ImageView imagenJugadorActual;
	private final String codCarta = "🂡";
	List<String> jugadores;
	Random random = new Random();
	@FXML
	private Label labelCarta;
	@FXML
	private Button botonEscogerCarta;
	private List<String> resultados = new ArrayList<>();

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		jugadores = SistemaDeJuego.getFichas();
		imagenJugadorActual.setImage(new Image(MarioPartyUtils.urlPersonaje(jugadores.get(0)).toString()));
		ponerCarta(random.nextInt(13), random.nextInt(4));
	}

	public static boolean jugarMarioCards() {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(
			ClassLoader.getSystemClassLoader().getResource(
				"model/Minijuegos/MarioCards.fxml"));
		try {
			stage.setScene(
				new Scene(
					(Parent) loader.load()));
		} catch (IOException ex) {
		}
		stage.showAndWait();
		return ((MarioCardsController) loader.getController()).resultado;
	}

	@FXML
	private void escogerCarta(ActionEvent event) {
		int numero = random.nextInt(13);
		int palo = random.nextInt(4);
		ponerCarta(numero, palo);
		resultados.add("" + (char) numero + (char) palo);
		if (resultados.size() == jugadores.size()) {
			List<Pair<String, String>> resultadosJugadores = new ArrayList<>();
			for (int i = 0; i < resultados.size(); i++)
				resultadosJugadores.add(new Pair<>(resultados.get(i), jugadores.get(i)));
			Collections.sort(resultadosJugadores, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));
			resultado = resultadosJugadores.get(0).getValue().equals(SistemaDeJuego.getPersonajeActual());
			Platform.runLater(() -> {
				new Alert(Alert.AlertType.INFORMATION, resultado ? "Has ganado!" : "Has perdido :c").showAndWait();
				((Stage) botonEscogerCarta.getScene().getWindow()).close();
			});
		}
		Collections.rotate(jugadores, -1);
		imagenJugadorActual.setImage(new Image(MarioPartyUtils.urlPersonaje(jugadores.get(0)).toString()));
	}

	private void ponerCarta(int nextInt, int nextInt0) {
		labelCarta.setText(codCarta.charAt(0) + Character.toString((char) (codCarta.charAt(1) + 16 * nextInt0 + nextInt)));
	}

}
