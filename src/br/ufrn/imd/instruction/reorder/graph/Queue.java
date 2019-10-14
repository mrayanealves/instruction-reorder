package br.ufrn.imd.instruction.reorder.graph;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	private List<Graph> graphs;

	public Queue() {
		graphs = new ArrayList<Graph>();
	}
	
	/**
	 * Adiciona elementos na fila
	 * @param graph Graph a ser adicionado
	 */
	public void enqueue(Graph graph) {
		Integer inQueue = graphs.indexOf(graph);
		if (inQueue != -1){
			return;
		}
		this.graphs.add(graph);
	}

	/**
	 * Remove elementos da fila
	 * @return Graph que está no começo da fila
	 */
	public Graph dequeue() {
		return this.graphs.remove(0);
	}
	
	/**
	 * Verifica se a fila está vazia
	 * @return boolean que indica se está vazia
	 */
	public boolean isEmpty() {
		return this.graphs.size() == 0;
	}
}
