package de.tobiasbell.aoc_2020.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class InputReader {

    public static final Pattern EMPTY_LINE_RE = Pattern.compile("^\\s*$", Pattern.MULTILINE);

    private InputReader() {
    }

    public static String loadFromSite(final int day) throws IOException, InterruptedException {
        final HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NEVER)
                .connectTimeout(Duration.ofSeconds(2))
                .build();
        final HttpRequest request = HttpRequest.newBuilder(
                URI.create(String.format("https://adventofcode.com/2020/day/%d/input", day)))
                .header("Cookie", "session=")
                .build();

        final HttpResponse<String> data = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        return data.body();
    }

    public static String getInput(final int day) throws IOException {
        try (InputStream stream = InputReader.class.getResourceAsStream("/day" + day + ".txt")) {
            final byte[] bytes = stream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    public static Stream<String> lines(final String input) {
        return Arrays.stream(input.split("\\R"));
    }

    public static Stream<String> splitByEmptyLines(final String input) {
        return Arrays.stream(EMPTY_LINE_RE.split(input));
    }
}
