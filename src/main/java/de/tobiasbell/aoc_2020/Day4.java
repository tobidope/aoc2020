package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.InputReader;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 {
    public static Map<PassportEntry, String> readPassport(final String entry) {
        Map<PassportEntry, String> passport = new EnumMap<>(PassportEntry.class);
        final String entries = Arrays.stream(PassportEntry.values())
                .map(PassportEntry::name)
                .map(String::toLowerCase)
                .collect(Collectors.joining("|"));
        final Pattern p = Pattern.compile("(" + entries + ")" + ":(\\S+)", Pattern.MULTILINE);
        final Matcher matcher = p.matcher(entry);
        while (matcher.find()) {
            final PassportEntry passportEntry = PassportEntry.of(matcher.group(1));
            passport.put(passportEntry, matcher.group(2));
        }

        return passport;
    }

    public static boolean hasEnoughEntries(Map<PassportEntry, String> map) {
        return map.keySet().stream()
                .filter(p -> p.isMandatory())
                .count() == PassportEntry.MANDATORY_ENTRIY_COUNT;
    }

    public static boolean hasValidValues(Map<PassportEntry, String> map) {
        return map.entrySet().stream()
                .allMatch(entry -> entry.getKey().validate(entry.getValue()));
    }

    public static long solve1(final String input) {
        return InputReader.splitByEmptyLines(input)
                .map(Day4::readPassport)
                .filter(Day4::hasEnoughEntries)
                .count();
    }

    public static long solve2(final String input) {
        return InputReader.splitByEmptyLines(input)
                .map(Day4::readPassport)
                .filter(Day4::hasEnoughEntries)
                .filter(Day4::hasValidValues)
                .count();
    }

    public enum PassportEntry {
        BYR {
            @Override
            public boolean validate(String input) {
                final int birthYear = Integer.parseInt(input);
                return birthYear >= 1920 && birthYear <= 2002;

            }
        },
        IYR {
            @Override
            public boolean validate(String input) {
                final int issueYear = Integer.parseInt(input);
                return issueYear >= 2010 && issueYear <= 2020;
            }
        },
        EYR {
            @Override
            public boolean validate(String input) {
                final int expirationYear = Integer.parseInt(input);
                return expirationYear >= 2020 && expirationYear <= 2030;
            }
        },
        HGT {
            @Override
            public boolean validate(String input) {
                if (input.length() < 4) {
                    return false;
                }
                final int height = Integer.parseInt(input.substring(0, input.length() - 2));
                final String measure = input.substring(input.length() - 2);
                return switch (measure) {
                    case "cm" -> height >= 150 && height <= 193;
                    case "in" -> height >= 59 && height <= 76;
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

        public static long MANDATORY_ENTRIY_COUNT = Arrays.stream(PassportEntry.values()).filter(PassportEntry::isMandatory).count();
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
                    .orElseThrow(() -> new IllegalArgumentException("Unknown entry: " + entry));
        }

        public boolean isMandatory() {
            return mandatory;
        }

        public abstract boolean validate(String input);
    }
}
