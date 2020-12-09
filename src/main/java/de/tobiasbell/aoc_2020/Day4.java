package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public class Day4 {

    public static final Pattern ENTRY_RE = Pattern.compile("(\\w+):(\\S+)");

    public static Map<PassportEntry, String> readPassport(final String entry) {
        return ENTRY_RE.matcher(entry).results()
                .collect(toMap(
                        match -> PassportEntry.of(match.group(1)),
                        match -> match.group(2),
                        (s, s2) -> {
                            throw new IllegalArgumentException("Duplicate entries not allowed");
                        },
                        () -> new EnumMap<>(PassportEntry.class)));
    }

    public static boolean hasEnoughEntries(Map<PassportEntry, String> map) {
        return map.keySet().stream()
                .filter(PassportEntry::isMandatory)
                .count() == PassportEntry.MANDATORY_ENTRY_COUNT;
    }

    public static boolean hasValidValues(Map<PassportEntry, String> map) {
        return map.entrySet().stream()
                .allMatch(entry -> entry.getKey().validate(entry.getValue()));
    }

    public static long solve1(final String input) {
        return Input.splitByEmptyLines(input)
                .map(Day4::readPassport)
                .filter(Day4::hasEnoughEntries)
                .count();
    }

    public static long solve2(final String input) {
        return Input.splitByEmptyLines(input)
                .map(Day4::readPassport)
                .filter(Day4::hasEnoughEntries)
                .filter(Day4::hasValidValues)
                .count();
    }

    public enum PassportEntry {
        BYR {
            @Override
            public boolean validate(String birthYear) {
                return PassportEntry.isInRange(birthYear, 1920, 2002);

            }
        },
        IYR {
            @Override
            public boolean validate(String issueYear) {
                return PassportEntry.isInRange(issueYear, 2010, 2020);
            }
        },
        EYR {
            @Override
            public boolean validate(String expirationYear) {
                return PassportEntry.isInRange(expirationYear, 2020, 2030);
            }
        },
        HGT {
            @Override
            public boolean validate(String input) {
                if (input.length() < 4) {
                    return false;
                }
                final String height = input.substring(0, input.length() - 2);
                final String measure = input.substring(input.length() - 2);
                return switch (measure) {
                    case "cm" -> PassportEntry.isInRange(height, 150, 193);
                    case "in" -> PassportEntry.isInRange(height, 59, 76);
                    default -> throw new IllegalStateException("Unexpected value: " + measure);
                };
            }
        },
        HCL {
            @Override
            public boolean validate(String hairColor) {
                return Pattern.matches("#[0-9a-f]{6}", hairColor);
            }
        },
        ECL {
            @Override
            public boolean validate(String eyeColor) {
                return switch (eyeColor) {
                    case "amb", "blu", "brn", "gry", "grn", "hzl", "oth" -> true;
                    default -> false;
                };
            }
        },
        PID {
            @Override
            public boolean validate(String passportId) {
                return Pattern.matches("\\d{9}", passportId);
            }
        },
        CID(false) {
            @Override
            public boolean validate(String input) {
                return true;
            }
        };

        public static final long MANDATORY_ENTRY_COUNT =
                Arrays.stream(PassportEntry.values()).filter(PassportEntry::isMandatory).count();
        private final boolean mandatory;

        PassportEntry(boolean mandatory) {
            this.mandatory = mandatory;
        }

        PassportEntry() {
            this(true);
        }

        public static PassportEntry of(final String entry) {
            return Arrays.stream(PassportEntry.values())
                    .filter(p -> p.name().equalsIgnoreCase(entry))
                    .findFirst()
                    .orElseThrow();
        }

        private static boolean isInRange(final String digits, int min, int max) {
            final int i = Integer.parseInt(digits);
            return i >= min && i <= max;
        }

        public boolean isMandatory() {
            return mandatory;
        }

        public abstract boolean validate(final String input);
    }
}
