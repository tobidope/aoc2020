package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.Map;
import java.util.stream.Collectors;

public class Day6 {
    private Day6() {
    }

    public static long distinctAnswers(final String answers) {
        return answers.chars()
                .filter(Character::isAlphabetic)
                .distinct()
                .count();
    }

    public static long solve1(final String input) {
        return Input.splitByEmptyLines(input)
                .mapToLong(Day6::distinctAnswers)
                .sum();
    }

    public static long everyoneAnsweredYes(final String answers) {
        final Map<Integer, Long> groupByChar = answers.trim().chars()
                .boxed()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        final Long personCount = groupByChar.getOrDefault((int) '\n', 0L) + 1;
        return groupByChar.values().stream()
                .filter(answerCount -> answerCount.equals(personCount))
                .count();
    }

    public static long solve2(String input) {
        return Input.splitByEmptyLines(input)
                .mapToLong(Day6::everyoneAnsweredYes)
                .sum();
    }
}
