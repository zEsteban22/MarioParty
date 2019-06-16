/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author zEstebanCruz
 */
public class Tablero {

	Map<Jugador, Nodo> posicionesJugadores;
	List<Pair<Character, Nodo>> nodoYSuChar;
	Grafo grafo;
	List<Nodo> tubos;

	Tablero(List<Jugador> jugadores) {
		posicionesJugadores = new HashMap<>();
		tubos = new ArrayList<>(3);
		grafo = new Grafo();
		nodoYSuChar = new ArrayList<>();
		ArrayList<Modificador> restantes = new ArrayList<>(
			Arrays.asList(Modificador.GATO, Modificador.SOPA_LETRAS,
										Modificador.CATCH_CAT, Modificador.MEMORY_PATH,
										Modificador.BROS_MEMORY, Modificador.BOMBERMARIO,
										Modificador.GUESS_WHO, Modificador.COLLECT_COINS,
										Modificador.MARIO_CARDS, Modificador.BOMBERMARIO,
										Modificador.BROS_MEMORY, Modificador.CARCEL,
										Modificador.CATCH_CAT, Modificador.COLA,
										Modificador.COLLECT_COINS, Modificador.ESTRELLA,
										Modificador.FIRE_FLOWER, Modificador.GATO,
										Modificador.GUESS_WHO, Modificador.ICE_FLOWER,
										Modificador.MARIO_CARDS, Modificador.MEMORY_PATH,
										Modificador.SOPA_LETRAS, Modificador.TUBO,
										Modificador.TUBO, Modificador.TUBO));
		Random random = new Random();
		for (int i = 0; i < 26; i++) {
			if (grafo.size() <= i) grafo.add(null);
			grafo.set(i, new Nodo(restantes.remove(random.nextInt(restantes.size()))));
			nodoYSuChar.add(new Pair<>((char) ('A' + i), grafo.get(i)));
			if (grafo.get(i).modificador == Modificador.TUBO)
				tubos.add(grafo.get(i));
		}
		Nodo k = null;
		for (Jugador jugador : jugadores) {
			while (posicionesJugadores.values().contains(k) || k == null)
				k = grafo.get(random.nextInt(26));
			try {
				posicionesJugadores.putIfAbsent(jugador, k);
			} catch (Exception e) {
			}
			jugador.visitados.add(k);
		}
		conectarGrafo();
	}

	public Nodo getNodo(Character caracter) {
		for (Pair p : nodoYSuChar)
			if ((char) p.getKey() == caracter)
				return (Nodo) p.getValue();
		return null;
	}

	public Character getChar(Nodo nodo) {
		for (Pair p : nodoYSuChar)
			if ((Nodo) p.getValue() == nodo)
				return (Character) p.getKey();
		return null;
	}

	public Nodo getPosicionJugador(Jugador jugador) {
		return posicionesJugadores.get(jugador);
	}

	final void conectarGrafo() {
		Random random = new Random();
		final char conexionesNecesarias = (char) (228 + random.nextInt(33));
		char cantConexionesActuales = 0;
		boolean flag;
		do {
			while (cantConexionesActuales < conexionesNecesarias)
				for (int i = 0; i < 26 && cantConexionesActuales < conexionesNecesarias; i++) {
					int j = random.nextInt(26);
					if (j != i && !grafo.get(i).conexiones.contains(grafo.get(j))) {
						grafo.get(i).addConexionNoDirigida(grafo.get(j), (short) (random.nextInt(10) + 1));
						cantConexionesActuales++;
					}
				}
			flag = Arrays.asList(grafo.floyd(grafo.get(0))).contains(Short.MAX_VALUE);
			if (flag)
				grafo.forEach((nodo) -> {
					nodo.conexiones.clear();
				});
		} while (flag);
	}

}
