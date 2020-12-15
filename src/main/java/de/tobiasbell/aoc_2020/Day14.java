package de.tobiasbell.aoc_2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {
    public static final Pattern MEM_RE = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");

    public static long applyMask(final String bitMask, long value) {
        for (int i = 0; i < bitMask.length(); i++) {
            final char at = bitMask.charAt(i);
            final int bit = 35 - i;
            switch (at) {
                case '1' -> value |= (1L << bit);
                case '0' -> value &= ~(1L << bit);
            }
        }
        return value;
    }

    public static List<Long> generateAdresses(final String bitMask, long adress) {
        final List<Long> adresses = new ArrayList<>();
        final List<Integer> floatingBits = new ArrayList<>();
        for (int i = 0; i < bitMask.length(); i++) {
            final char at = bitMask.charAt(i);
            final int bit = 35 - i;
            switch (at) {
                case '1' -> adress |= (1L << bit);
                case 'X' -> floatingBits.add(bit);
            }
        }
        final var permutationSize = 1 << floatingBits.size();
        for (int permutation = 0; permutation < permutationSize; permutation++) {
            var newAdress = adress;
            var currentPermutation = permutation;
            for (int j = floatingBits.size() - 1; j >= 0; j--) {
                var bitToChange = floatingBits.get(j);
                var changeWith = currentPermutation & 1;
                currentPermutation >>= 1;
                if (changeWith == 1) {
                    newAdress |= (1L << bitToChange);
                } else {
                    newAdress &= ~(1L << bitToChange);
                }
            }
            adresses.add(newAdress);
        }
        return adresses;
    }

    public static long solve1(final String puzzle) {
        String mask = "";
        Map<Long, Long> memory = new HashMap<>();
        for (var line : puzzle.split("\\R")) {
            if (line.startsWith("mask")) {
                mask = line.split("=")[1].trim();
                continue;
            }
            final Matcher m = MEM_RE.matcher(line);
            if (m.matches()) {
                final long adress = Long.parseLong(m.group(1));
                long value = Long.parseLong(m.group(2));
                value = applyMask(mask, value);
                memory.put(adress, value);
            } else {
                throw new IllegalArgumentException("Can't parse line: " + line);
            }
        }
        return memory.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    public static long solve2(final String puzzle) {
        String mask = "";
        Map<Long, Long> memory = new HashMap<>();
        for (var line : puzzle.split("\\R")) {
            if (line.startsWith("mask")) {
                mask = line.split("=")[1].trim();
                continue;
            }
            final Matcher m = MEM_RE.matcher(line);
            if (m.matches()) {
                final long adress = Long.parseLong(m.group(1));
                long value = Long.parseLong(m.group(2));
                final List<Long> adresses = generateAdresses(mask, adress);
                adresses.forEach(a -> memory.put(a, value));
            } else {
                throw new IllegalArgumentException("Can't parse line: " + line);
            }
        }
        return memory.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }

}
