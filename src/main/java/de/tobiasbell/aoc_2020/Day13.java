package de.tobiasbell.aoc_2020;

import java.util.List;
import java.util.stream.Collectors;

public class Day13 {
    public static long solve1(final String puzzle) {
        final Note note = Note.parse(puzzle);
        final List<Integer> numericLines = note.busLines().stream()
                .filter(s -> s.chars().allMatch(Character::isDigit))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int bestBus = -1;
        int minDepature = Integer.MAX_VALUE;
        for (final var currentBus : numericLines) {
            final int remainder = note.earliestDeparture % currentBus;
            final var departure = note.earliestDeparture + currentBus - remainder;
            if (minDepature > departure) {
                minDepature = departure;
                bestBus = currentBus;
            }
        }
        return bestBus * (minDepature - note.earliestDeparture);
    }

    public record Note(int earliestDeparture, List<String> busLines) {
        public static Note parse(final String puzzle) {
            final String[] split = puzzle.split("\\R");
            final int departure = Integer.parseInt(split[0]);
            final List<String> lines = List.of(split[1].split(","));
            return new Note(departure, lines);
        }
    }
}
