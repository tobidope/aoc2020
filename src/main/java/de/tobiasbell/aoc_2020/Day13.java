package de.tobiasbell.aoc_2020;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
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
        SortedMap<Integer, Integer> offsetToLine = parse(puzzle);
        return findFirstMatchingOffsets(offsetToLine, 100000000000000L);
    }

    public static long findFirstMatchingOffsets(SortedMap<Integer, Integer> offsetToLine, long startTimeStamp) {
        long result = -1;
        final Map.Entry<Integer, Integer> maxValueEntry = offsetToLine.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get();
        var offset = maxValueEntry.getKey();
        var step = maxValueEntry.getValue();
        var start = startTimeStamp - timestampOffset(startTimeStamp, step) + step - offset;
        for (long i = start; ; i += step) {
            long candidate = i;
            var found = offsetToLine.entrySet().stream()
                    .allMatch(entry -> timestampOffset(candidate, entry.getValue()) == entry.getKey());
            if (found) {
                result = candidate;
                break;
            }
        }
        return result;
    }

    public static SortedMap<Integer, Integer> parse(String puzzle) {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        final String[] split = puzzle.split("\\R");
        final String[] busLines = split[1].split(",");
        for (int i = 0; i < busLines.length; i++) {
            if (busLines[i].equals("x")) {
                continue;
            }
            map.put(i, Integer.parseInt(busLines[i]));
        }
        return map;
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
