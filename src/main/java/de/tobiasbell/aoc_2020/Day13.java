package de.tobiasbell.aoc_2020;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {

    public static long timestampOffset(long timestamp, long busline) {
        final long remainder = timestamp % busline;
        if (remainder == 0) {
            return remainder;
        }
        return busline - remainder;
    }

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

    public static long solve2(final String puzzle) {
        final List<BusOffset> busses = BusOffset.parse(puzzle.split("\\R")[1]);
        long timeStamp = 0;
        long leastCommonMultiple = 1;

        for (int i = 0; i < busses.size() - 1; i++) {
            final var nextBus = busses.get(i + 1);
            final var currentBus = busses.get(i);
            leastCommonMultiple *= currentBus.busLine;
            while ((timeStamp + nextBus.offset) % nextBus.busLine != 0) {
                timeStamp += leastCommonMultiple;
            }
        }
        return timeStamp;
    }

    public record BusOffset(int busLine, int offset) {
        public static List<BusOffset> parse(final String input) {
            List<BusOffset> result = new ArrayList<>();
            final String[] split = input.trim().split(",");
            for (int i = 0; i < split.length; i++) {
                var entry = split[i];
                if (entry.equals("x")) {
                    continue;
                }
                result.add(new BusOffset(Integer.parseInt(entry), i));
            }
            return result;
        }
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
