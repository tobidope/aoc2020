package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.InputReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

public class Day6 {
    private static final Logger LOG = LoggerFactory.getLogger(Day6.class);

    private Day6() {
    }

    public static long distinctAnswers(final String answers) {
        return answers.chars()
                .filter(Character::isAlphabetic)
                .distinct()
                .count();
    }

    public static long solve1(final String input) {
        return InputReader.splitByEmptyLines(input)
                .mapToLong(Day6::distinctAnswers)
                .sum();
    }

    public static long everyoneAnsweredYes(final String answers) {
        LOG.info("Answers: {}", answers);
        final Map<Integer, Long> groupByChar = answers.trim().chars()
                .boxed()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        LOG.info("groupByChar: {}", groupByChar);
        final Long personCount = groupByChar.getOrDefault((int) '\n', 0L) + 1;
        LOG.info("Persons: {}", personCount);
        LOG.info("==========");
        return groupByChar.values().stream()
                .filter(answerCount -> answerCount.equals(personCount))
                .count();
    }

    public static long solve2(String input) {
        return InputReader.splitByEmptyLines(input)
                .mapToLong(Day6::everyoneAnsweredYes)
                .sum();
    }
}
