package br.ufrn.imd.reordenamento.instrucoes.read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadTXT {

    public List<String> read (String txtFile){
        List<String> instructions = new ArrayList<String>();

        // Leitura do arquivo
        try {
            FileReader file = new FileReader(txtFile);
            BufferedReader readFile = new BufferedReader(file);
            String line = readFile.readLine();

            // Enquanto o arquivo não chegar ao fim
            while (line != null) {
                // Insere a instrução na lista
                instructions.add(line);
                line = readFile.readLine();
            }
            // Fecha o arquivo
            file.close();
            // Caso dê algum erro na abertura do aqruivo
        } catch (FileNotFoundException e) {
            System.err.printf("Error to find file: %s.\n", e.getMessage());
        } catch (IOException e) {
            System.err.printf("Error to open file: %s.\n", e.getMessage());
        }

        return instructions;
    }
}
