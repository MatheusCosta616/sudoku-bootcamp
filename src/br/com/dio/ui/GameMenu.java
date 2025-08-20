package br.com.dio.ui;

import br.com.dio.game.GameManager;

import java.util.Scanner;

public class GameMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final GameManager gameManager;

    public GameMenu(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nSelecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            int option = scanner.nextInt();

            switch (option) {
                case 1 -> gameManager.startNewGame();
                case 2 -> handleInputNumber();
                case 3 -> handleRemoveNumber();
                case 4 -> gameManager.showCurrentGame();
                case 5 -> gameManager.showGameStatus();
                case 6 -> handleClearGame();
                case 7 -> gameManager.finishGame();
                case 8 -> {
                    System.out.println("Obrigado por jogar!");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
            }
        }
    }

    private void handleInputNumber() {
        if (!gameManager.isGameStarted()) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Informe a coluna em que o número será inserido");
        int col = getValidNumber(0, 8);
        System.out.println("Informe a linha em que o número será inserido");
        int row = getValidNumber(0, 8);
        System.out.printf("Informe o número que vai entrar na posição [%s,%s]\n", col, row);
        int value = getValidNumber(1, 9);

        gameManager.inputNumber(col, row, value);
    }

    private void handleRemoveNumber() {
        if (!gameManager.isGameStarted()) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Informe a coluna em que o número será removido");
        int col = getValidNumber(0, 8);
        System.out.println("Informe a linha em que o número será removido");
        int row = getValidNumber(0, 8);

        gameManager.removeNumber(col, row);
    }

    private void handleClearGame() {
        if (!gameManager.isGameStarted()) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu progresso? (sim/não)");
        String confirm = scanner.next();
        while (!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("não")) {
            System.out.println("Informe 'sim' ou 'não'");
            confirm = scanner.next();
        }

        if (confirm.equalsIgnoreCase("sim")) {
            gameManager.clearGame();
        }
    }

    private int getValidNumber(final int min, final int max) {
        int current = scanner.nextInt();
        while (current < min || current > max) {
            System.out.printf("Informe um número entre %s e %s\n", min, max);
            current = scanner.nextInt();
        }
        return current;
    }
}