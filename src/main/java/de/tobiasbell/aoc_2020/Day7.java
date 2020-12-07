package de.tobiasbell.aoc_2020;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 {
    private Day7() {
    }

    public static Map<String, Set<String>> parseBags(final String input) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (var line : input.split("\\R")) {
            final String[] elements = line.split(" ");
            var color = elements[0] + " " + elements[1];
            if (line.endsWith("no other bags.")) {
                continue;
            }
            int index = 4;
            do {
                var containingColor = elements[index + 1] + " " + elements[index + 2];
                final Set<String> bags = graph.computeIfAbsent(containingColor, c -> new HashSet<>());
                bags.add(color);
                if (elements[index + 3].endsWith(".")) {
                    break;
                }
                index += 4;
            } while (true);
        }
        return graph;
    }

    public static long solve1(final String input) {
        final Map<String, Set<String>> graph = parseBags(input);
        return countBagsContaining(graph, "shiny gold").size();
    }

    private static Set<String> countBagsContaining(final Map<String, Set<String>> graph, final String color) {
        final Set<String> bagsHoldingColor = graph.getOrDefault(color, Collections.emptySet());
        return bagsHoldingColor.stream()
                .map(c -> countBagsContaining(graph, c))
                .reduce(bagsHoldingColor, (set1, set2) ->
                        Stream.concat(set1.stream(), set2.stream())
                                .collect(Collectors.toSet())
                );
    }


    public static long solve2(String input) {
        return 0;
    }

}
