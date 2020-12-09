package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day9Test {

    @Test
    void solve1() {
        // given
        var example = """
                35
                20
                15
                25
                47
                40
                62
                55
                65
                95
                102
                117
                150
                182
                127
                219
                299
                277
                309
                576
                """;
        // when
        final long result = Day9.solve1(example, 5);
        //then
        assertThat(result).isEqualTo(127);
        assertThat(Day9.solve1(InputReader.getInput(9), 25)).isEqualTo(167829540L);
    }

    @Test
    void solve2() {
        // given
        var example = """
                35
                20
                15
                25
                47
                40
                62
                55
                65
                95
                102
                117
                150
                182
                127
                219
                299
                277
                309
                576
                """;
        // when
        final long result = Day9.solve2(example, 127);
        //then
        assertThat(result).isEqualTo(62);
        assertThat(Day9.solve2(InputReader.getInput(9), 167829540L)).isEqualTo(28045630L);
    }
}