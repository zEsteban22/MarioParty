/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author zEstebanCruz
 */
public class SistemaDeJuego {

	public static int cantJugadores;
	private static final ArrayList<Jugador> JUGADORES = new ArrayList<>();

	public static void aniadirFicha(String nombreFicha) {
		JUGADORES.add(new Jugador(nombreFicha));
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
