package sudoku.model;

public enum GameStatusEnum {

    STARTED("iniciado"),
    NON_STARTED("não iniciado"),
    INCOMPLETE("incompleto"),
    COMPLETE("completo"),
    DEFAULT("default");

    private final String label;

    GameStatusEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
}
