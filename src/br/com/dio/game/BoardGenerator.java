package br.com.dio.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static br.com.dio.game.GameManager.BOARD_LIMIT;

public class BoardGenerator {
    private final Random random = new Random();

    public Map<String, String> generateRandomPositions() {
        Map<String, String> positions = new HashMap<>();

        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (int j = 0; j < BOARD_LIMIT; j++) {
                positions.put(String.format("%d,%d", i, j), "0,false");
            }
        }

        int fixedCount = 0;
        int maxFixed = BOARD_LIMIT * BOARD_LIMIT / 5;

        while (fixedCount < maxFixed) {
            int i = random.nextInt(BOARD_LIMIT);
            int j = random.nextInt(BOARD_LIMIT);
            String key = String.format("%d,%d", i, j);

            if (positions.get(key).endsWith("false")) {
                int value = random.nextInt(9) + 1;
                positions.put(key, value + ",true");
                fixedCount++;
            }
        }

        return positions;
    }
}