package de.tobiasbell.aoc_2020.day1;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day2 {
    public static boolean isValid(PasswordLine p) {
        final long count = p.line().chars()
                .filter(c -> c == p.c())
                .count();
        return count >= p.min() && count <= p.max();
    }

    public static boolean isTobboganValid(PasswordLine p) {
        boolean firstMatch = p.line().charAt(p.min() - 1) == p.c();
        boolean secondMatch = p.line().charAt(p.max() - 1) == p.c();
        return (firstMatch || secondMatch) && !(firstMatch && secondMatch);
    }

    public static long solve1(final String input) {
        return readPasswordLines(input)
                .filter(Day2::isValid)
                .count();
    }

    private static Stream<PasswordLine> readPasswordLines(String input) {
        return Arrays.stream(input.split("\\R"))
                .map(PasswordLine::of);
    }

    public static long solve2(final String input) {
        return readPasswordLines(input)
                .filter(Day2::isTobboganValid)
                .count();
    }

    public static record PasswordLine(int min, int max, char c, String line) {
        private static final Pattern LINE = Pattern.compile("^(\\d+)-(\\d+) ([a-z]): ([a-z]+)$");

        public static PasswordLine of(String line) {
            final Matcher m = LINE.matcher(line);
            if (m.matches()) {
                return new PasswordLine(Integer.parseInt(m.group(1)),
                        Integer.parseInt(m.group(2)),
                        m.group(3).charAt(0),
                        m.group(4));
            }
            throw new IllegalArgumentException("unknown input: " + line);
        }
    }
}
