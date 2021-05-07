package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Day9 {
    private Day9() {
    }

    public static long firstNotSumOfTwo(List<Long> numbers, int preamble) {
        for (int i = preamble; i < numbers.size(); i++) {
            List<Long> preambleNumbers = numbers.subList(i - preamble, preamble + i);
            final long candidate = numbers.get(i);
            boolean found = false;

            for (var first : preambleNumbers) {
                final long result = candidate - first;
                if (preambleNumbers.contains(result)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return candidate;
            }
        }
        throw new NoSuchElementException();
    }

    public static long solve1(final String input, int preamble) {
        final List<Long> numbers = parseNumbers(input);
        return firstNotSumOfTwo(numbers, preamble);
    }

    public static long solve2(final String input, long invalidNumber) {
        final List<Long> numbers = parseNumbers(input);
        return findWeakness(numbers, invalidNumber);
    }

    private static long findWeakness(List<Long> numbers, long invalidNumber) {
        for (int i = 0; i < numbers.size() - 2; i++) {
            for (int j = i + 1; j < numbers.size() - 1; j++) {
                final List<Long> subList = numbers.subList(i, j + 1);
                final long sum = subList.stream().mapToLong(l -> l).sum();
                if (sum == invalidNumber) {
                    final long min = subList.stream().mapToLong(l -> l).min().orElseThrow();
                    final long max = subList.stream().mapToLong(l -> l).max().orElseThrow();
                    return min + max;
                }
                if (sum > invalidNumber) {
                    break;
                }
            }
        }
        throw new NoSuchElementException();
    }

    private static List<Long> parseNumbers(String input) {
        return Input.lines(input)
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }


}
