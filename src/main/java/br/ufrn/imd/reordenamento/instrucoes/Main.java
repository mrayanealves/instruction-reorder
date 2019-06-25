package br.ufrn.imd.reordenamento.instrucoes;

import br.ufrn.imd.reordenamento.instrucoes.model.InstructionManager;
import br.ufrn.imd.reordenamento.instrucoes.read.ReadTXT;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ReadTXT readTXT = new ReadTXT();

        List<String> instructions = readTXT.read("src/main/java/br/ufrn/imd/reordenamento/instrucoes/files/instructions.txt");

        InstructionManager manager = new InstructionManager();

        List<String> reorderInstructions = manager.reorderInstructions(instructions);
        
        System.out.println("  INSTRUCTION  |  DEPENDS ON\n");
        for (String instruction : reorderInstructions){
            System.out.println(instruction);
        }
    }
}
