package application;

import sudoku.controller.GameController;
import sudoku.service.GameService;
import sudoku.view.GameView;

public class App {

    public static void main(String... args) {

        GameService service = new GameService();
        GameView view = new GameView(args);
        GameController controller = new GameController(service, view);
        controller.run();

    }

}
