package de.tobiasbell.aoc_2020.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class Input {

    public static final Pattern EMPTY_LINE_RE = Pattern.compile("^\\s*$", Pattern.MULTILINE);

    private Input() {
    }

    public static String puzzleInput(final int day) {
        return readResource("day" + day + ".txt");
    }

    public static String readResource(final String name) {
        try (InputStream stream = Input.class.getResourceAsStream("/" + name)) {
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
