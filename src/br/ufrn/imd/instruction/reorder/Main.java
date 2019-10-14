package br.ufrn.imd.instruction.reorder;

import br.ufrn.imd.instruction.reorder.model.InstructionManager;
import br.ufrn.imd.instruction.reorder.read.ReadTXT;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ReadTXT readTXT = new ReadTXT();

        List<String> instructions = readTXT.read("src/br/ufrn/imd/instruction/reorder/files/instructions.txt");

        InstructionManager manager = new InstructionManager();

        List<String> reorderInstructions = manager.reorderInstructions(instructions);
        
        System.out.println("  INSTRUCTION  |  DEPENDS ON\n");
        for (String instruction : reorderInstructions){
            System.out.println(instruction);
        }
    }
}
