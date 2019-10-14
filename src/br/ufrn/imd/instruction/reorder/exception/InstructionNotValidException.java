package br.ufrn.imd.instruction.reorder.exception;

public class InstructionNotValidException extends RuntimeException {
    public InstructionNotValidException() {
    }

    public InstructionNotValidException(String message) {
        super(message);
    }
}
