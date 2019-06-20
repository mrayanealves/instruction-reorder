package br.ufrn.imd.reordenamento.instrucoes.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph {
	private Node root;
	private Integer level;
	private List<Graph> responsables;
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
	
	public Integer getLevel() {
		return level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<Graph> getResponsables() {
		return this.responsables;
	}

	public void setResponsable(Graph responsable){
		this.responsables.add(responsable);
	}

	public List<Graph> getDependents() {
		return this.dependents;
	}

	public void setDependent(Graph dependent){
		this.dependents.add(dependent);
	}

	/**
	 * Retorna o grafo e suas dependências
	 * @return Lista com a instrução e suas dependências
	 */
	public List<String> returnGraphDependences(){
		List<String> orderedInstructions = new ArrayList<String>();
		List<Graph> graphLevelOrdered = levelOrder();

		String compactString = "";

		for (int i = 0; i < graphLevelOrdered.size(); i++){
			compactString.concat((i+1) + " " + graphLevelOrdered.get(i).getRoot().toString() + " | ");
			if (!graphLevelOrdered.get(i).getResponsables().isEmpty()){
				for (Graph graph : graphLevelOrdered.get(i).getResponsables()){
					compactString.concat((orderedInstructions.indexOf(graph)+1) + " ");
				}
			}
			orderedInstructions.add(compactString);
			compactString = "";
		}

		return orderedInstructions;
	}

	/**
	 * Insere um novo nó no grafo
	 * @param newNode Node a ser inserido
	 */
	public void insertNode(Node newNode) {
		// Se o grafo não tiver nenhum dependente, apenas insere o novo nó
		if (this.dependents.isEmpty()) {
			this.dependents.add(new Graph(newNode, 2));
		}

		// Cria o novo grafo
		Graph newGraph = new Graph(newNode);

		// Percorre o grafo por nível e itera
		List<Graph> graphs = this.levelOrder();
		Iterator<Graph> it = graphs.iterator();

		while (it.hasNext()) {
			Graph graph = it.next();
			// Se a raiz do novo grafo for dependente do grafo iterado
			if (newGraph.getRoot().isDependent(graph.getRoot())) {
				newGraph.setResponsable(graph);

				// Se o novo grafo não tiver responsável, o nível será o do grafo iterado mais um.
				// Se tiver, o seu nivel é o maior nível entre os responsáveis mais um.
				if (newGraph.getResponsables().isEmpty()){
					newGraph.setLevel(graph.getLevel() + 1);
				} else {
					newGraph.setLevel(higherLevel(newGraph.getResponsables()) + 1);
				}

				graph.setDependent(newGraph);
			}
		}
	}

	/**
	 * Calcula qual o maior nível entre os grafos
	 * @param graphs List<Graph> lista de grafos
	 * @return Integer com o maior nível
	 */
	private Integer higherLevel(List<Graph> graphs){
		Integer higherLevel = graphs.get(0).getLevel();
		for (Graph graph : graphs){
			if (graph.getLevel() > higherLevel){
				higherLevel = graph.getLevel();
			}
		}

		return higherLevel;
	}

	/**
	 * Percorre o grafo por nível
	 * @return Lista de grafos obtidos por nível
	 */
	private List<Graph> levelOrder(){
		Queue queue = new Queue();
		List<Graph> graphs = new ArrayList<Graph>();
		
		Graph graph = this;
		queue.enqueue(graph);
		
		while(!queue.isEmpty()) {
			graph = queue.dequeue();
			graphs.add(graph);
			
			if (!graph.dependents.isEmpty()) {
				for (Graph dependent : graph.getDependents()) {
					if (dependent.getLevel() == graph.getLevel() + 1) {
						queue.enqueue(dependent);
					}
				}
			}
		}

		return graphs;
	}
}
