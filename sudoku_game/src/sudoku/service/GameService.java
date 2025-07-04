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
    
}
