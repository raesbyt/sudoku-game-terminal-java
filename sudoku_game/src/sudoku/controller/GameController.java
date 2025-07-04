package sudoku.controller;

import sudoku.service.GameService;
import sudoku.view.GameView;

public class GameController {

    private GameService service;
    private GameView view;

    private boolean running;

    public GameController(GameService service, GameView view) {
        this.service = service;
        this.view = view;
        running = true;
    }

    public void run() {
        while (running) {
            view.showMenu();
            int option = view.readOption();
            handleOption(option);
        }
    }

    private void handleOption(int option) {
        switch (option) {
            case 1 -> {
                if (!view.getInitialBoard()) {
                    view.showStatusBoard();
                    service.startGame(view.readInitialBoard());
                }
                else 
                    view.showStatusBoard();
            }
            case 4 -> view.showBoard(service.getBoard());
            case 8 -> {
                view.sayGoodbye();
                running = false;
            }
            default -> view.showInvalidOption();
        }

    }
    
}
