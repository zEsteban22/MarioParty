/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
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

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		SistemaDeJuego.iniciarJuego();
		actualizarListaFloyd();
		actualizarListaDijkstra();
		imagenJugadorActual.setImage(new Image(MarioPartyUtils.urlPersonajeActual().toString()));
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
		caracteresPorCasilla.forEach((Pair<ImageView, Character> p) -> {
			(p.getKey()).setDisable(true);
		});
		evaluarTurno();

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
			if (!SistemaDeJuego.jugadorActualHaVisitado(i) && largoMinimosCaminos[i] != 0)
				lista.add((char) (i + 'A') + " -> " + largoMinimosCaminos[i]);
	}

	private void evaluarTurno() {
		if (SistemaDeJuego.jA_tieneCastigo())
			SistemaDeJuego.jA_disminuirCondena();
		else if (SistemaDeJuego.jugadorHaVisitadoSuCasillaActual())
			botonTurno.setDisable(false);
		else
			SistemaDeJuego.jugarCasillaActual(); //moverJugadorAnimadamente(SistemaDeJuego.getCharPosicionJugadorActual());}
	}

	private void moverJugadorAnimadamente(Character charPosicionJugadorActual) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@FXML
	private void girarDado(ActionEvent event) {
		Random r = new Random();
		int dado1 = r.nextInt(7), dado2 = r.nextInt(7);
		if (dado1 + dado2 == 0)
			SistemaDeJuego.jA_ponerCastigo(3);
		else if (dado1 == 0 || dado2 == 0)
			SistemaDeJuego.jA_ponerCastigo(1);
		else activarCasillas(dado1 + dado2);
	}

	private void activarCasillas(int i) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
