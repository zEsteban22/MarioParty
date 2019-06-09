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

	Nodo(Modificador m, char letra) {
		modificador = m;
		conexiones = new ArrayList<>();
	}

	void addConexionNoDirigida(Nodo nodo) {
		conexiones.add(nodo);
		nodo.conexiones.add(nodo);
	}

}
