package br.com.dio.game;

import br.com.dio.ui.GameMenu;

public class SudokuGame {
    private final GameManager gameManager;
    private final GameMenu gameMenu;

    public SudokuGame() {
        this.gameManager = new GameManager();
        this.gameMenu = new GameMenu(gameManager);
    }

    public void start() {
        gameMenu.showMenu();
    }
}