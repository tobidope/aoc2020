package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day16 {

    public static long solve1(final String puzzle) {
        final List<String> parts = Input.splitByEmptyLines(puzzle)
                .collect(Collectors.toList());
        Map<String, Set<Integer>> rules = parseRules(parts.get(0));
        List<List<Integer>> nearbyTickets = parseTicketFields(parts.get(2));
        return nearbyTickets.stream()
                .flatMap(List::stream)
                .filter(number -> rules.values().stream().noneMatch(s -> s.contains(number)))
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static long solve2(final String puzzle) {
        final List<String> parts = Input.splitByEmptyLines(puzzle)
                .collect(Collectors.toList());
        Map<String, Set<Integer>> rules = parseRules(parts.get(0));
        List<Integer> myTicket = parseTicketFields(parts.get(1)).get(0);
        List<List<Integer>> nearbyTickets = parseTicketFields(parts.get(2));
        var validTickets = nearbyTickets.stream()
                .filter(ticket -> ticket.stream()
                        .allMatch(number -> rules.values().stream()
                                .anyMatch(s -> s.contains(number))))
                .collect(Collectors.toList());
        Map<String, Integer> rulePosition = findRulePosition(rules, validTickets);
        return rulePosition.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("departure"))
                .map(Map.Entry::getValue)
                .mapToInt(myTicket::get)
                .reduce(1, (a, b) -> a * b)
                ;
    }

    private static Map<String, Integer> findRulePosition(Map<String, Set<Integer>> rules, List<List<Integer>> validTickets) {
        var ticketLength = validTickets.get(0).size();
        Map<String, Integer> rulePosition = new HashMap<>();
        for (int position = 0; position < ticketLength; position++) {
            var i = position;
            final Set<Integer> valuesOnPosition = validTickets.stream().map(t -> t.get(i)).collect(Collectors.toSet());
            final String rule = rules.entrySet().stream()
                    .filter(e -> e.getValue().containsAll(valuesOnPosition))
                    .filter(e -> !rulePosition.containsKey(e))
                    .findFirst()
                    .map(Map.Entry::getKey).get();
            rulePosition.put(rule, position);
        }
        return rulePosition;
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

    private static Map<String, Set<Integer>> parseRules(String rules) {
        final Pattern ranges = Pattern.compile("(\\d+)-(\\d+)");
        var ruleMap = new HashMap<String, Set<Integer>>();
        for (var line : rules.split("\\R")) {
            final String[] split = line.split(":");
            String key = split[0];
            Set<Integer> set = ranges.matcher(rules).results()
                    .flatMap(m ->
                            IntStream.rangeClosed(
                                    Integer.parseInt(m.group(1)),
                                    Integer.parseInt(m.group(2)))
                                    .boxed())
                    .collect(Collectors.toSet());
            ruleMap.put(key, set);
        }
        return ruleMap;
    }


}
