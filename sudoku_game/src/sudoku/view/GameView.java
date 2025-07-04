package sudoku.view;

import java.util.List;
import java.util.Scanner;

import sudoku.model.Board;
import sudoku.model.Space;

public class GameView {

    private final Scanner input = new Scanner(System.in);
    private String[] args;
    private boolean isInitialBoard;

    public GameView(String... args) {
        this.args = args;
        isInitialBoard = false;
    }

    public void showMenu() {
        System.out.println("""
            \n🧩 MENU SUDOKU
            1 - Iniciar novo jogo
            2 - Inserir número
            3 - Remover número
            4 - Visualizar jogo atual
            5 - Verificar status
            6 - Limpar tabuleiro
            7 - Finalizar jogo
            8 - Sair
        """);
    }

    public void showBoard(Board board) {
        input.nextLine();

        if (board == null){
            clearScreen();
            System.out.println("⚠️ Jogo ainda não iniciado.");
            return;
        }
        clearScreen();

        Object[] args = new Object[81];
        List<List<Space>> spaces = board.getSpaces();

        int i = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Integer actual = spaces.get(row).get(col).getActual();
                args[i++] = (actual == null || actual == 0) ? " " 
                    : actual.toString();
            }
        }
        System.out.printf(BoardView.boardViewStringBuilder(), args);
        System.out.print("\nAperte Enter para continuar... ");
        input.nextLine();
        clearScreen();
        
    }

    public int readOption() {
        System.out.print("Escolha uma opção: ");
        return input.nextInt();
    }

    public boolean getInitialBoard() {
        return isInitialBoard;
    }

    public String[] readInitialBoard() {
        isInitialBoard = true;
        return args;
    }

    public void showStatusBoard() {
        clearScreen();
        if (isInitialBoard) {
            System.out.println("🕹️ Jogo já esta iniciado.");
            return;
        } else 
            System.out.println("✅ Jogo iniciado com sucesso.");
    }

    public void showInvalidOption() {
        clearScreen();
        System.out.println("❌ Opção inválida. Tente novamente.");
    }

    public void sayGoodbye() {
        System.out.println("👋 Obrigado por jogar! Até a próxima.");
    }

    private void clearScreen() {   
        System.out.print("\033[H\033[2J");   
        System.out.flush();  
    }
    
}
