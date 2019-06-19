package br.ufrn.imd.reordenamento.instrucoes.model;

import java.util.List;

public class Queue {
	private List<Graph> graphs;

	public Queue() {
		super();
	}
	
	/**
	 * Adiciona elementos na fila
	 * @param graph Graph a ser adicionado
	 */
	public void enqueue(Graph graph) {
		this.graphs.add(graph);
	}
	
	public void enqueueAll(List<Graph> graphs) {
		graphs.addAll(graphs);
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
