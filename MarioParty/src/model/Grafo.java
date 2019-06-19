/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.String.format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author zEstebanCruz
 */
public final class Grafo extends ArrayList<Nodo> {

	Grafo() {
		super(26);
	}

	Map<Nodo, Integer> dijkstra(Nodo inicio) {
		return DijkstraAlgorithm.execute(inicio);
	}

	LinkedList<Nodo> caminoA(Nodo objetivo) {
		return DijkstraAlgorithm.getPath(objetivo);
	}

	Short[] floyd(Nodo nodo) {
		return FloydWarshall.floydWarshall(this)[indexOf(nodo)];
	}

	Short[][] floydCompleto() {
		return FloydWarshall.floydWarshall(this);
	}
}

final class DijkstraAlgorithm {

	private static final Set<Nodo> VISITADOS = new HashSet<>();
	private static final Set<Nodo> SIN_VISITAR = new HashSet<>();
	private static final Map<Nodo, Nodo> ANTERIORES = new HashMap<>();
	private static final Map<Nodo, Integer> DISTANCIAS = new HashMap<>();

	private DijkstraAlgorithm() {
	}

	public static Map<Nodo, Integer> execute(Nodo source) {
		VISITADOS.clear();
		SIN_VISITAR.clear();
		ANTERIORES.clear();
		DISTANCIAS.clear();
		DISTANCIAS.put(source, 0);
		SIN_VISITAR.add(source);
		while (!SIN_VISITAR.isEmpty()) {
			Nodo node = getMinimum();
			VISITADOS.add(node);
			SIN_VISITAR.remove(node);
			findMinimalDistances(node);
		}
		return DISTANCIAS;
	}

	private static void findMinimalDistances(Nodo node) {
		for (Nodo target : node.conexiones)
			if (!VISITADOS.contains(target) && getShortestDistance(target) > getShortestDistance(node)
																																			 + getDistance(node, target)) {
				DISTANCIAS.put(target, getShortestDistance(node)
															 + getDistance(node, target));
				ANTERIORES.put(target, node);
				SIN_VISITAR.add(target);
			}

	}

	private static int getDistance(Nodo node, Nodo target) {
		for (int i = 0; i < node.conexiones.size(); i++)
			if (node.conexiones.get(i) == target)
				return node.pesos.get(i);
		throw new RuntimeException("Should not happen");
	}

	private static Nodo getMinimum() {
		Nodo minimum = null;
		for (Nodo vertex : SIN_VISITAR)
			if (minimum == null)
				minimum = vertex;
			else if (getShortestDistance(vertex) < getShortestDistance(minimum))
				minimum = vertex;
		return minimum;
	}

	private static int getShortestDistance(Nodo destination) {
		Integer d = DISTANCIAS.get(destination);
		if (d == null)
			return Integer.MAX_VALUE;
		else
			return d;
	}

	/*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
	 */
	public static LinkedList<Nodo> getPath(Nodo target) {
		LinkedList<Nodo> path = new LinkedList<>();
		Nodo step = target;
		// check if a path exists
		if (ANTERIORES.get(step) == null)
			return null;
		path.add(step);
		while (ANTERIORES.get(step) != null) {
			step = ANTERIORES.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}

}

final class FloydWarshall {

	static Short[][] floydWarshall(Grafo grafo) {
		final int numVertices = 26;
		Short[][] dist = new Short[numVertices][numVertices];
		for (Short[] row : dist)
			Arrays.fill(row, Short.MAX_VALUE);
		// Hasta aquí {dist} está lleno con infinitos
		Nodo nI;
		for (int i = 0; i < grafo.size(); i++) {
			nI = grafo.get(i);
			for (int j = 0; j < nI.conexiones.size(); j++)
				dist[i][grafo.indexOf(nI.conexiones.get(j))] = nI.pesos.get(j);
		}
		//Se crea la matriz de pesos donde cuando no hay conexion, hay infinito
		char[][] next = new char[numVertices][numVertices];
		for (int i = 0; i < next.length; i++)
			for (int j = 0; j < next.length; j++)
				if (i != j)
					next[i][j] = (char) (j + 1);

		for (int k = 0; k < numVertices; k++)
			for (int i = 0; i < numVertices; i++)
				for (int j = 0; j < numVertices; j++)
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = (short) (dist[i][k] + dist[k][j]);
						next[i][j] = next[i][k];
					}
		return dist;
	}
}
