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
public class Nodo {

	Modificador modificador;
	ArrayList<Nodo> conexiones;
	ArrayList<Short> pesos;

	Nodo(Modificador m) {
		modificador = m;
		conexiones = new ArrayList<>();
		pesos = new ArrayList<>();
	}

	void addConexionNoDirigida(Nodo nodo, short peso) {
		conexiones.add(nodo);
		nodo.conexiones.add(nodo);
		pesos.add(peso);
		nodo.pesos.add(peso);
	}

}
