package sudoku.model;

import java.util.Collection;
import java.util.List;

public class Board {

    private final List<List<Space>> spaces;

    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public boolean changeValue(int col, int row, int value) {
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

    public void reset() {
        spaces.forEach(line -> line.forEach(Space::clearSpace));
    }

    public GameStatusEnum getStatus() {
        boolean allEmpty = spaces.stream()
                .flatMap(Collection::stream)
                .noneMatch(s -> !s.isFixed() && s.getActual() != null);

        if (allEmpty) 
            return GameStatusEnum.NON_STARTED;

        boolean hasEmpty = spaces.stream()
                .flatMap(Collection::stream)
                .anyMatch(s -> s.getActual() == null);

        return hasEmpty ? GameStatusEnum.INCOMPLETE : GameStatusEnum.COMPLETE;
    }

    public boolean hasErrors() {
        if (getStatus() == GameStatusEnum.NON_STARTED) 
            return false;

        return spaces.stream()
                .flatMap(Collection::stream)
                .anyMatch(s -> s.getActual() != null && 
                !s.getActual().equals(s.getExpected())
                );
    }

    public boolean gameIsFinished() {
        return !hasErrors() && getStatus() == GameStatusEnum.COMPLETE;
    }
    
}
