package sudoku.model;

public class Space {

    private Integer actual;
    private final int expected;
    private final boolean fixed;

    public Space(int expected, boolean fixed) {
        this.expected = expected;
        this.fixed = fixed;
        if (fixed) {
            this.actual = expected;
        }
    }

    public Integer getActual() {
        return actual;
    }

    public int getExpected() {
        return expected;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setActual(Integer actual) {
        if (!fixed) {
            this.actual = actual;
        }
    }

    public void clearSpace() {
        if (!fixed) {
            this.actual = null;
        }
    }
    
}
