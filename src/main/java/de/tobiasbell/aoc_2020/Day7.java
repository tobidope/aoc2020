package de.tobiasbell.aoc_2020;

import java.util.*;
import java.util.regex.Pattern;
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

    private static long bagsInside(final Map<String, List<BagRule>> graph, final String color) {
        final List<BagRule> rules = graph.getOrDefault(color, Collections.emptyList());
        return rules.stream()
                .map(c -> c.count() + c.count() * bagsInside(graph, c.color()))
                .mapToLong(l -> l)
                .sum();
    }

    public static long solve2(String input) {
        final Map<String, List<BagRule>> graph = parseBagGraph(input);
        return bagsInside(graph, "shiny gold");
    }

    public static Map<String, List<BagRule>> parseBagGraph(final String input) {
        Map<String, List<BagRule>> graph = new HashMap<>();
        for (var line : input.split("\\R")) {
            final String[] split = line.split(" ");
            var color = split[0] + " " + split[1];
            graph.put(color, BagRule.parse(line));
        }
        return graph;
    }

    public static record BagRule(String color, int count) {
        private static final Pattern BAG_RULE_RE = Pattern.compile("(\\d+) (\\w+ \\w+) bags?[,.]");

        public static List<BagRule> parse(final String input) {
            return BAG_RULE_RE.matcher(input).results()
                    .map(m -> new BagRule(m.group(2), Integer.parseInt(m.group(1))))
                    .collect(Collectors.toList());
        }
    }

}
