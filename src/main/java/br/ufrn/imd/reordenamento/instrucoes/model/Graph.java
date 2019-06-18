package br.ufrn.imd.reordenamento.instrucoes.model;

import java.util.List;

public class Graph {
	private Node root;
	private List<Graph> child;
	
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	public List<Graph> getChild() {
		return child;
	}
	public void setChild(List<Graph> child) {
		this.child = child;
	}
}
