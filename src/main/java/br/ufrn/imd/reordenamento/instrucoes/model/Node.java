package br.ufrn.imd.reordenamento.instrucoes.model;

public class Node {
	private String operation;
	private String destionationRecorder;
	private String firstOperationRecorder;
	private String lastOperationRecorder;
	
	public Node() {
		super();
	}
	
	public Node(String operation, String destionationRecorder, String firstOperationRecorder,
			String lastOperationRecorder) {
		this.operation = operation;
		this.destionationRecorder = destionationRecorder;
		this.firstOperationRecorder = firstOperationRecorder;
		this.lastOperationRecorder = lastOperationRecorder;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getDestionationRecorder() {
		return destionationRecorder;
	}
	
	public void setDestionationRecorder(String destionationRecorder) {
		this.destionationRecorder = destionationRecorder;
	}
	
	public String getFirstOperationRecorder() {
		return firstOperationRecorder;
	}
	
	public void setFirstOperationRecorder(String firstOperationRecorder) {
		this.firstOperationRecorder = firstOperationRecorder;
	}
	
	public String getLastOperationRecorder() {
		return lastOperationRecorder;
	}
	
	public void setLastOperationRecorder(String lastOperationRecorder) {
		this.lastOperationRecorder = lastOperationRecorder;
	}

	public boolean isDependent(Node node) {
		return false;
	}
	
	@Override
	public String toString() {
		return operation + " " + destionationRecorder
				+ " " + firstOperationRecorder + " "
				+ lastOperationRecorder;
	}
}
