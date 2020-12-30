package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.*;
import java.util.stream.Collectors;

public class Day19 {
    private Day19() {
    }

    public static long solve1(final String puzzle) {
        final List<String> input = Input.splitByEmptyLines(puzzle).collect(Collectors.toList());
        final Map<Integer, Rule> rules = parseRules(input.get(0));
        final Rule ruleZero = rules.get(0);
        return Input.lines(input.get(1))
                .map(s -> ruleZero.matches(s, rules))
                .filter(result -> result.equals(Optional.of("")))
                .count();
    }

    public static long solve2(final String puzzle) {
        final List<String> input = Input.splitByEmptyLines(puzzle).collect(Collectors.toList());
        final Map<Integer, Rule> rules = parseRules(input.get(0));
        var ruleReplacements = """
                8: 42 | 42 8
                11: 42 31 | 42 11 31""";
        rules.putAll(parseRules(ruleReplacements));
        final Rule ruleZero = rules.get(0);
        return Input.lines(input.get(1))
                .map(s -> ruleZero.matches(s, rules))
                .filter(result -> result.equals(Optional.of("")))
                .count();
    }

    public static Map<Integer, Rule> parseRules(final String input) {
        Map<Integer, Rule> rules = new HashMap<>();
        for (var line : input.split("\\R")) {
            final String[] split = line.split(":");
            final int ruleNumber = Integer.parseInt(split[0]);
            final String rule = split[1];
            if (rule.contains("\"")) {
                rules.put(ruleNumber, LiteralRule.parse(rule));
            } else if (rule.contains("|")) {
                rules.put(ruleNumber, OrRule.parse(rule));
            } else {
                rules.put(ruleNumber, BackreferenceRule.parse(rule));
            }
        }
        return rules;
    }

    public interface Rule {
        Optional<String> matches(final String input, Map<Integer, Rule> rules);
    }

    public static record LiteralRule(char c) implements Rule {

        public static LiteralRule parse(final String rule) {
            final char c = rule.trim().charAt(1);
            return new LiteralRule(c);
        }

        @Override
        public Optional<String> matches(String input, final Map<Integer, Rule> rule) {
            if (input.isEmpty()) {
                return Optional.empty();
            }
            return input.charAt(0) == c
                    ? Optional.of(input.substring(1))
                    : Optional.empty();
        }
    }

    public static record BackreferenceRule(List<Integer> backreferences) implements Rule {
        public static BackreferenceRule parse(final String rule) {
            final List<Integer> backreferences = Arrays.stream(rule.trim().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return new BackreferenceRule(backreferences);
        }

        @Override
        public Optional<String> matches(String input, final Map<Integer, Rule> rules) {
            Optional<String> result = Optional.of(input);
            for (int backreference : backreferences) {
                result = rules.get(backreference).matches(result.get(), rules);
                if (result.isEmpty()) {
                    break;
                }
            }
            return result;
        }
    }

    public static record OrRule(Rule left, Rule right) implements Rule {
        public static OrRule parse(String rule) {
            final String[] split = rule.split("\\|");
            return new OrRule(BackreferenceRule.parse(split[0]), BackreferenceRule.parse(split[1]));
        }

        @Override
        public Optional<String> matches(String input, final Map<Integer, Rule> rules) {
            Optional<String> match = left.matches(input, rules);
            if (match.isEmpty()) {
                match = right.matches(input, rules);
            }
            return match;
        }
    }

    public static record Match(String remaining) {
        public boolean isFullMatch() {
            return remaining.isEmpty();
        }
    }


}
