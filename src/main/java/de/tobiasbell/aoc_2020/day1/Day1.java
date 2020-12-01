package de.tobiasbell.aoc_2020.day1;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Day1 {

    public static final int EXPECTED = 2020;

    public static long[] pairFinder(List<Long> numbers) {
        SortedSet<Long> numberSet = new TreeSet<>(numbers);
        for (var n : numberSet) {
            if (n > EXPECTED) {
                continue;
            }
            final long l = EXPECTED - n;
            final SortedSet<Long> lesser = numberSet.tailSet(n);
            if (lesser.contains(l)) {
                return new long[]{n, l};
            }

        }
        return new long[0];
    }

    public static long[] findTriple(List<Long> numbers) {
        SortedSet<Long> set = new TreeSet<>(numbers);
        for (var first : set) {
            final SortedSet<Long> rest = set.tailSet(first);
            for (var second : rest) {
                final SortedSet<Long> rest2 = rest.tailSet(second);
                final long l = EXPECTED - first - second;
                if (rest2.contains(l)) {
                    return new long[]{first, second, l};
                }
            }
        }
        return new long[0];
    }

    public static long solve1(final String input) {
        final long[] longs = pairFinder(Arrays.stream(input.split("\\R"))
                .map(Long::parseLong)
                .collect(Collectors.toList()));
        return longs[0] * longs[1];
    }

    public static long solve2(String input) {
        final long[] longs = findTriple(Arrays.stream(input.split("\\R"))
                .map(Long::parseLong)
                .collect(Collectors.toList()));
        return longs[0] * longs[1] * longs[2];
    }
}
