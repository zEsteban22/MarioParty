/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.util.Pair;
import javax.swing.JOptionPane;
import model.MarioPartyUtils;
import model.SistemaDeJuego;

/**
 * FXML Controller class
 *
 * @author zEstebanCruz
 */
public class ControladorDeJuego implements Initializable {

	@FXML
	private ImageView imagenJugadorActual;
	@FXML
	private ImageView casillaA;
	// <editor-fold defaultstate="collapsed" desc="Declaración de las variables de las casillas...">
	@FXML
	private ImageView casillaB;
	@FXML
	private ImageView casillaC;
	@FXML
	private ImageView casillaD;
	@FXML
	private ImageView casillaE;
	@FXML
	private ImageView casillaF;
	@FXML
	private ImageView casillaG;
	@FXML
	private ImageView casillaH;
	@FXML
	private ImageView casillaI;
	@FXML
	private ImageView casillaJ;
	@FXML
	private ImageView casillaK;
	@FXML
	private ImageView casillaL;
	@FXML
	private ImageView casillaM;
	@FXML
	private ImageView casillaN;
	@FXML
	private ImageView casillaO;
	@FXML
	private ImageView casillaP;
	@FXML
	private ImageView casillaQ;
	@FXML
	private ImageView casillaR;
	@FXML
	private ImageView casillaS;
	@FXML
	private ImageView casillaT;
	@FXML
	private ImageView casillaU;
	@FXML
	private ImageView casillaV;
	@FXML
	private ImageView casillaW;
	@FXML
	private ImageView casillaX;
	@FXML
	private ImageView casillaY;
	@FXML
	private ImageView casillaZ;
	// </editor-fold>
	@FXML
	private Button botonTurno;
	@FXML
	private ListView<String> listaFloyd;
	@FXML
	private ListView<String> listaDijkstra;
	private List<Pair<ImageView, Character>> caracteresPorCasilla;
	private List<ImageView> fichasJugadores;
	@FXML
	private ListView<String> listaVisitados;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		SistemaDeJuego.iniciarJuego();
		fichasJugadores = new ArrayList<>(SistemaDeJuego.cantJugadores);
		ImageView temp;
		for (String ficha : SistemaDeJuego.getFichas()) {
			temp = new ImageView(MarioPartyUtils.urlPersonaje(ficha).toString());
			temp.setFitWidth(70);
			temp.setFitHeight(70);
			temp.setPreserveRatio(true);
			fichasJugadores.add(temp);
			((AnchorPane) casillaA.getParent()).getChildren().add(temp);
			temp.setLayoutX(392);
			temp.setLayoutY(295);
			ejecutarAnimacionDespues(temp, ficha);
		}
		caracteresPorCasilla = new ArrayList<>(26);
		// <editor-fold defaultstate="collapsed" desc="Inicialización del mapa de casillas de la interfaz con su respectiva letra... ">
		caracteresPorCasilla.add(new Pair<>(casillaA, 'A'));
		caracteresPorCasilla.add(new Pair<>(casillaB, 'B'));
		caracteresPorCasilla.add(new Pair<>(casillaC, 'C'));
		caracteresPorCasilla.add(new Pair<>(casillaD, 'D'));
		caracteresPorCasilla.add(new Pair<>(casillaE, 'E'));
		caracteresPorCasilla.add(new Pair<>(casillaF, 'F'));
		caracteresPorCasilla.add(new Pair<>(casillaG, 'G'));
		caracteresPorCasilla.add(new Pair<>(casillaH, 'H'));
		caracteresPorCasilla.add(new Pair<>(casillaI, 'I'));
		caracteresPorCasilla.add(new Pair<>(casillaJ, 'J'));
		caracteresPorCasilla.add(new Pair<>(casillaK, 'K'));
		caracteresPorCasilla.add(new Pair<>(casillaL, 'L'));
		caracteresPorCasilla.add(new Pair<>(casillaM, 'M'));
		caracteresPorCasilla.add(new Pair<>(casillaN, 'N'));
		caracteresPorCasilla.add(new Pair<>(casillaO, 'O'));
		caracteresPorCasilla.add(new Pair<>(casillaP, 'P'));
		caracteresPorCasilla.add(new Pair<>(casillaQ, 'Q'));
		caracteresPorCasilla.add(new Pair<>(casillaR, 'R'));
		caracteresPorCasilla.add(new Pair<>(casillaS, 'S'));
		caracteresPorCasilla.add(new Pair<>(casillaT, 'T'));
		caracteresPorCasilla.add(new Pair<>(casillaU, 'U'));
		caracteresPorCasilla.add(new Pair<>(casillaV, 'V'));
		caracteresPorCasilla.add(new Pair<>(casillaW, 'W'));
		caracteresPorCasilla.add(new Pair<>(casillaX, 'X'));
		caracteresPorCasilla.add(new Pair<>(casillaY, 'Y'));
		caracteresPorCasilla.add(new Pair<>(casillaZ, 'Z'));
		// </editor-fold>
		actualizarVista();
	}

	private void ejecutarAnimacionDespues(ImageView imagen, String ficha) {
		Platform.runLater(() -> moverJugador(imagen, SistemaDeJuego.getCharPosicion(ficha), Duration.seconds(2)));
	}

	private Character getCharacter(ImageView image) {
		for (Pair p : caracteresPorCasilla)
			if (p.getKey() == image)
				return (Character) p.getValue();
		return null;
	}

	private ImageView getCasilla(Character caracter) {
		for (Pair p : caracteresPorCasilla)
			if (p.getValue() == caracter)
				return (ImageView) p.getKey();
		return null;
	}

	private void actualizarVista() {
		listaVisitados.getItems().clear();
		listaVisitados.getItems().addAll(SistemaDeJuego.getCasillasVisitadas());
		imagenJugadorActual.setImage(new Image(MarioPartyUtils.urlPersonajeActual().toString()));
		caracteresPorCasilla.forEach((Pair<ImageView, Character> p) -> {
			(p.getKey()).setDisable(true);
			(p.getKey()).setEffect(null);
		});
		actualizarListaFloyd();
		actualizarListaDijkstra();
		evaluarTurno();
	}

	private void actualizarListaFloyd() {
		ObservableList<String> lista = listaFloyd.getItems();
		lista.clear();
		Short[] largoCaminosPosibles = SistemaDeJuego.obtenerCaminosPosibles();
		for (short i = 0; i < largoCaminosPosibles.length; i++)
			if (largoCaminosPosibles[i] <= 12)
				lista.add((char) (i + 'A') + " -> " + largoCaminosPosibles[i]);
	}

	private void actualizarListaDijkstra() {
		ObservableList<String> lista = listaDijkstra.getItems();
		lista.clear();
		Integer[] largoMinimosCaminos = SistemaDeJuego.minimosCaminos();
		for (short i = 0; i < largoMinimosCaminos.length; i++)
			if (!SistemaDeJuego.jA_haVisitado((int) i) && largoMinimosCaminos[i] != 0)
				lista.add((char) (i + 'A') + " -> " + largoMinimosCaminos[i]);
	}

	private void evaluarTurno() {
		if (SistemaDeJuego.jA_tieneCastigo()) {
			SistemaDeJuego.jA_disminuirCondena();
			JOptionPane.showMessageDialog(
				null, "El jugador tiene un castigo, se ha disminuido su castigo un turno.", "Castigo", 1);
			siguienteTurno();
		} else if (SistemaDeJuego.jA_haVisitadoCasillaActual())
			botonTurno.setDisable(false);
		else {
			SistemaDeJuego.jugarCasillaActual();
			moverJugador(fichasJugadores.get(0), SistemaDeJuego.getCharPosicion_jA(), Duration.seconds(2));
			if (SistemaDeJuego.jA_haGanado()) {
				JOptionPane.showMessageDialog(null, "Has ganado!", "Ganador", 1);
				((AnchorPane) botonTurno.getParent()).setDisable(true);
			}
		}
	}

	private void moverJugador(ImageView imagenJugador, Character casillaObjetivo, Duration duracion) {
		makeTransition(imagenJugador, casillaObjetivo, duracion).play();
	}

	private TranslateTransition makeTransition(ImageView imagenJugador, Character casillaObjetivo, Duration duracion) {
		return makeTransition(imagenJugador, imagenJugador, casillaObjetivo, duracion);
	}

	private TranslateTransition makeTransition(ImageView imagenJugador, ImageView anterior, Character casillaObjetivo, Duration duracion) {
		ImageView objetivo = getCasilla(casillaObjetivo);
		TranslateTransition transicion = new TranslateTransition(duracion, imagenJugador);
		transicion.setToX(objetivo.getLayoutX() - imagenJugador.getLayoutX());
		transicion.setToY(objetivo.getLayoutY() - imagenJugador.getLayoutY());
		return transicion;
	}

	private void realizarRecorrido(LinkedList<Character> camino, int tamanioInicial) {
		SequentialTransition s = new SequentialTransition();
		for (short i = 0; i < camino.size(); i++)
			s.getChildren().add(
				makeTransition(fichasJugadores.get(0), i == 0 ? fichasJugadores.get(0) : getCasilla(camino.get(i - 1)), camino.get(i),
											 Duration.seconds(1)));
		s.play();
		s.setOnFinished((event) -> {
			SistemaDeJuego.jugarCasillaActual();
			siguienteTurno();
		});
	}

	@FXML
	private void girarDado(ActionEvent event) {
		botonTurno.setDisable(true);
		Random r = new Random();
		String s;
		int dado1 = r.nextInt(7), dado2 = r.nextInt(7);
		if (dado1 + dado2 == 0) {
			s = "Ups, ambos dados han caído en castigo, entonces ahora "
					+ "el jugador tiene 3 turnos de castigo.";

			SistemaDeJuego.jA_ponerCastigo(3);
		} else if (dado1 == 0 || dado2 == 0) {
			s = "En un dado ha caído castigo, ahora el jugador tiene un "
					+ "turno de castigo.";
			SistemaDeJuego.jA_ponerCastigo(1);
		} else {
			s = "Ha caído un " + (dado1 + dado2) + " en los dados.";
			activarCasillas(dado1 + dado2);
		}
		JOptionPane.showMessageDialog(null, s, "Resultado dados", 1);
		if (SistemaDeJuego.jA_tieneCastigo())
			siguienteTurno();
	}

	private void activarCasillas(int resultadoDado) {
		ImageView casilla;
		boolean hayAlguna = false;
		for (String str : listaFloyd.getItems())
			if (Integer.parseInt(str.substring(str.lastIndexOf(" ") + 1)) == resultadoDado
					&& !SistemaDeJuego.jA_haVisitado(str.charAt(0))
					&& !SistemaDeJuego.hayUnJugadorEn(str.charAt(0))) {
				casilla = getCasilla(str.charAt(0));
				casilla.setDisable(false);
				casilla.setEffect(new Glow(0.8));
				hayAlguna = true;
			}
		if (!hayAlguna) {
			JOptionPane.showMessageDialog(
				null, "No hay caminos posibles para ese resultado de dado, le toca "
							+ "el turno al siguiente jugador.", "Sin caminos posibles.", 1);
			siguienteTurno();
		}
	}

	@FXML
	private void accionCasilla(MouseEvent event) {
		LinkedList<Character> recorrido;
		recorrido = SistemaDeJuego.obtenerCaminoA(getCharacter((ImageView) event.getSource()));
		realizarRecorrido(recorrido, recorrido.size());
		SistemaDeJuego.mover_jA(getCharacter((ImageView) event.getSource()));
	}

	private void siguienteTurno() {
		Collections.rotate(fichasJugadores, -1);
		SistemaDeJuego.siguienteTurno();
		actualizarVista();
	}

}
