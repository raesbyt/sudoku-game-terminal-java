package sudoku.view;

import java.util.Scanner;

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
        if (isInitialBoard) {
            System.out.println("🕹️ Jogo já esta iniciado.");
            return;
        } else 
            System.out.println("✅ Jogo iniciado com sucesso.");
    }

    public void showInvalidOption() {
        System.out.println("❌ Opção inválida. Tente novamente.");
    }

    public void sayGoodbye() {
        System.out.println("👋 Obrigado por jogar! Até a próxima.");
    }
    
}
