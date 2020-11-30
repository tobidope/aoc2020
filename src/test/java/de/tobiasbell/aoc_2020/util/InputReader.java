package de.tobiasbell.aoc_2020.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class InputReader {

    private InputReader() {
    }

    public static final String getInput(final int day) throws IOException {
        final byte[] bytes = InputReader.class.getResourceAsStream("/day" + day + ".txt").readAllBytes();
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
