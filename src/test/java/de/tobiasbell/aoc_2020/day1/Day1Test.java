package de.tobiasbell.aoc_2020.day1;

import de.tobiasbell.aoc_2020.util.InputReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Test
    void pairFinder() throws IOException {
        var test = """
                1721
                979
                366
                299
                675
                1456
                """;
        final List<Long> numbers = Stream.of(test.split("\\R"))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        assertThat(Day1.pairFinder(numbers)).containsExactlyInAnyOrder(1721, 299);
    }

    @Test
    void solve1() throws IOException {
        assertThat(Day1.solve1(InputReader.getInput(1))).isEqualTo(567171L);
    }

    @Test
    void solve2() throws IOException {
        assertThat(Day1.solve2(InputReader.getInput(1))).isEqualTo(212428694L);
    }
}