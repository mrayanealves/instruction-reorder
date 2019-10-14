package br.ufrn.imd.instruction.reorder.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe que repesenta um grafo de instruções
 * A ideia é que haja um nó de instrução e que
 * cada nó seja filho (dependent) do nó com a instrução da qual ele depende para ser executado.
 * É importante mencionar que uma instrução pode depender de, no máximo, duas outras instruções.
 * Isto é, um nó pode ter, no máximo, dois nós responsáveis.
 */

public class Graph {
	private Node root;
	private Integer level;
	private List<Graph> responsables;
	private List<Graph> dependents;

	/**
	 * Construtores
	 */
	public Graph() {
		this.root = null;
		this.dependents = new ArrayList<Graph>();
		this.responsables = new ArrayList<Graph>();
		this.level = 1;
	}

	public Graph(Node node) {
		this.root = node;
		this.dependents = new ArrayList<Graph>();
		this.responsables = new ArrayList<Graph>();
		this.level = 0;
	}

	public Graph(Node node, Integer level) {
		this.root = node;
		this.level = level;
		this.dependents = new ArrayList<Graph>();
		this.responsables = new ArrayList<Graph>();
	}

	/**
	 * Geters e Seters
	 *
	 */
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
	 * Insere um novo nó no grafo
	 * @param newNode Node a ser inserido
	 */
	public void insertNode(Node newNode) {
		// Se o grafo não tiver nenhum dependente, apenas insere o novo nó
		if (this.dependents.isEmpty()) {
			this.dependents.add(new Graph(newNode, 2));
			return;
		}

		boolean hasResponsable = false;

		// Cria o novo grafo
		Graph newGraph = new Graph(newNode);

		// Percorre o grafo por nível e itera
		List<Graph> graphs = this.levelOrder();
		Iterator<Graph> it = graphs.iterator();

		while (it.hasNext()) {
			Graph graph = (Graph) it.next();

			// Se a raiz do novo grafo for dependente do grafo iterado
			if (newGraph.getRoot().isDependent(graph.getRoot())) {
				newGraph.setResponsable(graph);
				hasResponsable = true;

				// Se o novo grafo não tiver responsável, o nível será o do grafo iterado mais um.
				// Se tiver, o seu nivel é o maior nível entre os responsáveis mais um.
				if (newGraph.getResponsables().isEmpty()){
					newGraph.setLevel(graph.getLevel() + 1);
				} else {
					newGraph.setLevel(higherLevel(newGraph.getResponsables()) + 1);
				}
				graph.setDependent(newGraph);
			}

			// Se o grafo iterado for diferente de nulo e for dependente do novo grafo
			else if ((graph.getRoot() != null) && (graph.getRoot().isDependent(newGraph.getRoot()))) {
				newGraph.setDependent(graph);
				graph.setResponsable(newGraph);
			}
		}

		// Se depois de percorrer o grafo completo o novo grafo não encontrar nenhuma dependência para ele, insere ele
		// no primeiro nível
		if (!hasResponsable){
			this.dependents.add(new Graph(newNode, 2));
		}
	}

	/**
	 * Retorna o grafo e suas dependências
	 * @return Lista com a instrução e suas dependências
	 */
	public List<String> returnGraphDependences(){
		List<String> orderedInstructions = new ArrayList<String>();

		// Percorre o grafo por nível
		List<Graph> graphLevelOrdered = levelOrder();

		String compactString = "";

		// Se houver algo no grafo
		if (graphLevelOrdered != null){

			for (int i = 0; i < graphLevelOrdered.size(); i++){
				// Se o nó for difrente de nulo
				if (graphLevelOrdered.get(i).getRoot() != null){
					compactString += i + " " + graphLevelOrdered.get(i).getRoot().toString() + " | ";

					// Se o nó tiver responsáveis diferente de nulo
					if (graphLevelOrdered.get(i).getResponsables() != null){

						// Se não for vazio
						if (!graphLevelOrdered.get(i).getResponsables().isEmpty()){

							// Busca qual posição da lita está o responsável e adiciona na strig construída naquele nó
							compactString += findInGraph(graphLevelOrdered, graphLevelOrdered.get(i).getResponsables().get(0)) + " ";

							// Se tiver dois responsáveis
							if (graphLevelOrdered.get(i).getResponsables().size() == 2){
								compactString += findInGraph(graphLevelOrdered, graphLevelOrdered.get(i).getResponsables().get(1)) + " ";
							}
						}

					}

					// Adiciona a string constrída
					orderedInstructions.add(compactString);
					compactString = "";
				}

			}

		}

		return orderedInstructions;
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

		// Emplilha o grafo
		queue.enqueue(graph);

		// Enquanto a pilha estiver com algum elemento
		while(!queue.isEmpty()) {
			// Despilha
			graph = queue.dequeue();

			// Adiciona esse grafo despilhado
			graphs.add(graph);

			// Se houver dependentes
			if ((graph.dependents != null) && (!graph.dependents.isEmpty())){

				for (Graph dependent : graph.getDependents()) {

					// Se o nível do dependente for o imediatamente abaixo do responsável
					if (dependent.getLevel() == graph.getLevel() + 1) {
						queue.enqueue(dependent);
					}

				}

			}

		}

		return graphs;
	}

	/**
	 * Verifica se um grafo é igual ao outro
	 * @param graph Graph a ser comparado
	 * @return boolean respondendo se é ou não
	 */
	private boolean equals(Graph graph){
		boolean equals = false;

		// Se as raizes não forem nulas
		if ((this.root != null) && (graph.root != null)){

			// Se as raízes forem iguais
			if (this.root.equals(graph.root)){
				equals = true;
			}

		}

		return equals;
	}

	/**
	 * Busca a posição de um grafo em uma lista
	 * @param graphs List de grafos
	 * @param graph Graph a ser buscado
	 * @return Integer com a posiçao do grafo na lista
	 */
	private Integer findInGraph(List<Graph> graphs, Graph graph) {
		Integer position = -1;

		for (int i = 0; i < graphs.size(); i++) {
			if (graph.equals(graphs.get(i))) {
				position = i;
				break;
			}
		}

		return position;
	}
}
