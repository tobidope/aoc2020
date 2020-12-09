package de.tobiasbell.aoc_2020;

import java.util.List;
import java.util.stream.Collectors;

import static de.tobiasbell.aoc_2020.util.Input.lines;

public class Day1 {
    public static final int EXPECTED = 2020;

    private Day1() {
    }

    public static long[] pairFinder(List<Long> numbers) {
        for (var i : numbers) {
            for (var j : numbers) {
                if (i + j == EXPECTED) {
                    return new long[]{i, j};
                }
            }
        }
        return new long[0];
    }

    public static long[] findTriple(List<Long> numbers) {
        for (var i : numbers) {
            for (var j : numbers) {
                for (var k : numbers) {
                    if (i + j + k == EXPECTED) {
                        return new long[]{i, j, k};
                    }
                }
            }
        }
        return new long[0];
    }

    public static long solve1(final String input) {
        final long[] longs = pairFinder(lines(input)
                .map(Long::parseLong)
                .collect(Collectors.toList()));
        return longs[0] * longs[1];
    }

    public static long solve2(String input) {
        final long[] longs = findTriple(lines(input)
                .map(Long::parseLong)
                .collect(Collectors.toList()));
        return longs[0] * longs[1] * longs[2];
    }
}
