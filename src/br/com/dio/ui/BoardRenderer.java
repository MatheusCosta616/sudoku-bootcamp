package br.com.dio.ui;

import br.com.dio.model.Board;

import static br.com.dio.game.GameManager.BOARD_LIMIT;
import static br.com.dio.util.BoardTemplate.BOARD_TEMPLATE;
import static java.util.Objects.isNull;

public class BoardRenderer {

    public void renderBoard(Board board) {
        Object[] args = new Object[81];
        int argPos = 0;

        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (var col : board.getSpaces()) {
                args[argPos++] = " " + ((isNull(col.get(i).getActual())) ? " " : col.get(i).getActual());
            }
        }

        System.out.println("Seu jogo se encontra da seguinte forma");
        System.out.printf((BOARD_TEMPLATE) + "\n", args);
    }
}