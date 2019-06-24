package br.ufrn.imd.reordenamento.instrucoes.model;

import br.ufrn.imd.reordenamento.instrucoes.exception.InstructionNotValidException;
import br.ufrn.imd.reordenamento.instrucoes.graph.Graph;
import br.ufrn.imd.reordenamento.instrucoes.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class InstructionManager {
    private Integer nextRenameRegister;

    public InstructionManager() {
        nextRenameRegister = 0;
    }

    public Integer getNextRenameRegister() {
        return nextRenameRegister;
    }

    public void setNextRenameRegister(Integer nextRenameRegister) {
        this.nextRenameRegister = nextRenameRegister;
    }

    public List<String> reorderInstructions(List<String> instructions){
        Graph graph = makeGraph(instructions);

        return graph.returnGraphDependences();
    }

    private String newRegisterName(){
        String newName = "x" + nextRenameRegister;
        nextRenameRegister++;
        return newName;
    }

    private Graph makeGraph(List<String> instructions){
        List<Node> nodes = makeNodes(instructions);
        Graph graph = new Graph();

        for (int i = 0; i < nodes.size(); i++){
            if (i == 0){
                graph.insertNode(nodes.get(0));
            } else {
                if (nodes.get(i).isFalseDependent(nodes.get(i-1))){
                    nodes.get(i).getInstruction().setDestionationRecorder(newRegisterName());
                }
                graph.insertNode(nodes.get(i));
            }
        }

        return graph;
    }

    private List<Node> makeNodes(List<String> instructions){
        List<Node> newsNodes = new ArrayList<Node>();
        Node newNode = new Node();

        for (String instruction : instructions){
            newNode = new Node();
            String [] splitInstruction = instruction.split(" ");

            if (splitInstruction.length != 4){
                throw new InstructionNotValidException("Instruction not valid");
            }

            newNode.getInstruction().setOperation(splitInstruction[0]);
            newNode.getInstruction().setDestionationRecorder(splitInstruction[1]);
            newNode.getInstruction().setFirstOperationRecorder(splitInstruction[2]);
            newNode.getInstruction().setLastOperationRecorder(splitInstruction[3]);

            newsNodes.add(newNode);
        }

        return newsNodes;
    }
}
