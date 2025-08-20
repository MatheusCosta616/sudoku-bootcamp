package br.com.dio.game;

import br.com.dio.model.Board;
import br.com.dio.model.Space;
import br.com.dio.ui.BoardRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class GameManager {
    private Board board;
    private final BoardGenerator boardGenerator;
    private final BoardRenderer boardRenderer;
    public static final int BOARD_LIMIT = 9;

    public GameManager() {
        this.boardGenerator = new BoardGenerator();
        this.boardRenderer = new BoardRenderer();
    }

    public boolean startNewGame() {
        if (nonNull(board)) {
            System.out.println("Reiniciando o jogo com um novo tabuleiro...");
            board = null;
        }

        Map<String, String> positions = boardGenerator.generateRandomPositions();
        List<List<Space>> spaces = new ArrayList<>();

        try {
            for (int i = 0; i < BOARD_LIMIT; i++) {
                spaces.add(new ArrayList<>());
                for (int j = 0; j < BOARD_LIMIT; j++) {
                    String positionConfig = positions.get("%s,%s".formatted(i, j));
                    if (positionConfig == null) {
                        System.out.printf("Configuração da posição [%s,%s] não encontrada.\n", i, j);
                        return false;
                    }
                    String[] split = positionConfig.split(",");
                    if (split.length < 2) {
                        System.out.printf("Configuração da posição [%s,%s] está incompleta!\n", i, j);
                        return false;
                    }
                    int expected = Integer.parseInt(split[0]);
                    boolean fixed = Boolean.parseBoolean(split[1]);
                    Space currentSpace = new Space(expected, fixed);
                    spaces.get(i).add(currentSpace);
                }
            }

            board = new Board(spaces);
            System.out.println("O jogo está pronto para começar");
            showCurrentGame();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao iniciar o jogo: " + e.getMessage());
            return false;
        }
    }

    public boolean inputNumber(int col, int row, int value) {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return false;
        }

        if (!board.changeValue(col, row, value)) {
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
            return false;
        }

        System.out.println("Número inserido com sucesso!");
        return true;
    }

    public boolean removeNumber(int col, int row) {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return false;
        }

        if (!board.clearValue(col, row)) {
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
            return false;
        }

        System.out.println("Número removido com sucesso!");
        return true;
    }

    public void showCurrentGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        boardRenderer.renderBoard(board);
    }

    public void showGameStatus() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.printf("O jogo atualmente se encontra no status %s\n", board.getStatus().getLabel());
        if (board.hasErrors()) {
            System.out.println("O jogo contém erros");
        } else {
            System.out.println("O jogo não contém erros");
        }
    }

    public void clearGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        board.reset();
        System.out.println("Jogo limpo com sucesso!");
    }

    public boolean finishGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return false;
        }

        if (board.gameIsFinished()) {
            System.out.println("Parabéns você concluiu o jogo");
            showCurrentGame();
            board = null;
            return true;
        } else if (board.hasErrors()) {
            System.out.println("Seu jogo contém erros, verifique seu board e ajuste-o");
        } else {
            System.out.println("Você ainda precisa preencher algum espaço");
        }
        return false;
    }

    public boolean isGameStarted() {
        return nonNull(board);
    }
}