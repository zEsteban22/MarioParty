/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.application.Platform;
import javax.swing.JOptionPane;

/**
 *
 * @author zEstebanCruz
 */
public class SistemaDeJuego {

	public static int cantJugadores;
	private static final ArrayList<Jugador> JUGADORES = new ArrayList<>();
	private static Tablero tablero;
	private static final Map<Jugador, Short> CARCEL = new HashMap<>();

	/**
	 * Pregunta si hay algún jugador en la casilla que recibe de parámetro
	 *
	 * @param idNodo La casilla en la que hará la consulta
	 * @return La respuesta de si hay alguien o no
	 */
	public static boolean hayUnJugadorEn(char idNodo) {
		return tablero.posicionesJugadores.values().contains(tablero.getNodo(idNodo));
	}

	public static boolean jA_haGanado() {
		return jA().visitados.size() == 26;
	}

	public static List<String> getCasillasVisitadas() {
		List<String> salida = new ArrayList<>(jA().visitados.size());
		jA().visitados.forEach((nodo) -> {
			salida.add(Character.toString(tablero.getChar(nodo)));
		});
		return salida;
	}

	private SistemaDeJuego() {
	}

	/**
	 * Inicializa el tablero de juego
	 */
	public static void iniciarJuego() {
		tablero = new Tablero(JUGADORES);
	}

	/**
	 * Pone un castigo al jugador actual
	 *
	 * @param cantTurnos la cantidad de turnos que durará el castigo
	 */
	public static void jA_ponerCastigo(int cantTurnos) {
		CARCEL.putIfAbsent(jA(), (short) cantTurnos);
	}

	/**
	 * Pregunta si el jugador actual tiene un castigo
	 *
	 * @return la respuesta de si tiene castigo o no
	 */
	public static boolean jA_tieneCastigo() {
		return CARCEL.containsKey(jA());
	}

	/**
	 * Indica al sistema de juego que el jugador ya cumplió un turno de su condena
	 */
	public static void jA_disminuirCondena() {
		CARCEL.put(jA(), (short) (CARCEL.getOrDefault(jA(), (short) 1) - 1));
		if (CARCEL.get(jA()) == 0)
			CARCEL.remove(jA());
	}

	/**
	 * Accede al campo jugador actual
	 *
	 * @return jugador actual
	 */
	public static Jugador jA() {
		return JUGADORES.get(0);
	}

	/**
	 * Pregunta si el jugador actual ha visitado un nodo
	 *
	 * @param indiceNodo el indice del nodo el cual se desea saber si ha sido visitado
	 * @return la respuesta de si ha sido visitado o no
	 */
	public static boolean jA_haVisitado(Integer indiceNodo) {
		return jA().visitados.contains(tablero.grafo.get(indiceNodo));
	}

	/**
	 * Pregunta si el jugador actual ha visitado un nodo
	 *
	 * @param charNodo el char que representa el nodo del cual se desea saber si ha sido visitado
	 * @return la respuesta de si ha sido visitado o no
	 */
	public static boolean jA_haVisitado(Character charNodo) {
		return jA().visitados.contains(tablero.getNodo(charNodo));
	}

	/**
	 * Pregunte si el jugador actual ha visitado la casilla en la que se encuentra
	 *
	 * @return la respuesta a la pregunta
	 */
	public static boolean jA_haVisitadoCasillaActual() {
		return jA().visitados.contains(tablero.getPosicionJugador(jA()));
	}

	/**
	 * Ejecuta el modificador de la casilla en la que se encuentra el jugador actual
	 */
	public static void jugarCasillaActual() {
		jA().visitados.add(tablero.posicionesJugadores.get(jA()));
		Platform.runLater(() -> JOptionPane.showMessageDialog(null, "Se ha jugado la casilla actual jaja", "Minijuego", 1));
	}

	/**
	 * @return el caracter que hace referencia al nodo en que se encuentra el jugador actual
	 */
	public static Character getCharPosicion_jA() {
		return getCharPosicion(jA());
	}

	/**
	 * @param jugador El jugador del que se buscará la posición
	 * @return el caracter que hace referencia al nodo en que se encuentra el jugador que recibe de
	 * parámetro
	 */
	public static Character getCharPosicion(Jugador jugador) {
		return tablero.getChar(tablero.getPosicionJugador(jugador));
	}

	/**
	 * @param ficha la ficha del jugador del que se buscará la posición
	 * @return el caracter que hace referencia al nodo en que se encuentra el jugador que recibe de
	 * parámetro
	 */
	public static Character getCharPosicion(String ficha) {
		return getCharPosicion(JUGADORES.stream().filter(
			(jugador)
			-> jugador.ficha.equals(ficha))
			.findFirst().get());
	}

	/**
	 * Añade un jugador
	 *
	 * @param nombreFicha la ficha que se le añadirá al jugador que se está creando
	 */
	public static void aniadirFicha(String nombreFicha) {
		JUGADORES.add(new Jugador(nombreFicha));
	}

	/**
	 * Realiza el recorrido de floyd en el tablero
	 *
	 * @return la lista de distancias a cada nodo por su posición en la lista de nodos del grafo
	 */
	public static Short[] obtenerCaminosPosibles() {
		return tablero.grafo.floyd(tablero.posicionesJugadores.get(JUGADORES.get(0)));
	}

	/**
	 * Realiza el recorrido de dijkstra partiendo del nodo en el que se encuentra el jugador
	 *
	 * @return la lista de pesos requeridos para llegar a el resto de nodos
	 */
	public static Integer[] minimosCaminos() {
		Map<Nodo, Integer> minimosCaminos = tablero.grafo.dijkstra(tablero.posicionesJugadores.
			get(JUGADORES.get(0)));
		List<Entry<Nodo, Integer>> listaDeEntradas = new ArrayList<>(minimosCaminos.entrySet());
		Collections.sort(listaDeEntradas, (o1, o2)
										 -> tablero.grafo.indexOf(o1.getKey()) - tablero.grafo.indexOf(o2.getKey()));
		List<Integer> salida = new ArrayList<>();
		listaDeEntradas.forEach((entrada) -> {
			salida.add(entrada.getValue());
		});
		return salida.toArray(new Integer[salida.size()]);
	}

	/**
	 * Segun el recorrido de dijkstra, da el camino desde la posición del jugador actual al nodo que
	 * recibe de parámetro
	 *
	 * @param idNodo nodo al que se desea llegar
	 * @return El recorrido de nodos para llegar hasta el nodo que recibe de parámetro
	 */
	public static LinkedList<Character> obtenerCaminoA(char idNodo) {
		LinkedList<Nodo> camino = tablero.grafo.caminoA(tablero.getNodo(idNodo));
		LinkedList<Character> salida = new LinkedList<>();
		camino.forEach((nodo) -> {
			salida.add(tablero.getChar(nodo));
		});
		return salida;
	}

	static String getFicha(int i) {
		return JUGADORES.get(i).ficha;
	}

	/**
	 * @return la ficha del personaje actual
	 */
	public static String getPersonajeActual() {
		return JUGADORES.get(0).ficha;
	}

	/**
	 * Cambia el jugador actual por el siguiente y el anterior jugador actual pasa a ser el último
	 */
	public static void siguienteTurno() {
		Collections.rotate(JUGADORES, -1);
	}

	public static void setJugadores(ArrayList<String> ordenJugadores) {
		JUGADORES.clear();
		ordenJugadores.forEach((i) -> {
			JUGADORES.add(new Jugador(i));
		});
	}

	/**
	 * Solicita las fichas de los jugadores que están en juego
	 *
	 * @return la lista de strings que representa a cada ficha
	 */
	public static List<String> getFichas() {
		List<String> fichas = new ArrayList<>();
		JUGADORES.forEach((i) -> {
			fichas.add(i.ficha);
		});
		return fichas;
	}

	public static void mover_jA(char idNodo) {
		tablero.posicionesJugadores.put(jA(), tablero.getNodo(idNodo));
	}
}
