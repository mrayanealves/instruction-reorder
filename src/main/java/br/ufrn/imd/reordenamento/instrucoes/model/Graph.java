package br.ufrn.imd.reordenamento.instrucoes.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph {
	private Node root;
	private Integer level;
	private List<Graph> dependents;
	
	
	
	public Graph() {
		this.root = null;
		this.dependents = new ArrayList<Graph>();
		this.level = 1;
	}

	public Graph(Node node) {
		this.root = node;
		this.dependents = new ArrayList<Graph>();
		this.level = null;
	}

	public Graph(Node node, Integer level) {
		this.root = node;
		this.level = level;
	}

	public Node getRoot() {
		return root;
	}
	
	public void setRoot(Node root) {
		this.root = root;
	}
	
	public Integer getLevel() {
		return level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public List<Graph> getDependents() {
		return dependents;
	}

	public void setDependents(List<Graph> dependents) {
		this.dependents = dependents;
	}

	public void insertNode(Node newNode) {
		if (this.dependents.isEmpty()) {
			this.dependents.add(new Graph(newNode, 2));
		}
		
		List<Graph> graphs = this.levelOrder();
		Iterator<Graph> it = graphs.iterator();
		
		boolean hasResponsable = false;
		Integer level = 0;
		Graph responsable = new Graph();
		
		while (it.hasNext()) {
			Graph graph = (Graph) it.next();
			if (newNode.isDependent(graph.root)) {
				if (hasResponsable) {
					level = graph.getLevel() + 1;
					if (graph.getLevel() < responsable.getLevel()) {
						level = responsable.level + 1;
						// Parei aqui
					}
				}
			}
		}
		
	}
	
	public List<Graph> returnOrderedInstructions(Graph graph){
		return null;
	}
	
	private List<Graph> levelOrder(){
		Queue queue = new Queue();
		List<Graph> graphs = new ArrayList<Graph>();
		
		Graph graph = this;
		queue.enqueue(graph);
		
		while(!queue.isEmpty()) {
			graph = queue.dequeue();
			graphs.add(graph);
			
			if (!graph.dependents.isEmpty()) {
				queue.enqueueAll(graph.getDependents());
			}
		}
		
		return graphs;
	}
}
