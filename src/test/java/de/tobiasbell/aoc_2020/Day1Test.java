package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Test
    void pairFinder() {
        var test = """
                1721
                979
                366
                299
                675
                1456
                """;
        final List<Long> numbers = Input.lines(test)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        assertThat(Day1.pairFinder(numbers)).containsExactlyInAnyOrder(1721, 299);
    }

    @Test
    void solve1() {
        assertThat(Day1.solve1(Input.puzzleInput(1))).isEqualTo(567171L);
    }

    @Test
    void solve2() {
        assertThat(Day1.solve2(Input.puzzleInput(1))).isEqualTo(212428694L);
    }
}