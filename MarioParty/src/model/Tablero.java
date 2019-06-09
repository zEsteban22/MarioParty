/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author zEstebanCruz
 */
public class Tablero {

	Map<Jugador, Nodo> posicionesJugadores;
	Grafo grafo;

	Tablero() {
		posicionesJugadores = new TreeMap<>();
		grafo = new Grafo();
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
		String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < 26; i++)
			grafo.nodos[i] = new Nodo(restantes.remove(random.nextInt(restantes.size())), c.charAt(i));
	}

	void conectarGrafo() {
		Random random = new Random();
		final char conexionesNecesarias = (char) (228 + random.nextInt(33));
		char cantConexionesActuales = 0;
		for (int i = 0; i < 26 && cantConexionesActuales < conexionesNecesarias; i++)
			for (int j = i + 1; j < 26 && cantConexionesActuales < conexionesNecesarias; j++)
				if (!grafo.nodos[i].conexiones.contains(grafo.nodos[j])) {
					grafo.nodos[i].addConexionNoDirigida(grafo.nodos[j]);
					cantConexionesActuales++;
				}
	}

}
