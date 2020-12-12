package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 {
    private Day10() {
    }

    public static List<Long> parseAdaptersSorted(final String input) {
        return Input.lines(input)
                .map(Long::parseLong)
                .sorted()
                .collect(Collectors.toList());
    }

    public static Map<Long, Long> joltDifferenceDistribution(final Collection<Long> adapters) {
        var currentJolt = 0L;
        var map = new HashMap<Long, Long>();
        for (var adapter : adapters) {
            final long difference = adapter - currentJolt;
            currentJolt = adapter;
            map.merge(difference, 1L, Long::sum);
        }
        map.merge(3L, 1L, Long::sum);
        return map;
    }

    public static long arrangements(final List<Long> adapters) {
        Map<Long, Long> countMap = new HashMap<>();
        countMap.put(0L, 1L);
        for (var adapter :
                adapters) {
            final long sum =
                    countMap.getOrDefault(adapter - 1, 0L)
                            + countMap.getOrDefault(adapter - 2, 0L)
                            + countMap.getOrDefault(adapter - 3, 0L);
            countMap.put(adapter, sum);
        }
        return countMap.get(adapters.get(adapters.size() - 1));
    }

    public static long solve1(String puzzle) {
        final Collection<Long> adaptersSorted = parseAdaptersSorted(puzzle);
        final Map<Long, Long> distribution = joltDifferenceDistribution(adaptersSorted);
        return distribution.get(1L) * distribution.get(3L);
    }

    public static long solve2(String puzzle) {
        final List<Long> adaptersSorted = parseAdaptersSorted(puzzle);
        return arrangements(adaptersSorted);

    }
}
