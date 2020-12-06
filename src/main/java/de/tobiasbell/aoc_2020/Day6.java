package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.InputReader;

import java.util.Map;
import java.util.stream.Collectors;

public class Day6 {

    public static long distinctAnswers(final String answers) {
        return answers.chars()
                .distinct()
                .count();
    }

    public static long solve1(final String input) {
        return InputReader.splitByEmptyLines(input)
                .mapToLong(Day6::distinctAnswers)
                .sum();
    }

    public static long everoneAnsweredYes(String answers) {
        final Map<Integer, Long> groupByChar = answers.trim().chars()
                .boxed()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        final Long persons = groupByChar.getOrDefault((int) '\n', 0L) + 1;
        return groupByChar.values().stream()
                .filter(answerCount -> answerCount == persons)
                .mapToLong(i -> i)
                .count() - 1; // don't count the '\n';
    }

    public static long solve2(String input) {
        return InputReader.splitByEmptyLines(input)
                .mapToLong(Day6::everoneAnsweredYes)
                .sum();
    }
}
