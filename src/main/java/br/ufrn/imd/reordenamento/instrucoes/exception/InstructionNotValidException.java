package br.ufrn.imd.reordenamento.instrucoes.exception;

public class InstructionNotValidException extends RuntimeException {
    public InstructionNotValidException() {
    }

    public InstructionNotValidException(String message) {
        super(message);
    }
}
