package sudoku.model;

import java.util.List;

public class Board {

    private final List<List<Space>> spaces;

    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public boolean changeValue(final int col, final int row, final int value) {
        Space space = spaces.get(row).get(col);
        if (space.isFixed()) 
        return false;

        space.setActual(value);
        return true;
    }

    public boolean clearValue(int col, int row) {
        var space = spaces.get(row).get(col);
        if (space.isFixed()) 
            return false;

        space.clearSpace();
        return true;
    }
    
}
