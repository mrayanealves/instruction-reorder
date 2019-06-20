package br.ufrn.imd.reordenamento.instrucoes;

import br.ufrn.imd.reordenamento.instrucoes.model.InstructionManager;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> instructions = new ArrayList<String>();
        instructions.add("add s0 t1 t2");
        instructions.add("add s1 s0 t3");
        instructions.add("add s4 t3 t6");
        instructions.add("addi s5 s0 s4");
        instructions.add("add s3 s1 s2");
        instructions.add("add s3 s1 s0");

        InstructionManager manager = new InstructionManager();

        List<String> reorderInstructions = manager.reorderInstructions(instructions);

        for (String instruction : reorderInstructions){
            System.out.println(instruction);
        }
    }
}
