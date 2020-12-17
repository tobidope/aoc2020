package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day16 {

    private Day16() {
    }

    public static long solve1(final String puzzle) {
        final List<String> parts = Input.splitByEmptyLines(puzzle)
                .collect(Collectors.toList());
        List<Rule> rules = parseRules(parts.get(0));
        List<List<Integer>> nearbyTickets = parseTicketFields(parts.get(2));
        return nearbyTickets.stream()
                .flatMap(List::stream)
                .filter(number -> rules.stream().noneMatch(rule -> rule.test(number)))
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static long solve2(final String puzzle) {
        final List<String> parts = Input.splitByEmptyLines(puzzle)
                .collect(Collectors.toList());
        List<Rule> rules = parseRules(parts.get(0));
        var allRulesPredicate = rules.stream()
                .map(Predicate.class::cast)
                .reduce(n -> false, Predicate::or);
        List<Integer> ticket = parseTicketFields(parts.get(1)).get(0);
        List<List<Integer>> nearbyTickets = parseTicketFields(parts.get(2));
        var validTickets = nearbyTickets.stream()
                .filter(t -> t.stream().allMatch(allRulesPredicate))
                .collect(Collectors.toList());
        Map<String, Integer> ruleToPosition = findRulePositions(rules, validTickets);
        return ruleToPosition.entrySet().stream()
                .filter(e -> e.getKey().startsWith("departure"))
                .map(Map.Entry::getValue)
                .mapToLong(ticket::get)
                .reduce(1, (a, b) -> a * b);
    }

    private static Map<String, Integer> findRulePositions(List<Rule> rules, List<List<Integer>> validTickets) {
        var ticketSize = validTickets.get(0).size();
        Map<String, Integer> result = new HashMap<>();
        Map<Integer, List<Rule>> candidates = new HashMap<>();
        for (int position = 0; position < ticketSize; position++) {
            int i = position;
            var column = validTickets.stream().map(t -> t.get(i)).collect(Collectors.toList());
            var matchingRules =
                    rules.stream().filter(r -> column.stream()
                            .allMatch(r::test))
                            .filter(r -> !result.containsKey(r.getName()))
                            .collect(Collectors.toList());
            if (matchingRules.size() == 1) {
                final var ruleFound = matchingRules.get(0);
                result.put(ruleFound.getName(), position);
                candidates.values().forEach(ruleList -> ruleList.remove(ruleFound));
            } else {
                candidates.put(position, matchingRules);
            }
        }
        while (result.size() < ticketSize) {
            final var finalRule = candidates.entrySet().stream()
                    .filter(e -> e.getValue().size() == 1)
                    .findFirst()
                    .orElseThrow();
            candidates.remove(finalRule.getKey());
            final var rule = finalRule.getValue().get(0);
            candidates.values().forEach(ruleList -> ruleList.remove(rule));
            result.put(rule.getName(), finalRule.getKey());

        }
        return result;
    }


    private static List<List<Integer>> parseTicketFields(String tickets) {
        List<List<Integer>> ticketFields = new ArrayList<>();
        var lines = tickets.trim().split("\\R");
        for (int i = 1; i < lines.length; i++) {
            final List<Integer> numbers = Arrays.stream(lines[i].split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            ticketFields.add(numbers);
        }
        return ticketFields;
    }

    private static List<Rule> parseRules(String rules) {
        return Input.lines(rules)
                .map(Rule::of)
                .collect(Collectors.toList());
    }

    public static class Rule implements Predicate<Integer> {
        public static final Pattern RULE_PATTERN = Pattern.compile("([^:]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");
        private final String name;
        private final int firstLow;
        private final int firstHigh;
        private final int secondLow;
        private final int secondHigh;

        public Rule(String name, int firstLow, int firstHigh, int secondLow, int secondHigh) {
            this.name = name;
            this.firstLow = firstLow;
            this.firstHigh = firstHigh;
            this.secondLow = secondLow;
            this.secondHigh = secondHigh;
        }

        public static Rule of(final String input) {
            final Matcher m = RULE_PATTERN.matcher(input);
            if (m.matches()) {
                final var name = m.group(1);
                final var first = Integer.parseInt(m.group(2));
                final var second = Integer.parseInt(m.group(3));
                final var third = Integer.parseInt(m.group(4));
                final var fourth = Integer.parseInt(m.group(5));
                return new Rule(name, first, second, third, fourth);
            }
            throw new IllegalArgumentException("Couldn't parse input: " + input);
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean test(Integer value) {
            return (firstLow <= value && value <= firstHigh) || (secondLow <= value && value <= secondHigh);
        }
    }
}
