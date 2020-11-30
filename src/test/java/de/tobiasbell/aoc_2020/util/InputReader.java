package de.tobiasbell.aoc_2020.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class InputReader {

    private InputReader() {
    }

    public static String getInput(final int day) throws IOException {
        try (InputStream stream = InputReader.class.getResourceAsStream("/day" + day + ".txt")) {
            final byte[] bytes = stream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        }

    }
}
