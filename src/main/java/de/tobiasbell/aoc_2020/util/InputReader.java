package de.tobiasbell.aoc_2020.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class InputReader {

    public static final Pattern EMPTY_LINE_RE = Pattern.compile("^\\s*$", Pattern.MULTILINE);

    private InputReader() {
    }

    public static String getInput(final int day) {
        try (InputStream stream = InputReader.class.getResourceAsStream("/day" + day + ".txt")) {
            final byte[] bytes = stream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public static Stream<String> lines(final String input) {
        return Arrays.stream(input.split("\\R"));
    }

    public static Stream<String> splitByEmptyLines(final String input) {
        return Arrays.stream(EMPTY_LINE_RE.split(input));
    }
}
