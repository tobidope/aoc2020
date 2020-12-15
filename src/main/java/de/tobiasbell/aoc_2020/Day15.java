package de.tobiasbell.aoc_2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day15 {

    public static long solve1(final String puzzle) {
        final List<Integer> startNumber = Arrays.stream(puzzle.trim().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new MemoryGame(startNumber).sayUntilTurn(2020);
    }

    public static long solve2(final String puzzle) {
        final List<Integer> startNumber = Arrays.stream(puzzle.trim().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new MemoryGame(startNumber).sayUntilTurn(30000000);
    }

    public static class MemoryGame {
        private final Map<Integer, Integer> numberToTurn = new HashMap<>();
        private int lastNumberSpoken;
        private int currentTurn;

        public MemoryGame(List<Integer> startingNumbers) {
            this.lastNumberSpoken = startingNumbers.get(startingNumbers.size() - 1);
            for (int i = 0; i < startingNumbers.size(); i++) {
                var number = startingNumbers.get(i);
                var turn = i + 1;
                numberToTurn.put(number, turn);
            }
            this.currentTurn = startingNumbers.size();
        }

        public int lastNumberSpoken() {
            return lastNumberSpoken;
        }

        public int lastTurn() {
            return currentTurn;
        }

        public int sayNextNumber() {
            return sayUntilTurn(currentTurn + 1);
        }

        public int sayUntilTurn(int turn) {
            while (turn > currentTurn) {
                final int turnSpoken = numberToTurn.getOrDefault(lastNumberSpoken, currentTurn);
                numberToTurn.put(lastNumberSpoken, currentTurn);
                lastNumberSpoken = currentTurn - turnSpoken;
                currentTurn++;
            }
            return lastNumberSpoken;
        }
    }
}
