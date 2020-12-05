package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.InputReader;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Day5 {

    public static long solve1(final String input) {
        return InputReader.lines(input)
                .mapToLong(Day5::computeSeatId)
                .max()
                .orElseThrow();
    }

    public static long solve2(final String input) {
        return findMissingSeatId(input);
    }

    public static long findMissingSeatId(final String input) {
        final List<Long> seatIds = InputReader.lines(input)
                .map(Day5::computeSeatId)
                .sorted()
                .collect(Collectors.toList());
        for (int i = 0; i < seatIds.size() - 1; i++) {
            final Long seatA = seatIds.get(i);
            final Long seatB = seatIds.get(i + 1);
            if (seatB == seatA + 2) {
                return seatA + 1;
            }
        }
        throw new NoSuchElementException();
    }

    public static long computeSeatId(final String input) {
        final String binaryNumber = input
                .replaceAll("[BR]", "1")
                .replaceAll("[FL]", "0");
        return Integer.parseInt(binaryNumber, 2);
    }


}
