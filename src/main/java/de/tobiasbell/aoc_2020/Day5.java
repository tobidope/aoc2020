package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day5 {
    private Day5() {
    }

    public static long solve1(final String input) {
        return Input.lines(input)
                .mapToLong(Day5::computeSeatId)
                .max()
                .orElseThrow();
    }

    public static long solve2(final String input) {
        return findMissingSeatId(input);
    }

    public static long findMissingSeatId(final String input) {
        final List<Long> sortedSeatIds = Input.lines(input)
                .map(Day5::computeSeatId)
                .sorted()
                .collect(Collectors.toList());
        final long minSeatId = sortedSeatIds.get(0);
        final long maxSeatId = sortedSeatIds.get(sortedSeatIds.size() - 1);
        // The difference of the sum of all numbers between minSeatId and maxSeatId
        // and the sum of all sortedSeatIds is the missing seatId
        return LongStream
                .rangeClosed(minSeatId, maxSeatId)
                .sum()
                - sortedSeatIds.stream()
                .mapToLong(i -> i)
                .sum();
    }

    public static long computeSeatId(final String input) {
        final String binaryNumber = input
                .replaceAll("[BR]", "1")
                .replaceAll("[FL]", "0");
        return Integer.parseInt(binaryNumber, 2);
    }


}
