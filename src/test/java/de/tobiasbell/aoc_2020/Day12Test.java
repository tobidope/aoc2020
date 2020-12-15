package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {

    @Test
    void solve1() {
        // given
        var example = """
                F10
                N3
                F7
                R90
                F11
                """;
        // when
        final long result = Day12.solve1(example);
        //then
        assertThat(result).isEqualTo(25);
        assertThat(Day12.solve1(Input.puzzleInput(12))).isEqualTo(845L);
    }

    @Test
    void solve2() {
        // given
        var example = """
                F10
                N3
                F7
                R90
                F11
                """;
        // when
        final long result = Day12.solve2(example);
        //then
        assertThat(result).isEqualTo(286);
        assertThat(Day12.solve2(Input.puzzleInput(12))).isEqualTo(27016L);
    }
}