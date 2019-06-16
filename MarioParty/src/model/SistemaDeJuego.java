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

/**
 *
 * @author zEstebanCruz
 */
public class SistemaDeJuego {

	public static int cantJugadores;
	private static final ArrayList<Jugador> JUGADORES = new ArrayList<>();
	private static Tablero tablero;
	private static final Map<Jugador, Short> carcel = new HashMap<>();

	private SistemaDeJuego() {
	}

	public static void jA_ponerCastigo(int cantTurnos) {
		carcel.putIfAbsent(jugadorActual(), (short) cantTurnos);
	}

	public static boolean jA_tieneCastigo() {
		return carcel.containsKey(jugadorActual());
	}

	public static void jA_disminuirCondena() {
		carcel.put(jugadorActual(), (short) (carcel.getOrDefault(jugadorActual(), (short) 1) - 1));
		if (carcel.get(jugadorActual()) == 0)
			carcel.remove(jugadorActual());
	}

	public static Jugador jugadorActual() {
		return JUGADORES.get(0);
	}

	public static boolean jugadorActualHaVisitado(int indiceNodo) {
		return jugadorActual().visitados.contains(tablero.grafo.get(indiceNodo));
	}

	public static boolean jugadorHaVisitadoSuCasillaActual() {
		return jugadorActual().visitados.contains(tablero.getPosicionJugador(jugadorActual()));
	}

	public static void jugarCasillaActual() {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	public static Character getCharPosicionJugadorActual() {
		return tablero.getChar(tablero.getPosicionJugador(jugadorActual()));
	}

	public static void iniciarJuego() {
		tablero = new Tablero(JUGADORES);
	}

	public static void aniadirFicha(String nombreFicha) {
		JUGADORES.add(new Jugador(nombreFicha));
	}

	public static Short[] obtenerCaminosPosibles() {
		return tablero.grafo.floyd(tablero.posicionesJugadores.get(JUGADORES.get(0)));
	}

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

	public static LinkedList<Nodo> obtenerCaminoA(char idNodo) {
		return tablero.grafo.caminoA(tablero.getNodo(idNodo));
	}

	static String getFicha(int i) {
		return JUGADORES.get(i).ficha;
	}

	public static String getPersonajeActual() {
		return JUGADORES.get(0).ficha;
	}

	public static void siguienteTurno() {
		Collections.rotate(JUGADORES, -1);
	}

	public static void setJugadores(ArrayList<String> ordenJugadores) {
		JUGADORES.clear();
		ordenJugadores.forEach((i) -> {
			JUGADORES.add(new Jugador(i));
		});
	}
}
