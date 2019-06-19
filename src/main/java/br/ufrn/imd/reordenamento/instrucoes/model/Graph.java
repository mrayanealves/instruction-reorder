package br.ufrn.imd.reordenamento.instrucoes.model;

import java.util.List;

public class Graph {
	private Node root;
	private Integer level;
	private List<Graph> child;
	
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
	
	public List<Graph> getChild() {
		return child;
	}
	
	public void setChild(List<Graph> child) {
		this.child = child;
	}
	
	public void insertNode(Node newNode) {
		
	}
	
	public List<Graph> returnOrderedInstructions(Graph graph){
		return null;
	}
	
	private List<Graph> levelOrder(){
		return null;
	}
}
