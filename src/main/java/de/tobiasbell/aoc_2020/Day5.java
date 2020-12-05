package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.InputReader;

import java.util.Set;
import java.util.stream.Collectors;

public class Day5 {

    public static long solve1(final String input) {
        return InputReader.lines(input)
                .map(Day5::computeSeat)
                .mapToLong(Seat::seatId)
                .max()
                .orElseThrow();
    }

    public static long solve2(final String input) {
        return findMissingSeat(input).seatId();
    }

    public static Seat findMissingSeat(final String input) {
        final Set<Seat> seats = InputReader.lines(input)
                .map(Day5::computeSeat)
                .collect(Collectors.toSet());

        final Set<Integer> seatIds = seats.stream()
                .map(Seat::seatId)
                .collect(Collectors.toSet());

        for (int row = 1; row < 127; row++) {
            for (int column = 0; column < 8; column++) {
                final Seat candidateSeat = new Seat(row, column);
                if (!seats.contains(candidateSeat)
                        && seatIds.contains(candidateSeat.seatId() + 1)
                        && seatIds.contains(candidateSeat.seatId() - 1)) {
                    return candidateSeat;
                }
            }
        }
        throw new RuntimeException("Haven't found a seat");
    }

    public static Seat computeSeat(final String input) {
        int[] rows = {0, 127};
        int toRemove = 64;
        for (int i = 0; i < 7; i++) {
            final char c = input.charAt(i);
            switch (c) {
                case 'F' -> rows[1] -= toRemove;
                case 'B' -> rows[0] += toRemove;
            }
            toRemove /= 2;
        }
        int[] columns = {0, 7};
        toRemove = 4;
        for (int i = 7; i < input.length(); i++) {
            final char c = input.charAt(i);
            switch (c) {
                case 'L' -> columns[1] -= toRemove;
                case 'R' -> columns[0] += toRemove;
            }
            toRemove /= 2;
        }
        return new Seat(rows[0], columns[0]);
    }

    public record Seat(int row, int column) {
        public int seatId() {
            return row * 8 + column;
        }
    }

}
