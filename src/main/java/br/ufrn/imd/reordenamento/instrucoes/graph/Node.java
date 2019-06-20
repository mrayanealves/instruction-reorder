package br.ufrn.imd.reordenamento.instrucoes.graph;

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
		boolean isDependent = false;

		if (node != null){
			if ((node.getDestionationRecorder().equals(this.getFirstOperationRecorder())) ||
					(node.getDestionationRecorder().equals(this.getLastOperationRecorder()))){
				isDependent = true;
			}
		}

		return isDependent;
	}

	public boolean isFalseDependent(Node node){
		boolean isFalseDependent = false;

		if (this.getDestionationRecorder().equals(node.getDestionationRecorder())){
			isFalseDependent = true;
		}

		return isFalseDependent;
	}

	public boolean equals(Node node){
		boolean equals = false;

		if ((this.getOperation().equals(node.getOperation())) ||
				(this.getDestionationRecorder().equals(node.getDestionationRecorder())) ||
				(this.getFirstOperationRecorder().equals(node.getFirstOperationRecorder())) ||
				(this.getLastOperationRecorder().equals(node.getLastOperationRecorder()))){
			equals = true;
		}

		return equals;
	}
	
	@Override
	public String toString() {
		return operation + " " + destionationRecorder
				+ " " + firstOperationRecorder + " "
				+ lastOperationRecorder;
	}
}
