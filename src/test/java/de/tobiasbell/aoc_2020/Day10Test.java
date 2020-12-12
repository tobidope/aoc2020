package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class Day10Test {

    @Test
    void joltDifferenceDistribution() {
        // given
        var example = """
                1
                4
                5
                6
                7
                10
                11
                12
                15
                16
                19
                """;
        var adapters = Day10.parseAdaptersSorted(example);
        // when
        final Map<Long, Long> distribution = Day10.joltDifferenceDistribution(adapters);
        //then
        assertThat(distribution)
                .containsKeys(1L, 3L)
                .contains(
                        entry(1L, 7L),
                        entry(3L, 5L)
                );
    }

    @Test
    void joltDifferenceDistributionBigger() {
        // given
        var example = """
                28
                33
                18
                42
                31
                14
                46
                20
                48
                47
                24
                23
                49
                45
                19
                38
                39
                11
                1
                32
                25
                35
                8
                17
                7
                9
                4
                2
                34
                10
                3
                """;
        var adapters = Day10.parseAdaptersSorted(example);
        // when
        final Map<Long, Long> distribution = Day10.joltDifferenceDistribution(adapters);
        //then
        assertThat(distribution)
                .contains(
                        entry(1L, 22L),
                        entry(3L, 10L)
                );
    }


    @Test
    void solve1() {
        // given
        var example = """
                16
                10
                15
                5
                1
                11
                7
                19
                6
                12
                4              
                """;
        // when
        final long result = Day10.solve1(example);
        //then
        assertThat(result).isEqualTo(35);
        assertThat(Day10.solve1(Input.puzzleInput(10))).isEqualTo(1856L);
    }

    @Test
    void solve2() {
        // given
        var example = """
                1
                4
                5
                6
                7
                10
                11
                12
                15
                16
                19
                """;
        // when
        final long result = Day10.solve2(example);
        //then
        assertThat(result).isEqualTo(8);
        assertThat(Day10.solve2(Input.puzzleInput(10))).isEqualTo(2314037239808L);
    }
}