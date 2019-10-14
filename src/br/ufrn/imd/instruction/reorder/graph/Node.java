package br.ufrn.imd.instruction.reorder.graph;

import br.ufrn.imd.instruction.reorder.model.Instruction;

/**
 * Classe que representa o nó com a instrução
 */

public class Node {
	private Instruction instruction;

	/**
	 * Construtores
	 */
	public Node() {
		this.instruction = new Instruction();
	}

	public Node(Instruction instruction) {
		this.instruction = instruction;
	}

	/**
	 * Getters and Setters
	 */
	public Instruction getInstruction() {
		return instruction;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	public boolean isDependent(Node node) {
		if (node == null){
			return false;
		}
		return this.instruction.isDependent(node.getInstruction());
	}

	public boolean isFalseDependent(Node node){
		if (node == null){
			return false;
		}
		return this.instruction.isFalseDependent(node.getInstruction());
	}

	public boolean equals(Node node){
		if (node == null){
			return false;
		}
		return this.instruction.equals(node.getInstruction());
	}
	
	@Override
	public String toString() {
		return this.instruction.toString();
	}
}
