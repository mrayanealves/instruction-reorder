package br.ufrn.imd.instruction.reorder.model;

public class Instruction {
    // Operação da instrução
    private String operation;

    // Registrador de destino da instrução
    private String destionationRecorder;

    // Primeiro regisrador para a operação
    private String firstOperationRecorder;

    // Segundo regisrador para a operação
    private String lastOperationRecorder;

    /**
     * Construtores
     */
    public Instruction() {
    }

    public Instruction(String operation, String destionationRecorder, String firstOperationRecorder, String lastOperationRecorder) {
        this.operation = operation;
        this.destionationRecorder = destionationRecorder;
        this.firstOperationRecorder = firstOperationRecorder;
        this.lastOperationRecorder = lastOperationRecorder;
    }

    /**
     * Geters e Seters
     * @return
     */
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

    /**
     * Verifica se uma instrução depende de outra
     * @param instruction Instruction para comparação
     * @return boolean se depende ou não
     */
    public boolean isDependent(Instruction instruction) {
        boolean isDependent = false;

        if (instruction != null){
            if ((instruction.getDestionationRecorder().equals(this.getFirstOperationRecorder())) ||
                    (instruction.getDestionationRecorder().equals(this.getLastOperationRecorder()))){
                isDependent = true;
            }
        }

        return isDependent;
    }

    /**
     * Verifica se há falsa dependência ou não
     * @param instruction Instruction para comparação
     * @return boolean se é falso dependente ou não
     */
    public boolean isFalseDependent(Instruction instruction){
        boolean isFalseDependent = false;

        if (this.getDestionationRecorder().equals(instruction.getDestionationRecorder())){
            isFalseDependent = true;
        }

        return isFalseDependent;
    }

    /**
     * Verifica se uma instrução é igual a outra
     * @param instruction Instruction para comparação
     * @return boolean se é igual ou não
     */
    public boolean equals(Instruction instruction){
        boolean equals = false;

        if ((this.getOperation().equals(instruction.getOperation())) &&
                (this.getDestionationRecorder().equals(instruction.getDestionationRecorder())) &&
                (this.getFirstOperationRecorder().equals(instruction.getFirstOperationRecorder())) &&
                (this.getLastOperationRecorder().equals(instruction.getLastOperationRecorder()))){
            equals = true;
        }

        return equals;
    }

    /**
     * To String
     */
    @Override
    public String toString() {
        return operation + " " + destionationRecorder
                + " " + firstOperationRecorder + " "
                + lastOperationRecorder;
    }
}
