/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author zEstebanCruz
 */
public class Jugador {

	String ficha;
	ArrayList<Nodo> visitados;

	Jugador(String ficha) {
		this.ficha = ficha;
		visitados = new ArrayList<>();
	}

	void visitar(Nodo nodo) {
		visitados.add(nodo);
	}

	void reiniciar() {
		visitados.clear();
	}
}
