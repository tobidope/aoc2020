package de.tobiasbell.aoc_2020.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class InputReader {

    private InputReader() {
    }

    public static String getInput(final int day) throws IOException {
        try (InputStream stream = InputReader.class.getResourceAsStream("/day" + day + ".txt")) {
            final byte[] bytes = stream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    public static <T> List<T> readLines(final int day, Function<String, T> converter) throws IOException {
        return Arrays.stream(getInput(day).split("\\R"))
                .map(converter)
                .collect(Collectors.toList());
    }
}
