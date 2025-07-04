package sudoku.controller;

import sudoku.model.GameStatusEnum;
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
                
                if (!service.isInvalid()) {
                    view.showStatusBoard(GameStatusEnum.STARTED);
                    service.startGame(view.readInitialBoard());
                }
                else 
                    view.showStatusBoard(GameStatusEnum.DEFAULT);
            }
            case 2 -> {
                if (service.isInvalid()) {
                    service.inputNumber(view.readCol(), view.readRow(), 
                    view.readValue());
                    view.clearView();
                } else
                    view.showStatusBoard(GameStatusEnum.NON_STARTED);
            }
            case 3 -> {
                if (service.isInvalid()) {
                    service.removeNumber(view.readCol(), view.readRow());
                    view.clearView();
                } else
                    view.showStatusBoard(GameStatusEnum.NON_STARTED);
            }
            case 4 -> view.showBoard(service.getBoard());
            case 5 -> {
                if (!service.isInvalid()) {
                    view.showStatusBoard(GameStatusEnum.NON_STARTED);
                } else
                    view.showStatusBoard(GameStatusEnum.DEFAULT);
            }
            case 6 -> {
                view.clearView();
                if (!service.isInvalid()) {
                    view.showStatusBoard(GameStatusEnum.NON_STARTED);

                } else
                    service.clearBoard();
            }
            case 7 -> {
                view.clearView();
                if (!service.isInvalid()) {
                    view.showStatusBoard(GameStatusEnum.NON_STARTED);

                } else if (service.finishGame()) {
                    view.showSuccess();
                }
            }
            case 8 -> {
                view.sayGoodbye();
                running = false;
            }
            default -> view.showInvalidOption();
        }

    }
    
}
