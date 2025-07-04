package sudoku.service;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import sudoku.model.Board;
import sudoku.model.Space;

public class GameService {

    public Board board;

    public void startGame(String[] args) {
        Map<String, String> positions = Stream.of(args)
				.collect(toMap(
						k -> k.split(":")[0], 
						v -> v.split(":")[1]
				)
        );
        List<List<Space>> spaces = new ArrayList<>();

		for (var i = 0; i < 9; i++) {
            spaces.add(new ArrayList<>());

			for (var j = 0; j < 9; j++) {
				String positionKey = i + "," + j;
                Integer defaultVal = 0;
                Boolean fixed = false;

				if (positions.containsKey(positionKey)) {
					var config = positions.get(positionKey).split(",");
					defaultVal = Integer.parseInt(config[0]);
					fixed = Boolean.parseBoolean(config[1]);
				}
                spaces.get(i).add(new Space(defaultVal, fixed));
			}
        }
        board = new Board(spaces);
        
    }

    public Board getBoard() {
        return board;
    }

    public void inputNumber(int col, int row, int value) {
        if (!board.changeValue(col, row, value)) {
            System.out.printf("⚠️ A posição [%d,%d] é fixa e não "
                + "pode ser alterada.\n", col, row);
        }
    }

    public void removeNumber(int col, int row) {
        if (!board.clearValue(col, row)) {
            System.out.printf("⚠️ A posição [%d,%d] é fixa e não "
                + "pode ser alterada.\n", col, row);
        }
    }
    public void clearBoard() {
        if (!isInvalid()) 
            return;

        board.reset();
        System.out.println("🧼 Jogo limpo com sucesso.");
    }

    public boolean finishGame() {
        if (!isInvalid()) 
            return false;

        if (board.gameIsFinished()) {
            return true;
        } else {
            if (board.hasErrors()) {
                System.out.println("❌ O jogo contém erros. Verifique as "
                    + "posições incorretas.");
            } else {
                System.out.println("⏳ Ainda há espaços para preencher.");
            }
            return false;
        }
    }

    public boolean isInvalid() {
        if (board == null) {
            return false;
        }
        return true;
    }
    
}
