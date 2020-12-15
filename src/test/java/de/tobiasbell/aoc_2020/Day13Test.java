package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class Day13Test {

    @Test
    void solve1() {
        // given
        var example = """
                939
                7,13,x,x,59,x,31,19
                """;
        // when
        final long result = Day13.solve1(example);
        //then
        assertThat(result).isEqualTo(295);
        assertThat(Day13.solve1(Input.puzzleInput(13))).isEqualTo(5946L);
    }

    @Test
    @Disabled
    void solve2() {
        // when
        final long result = Day13.solve2(Input.puzzleInput(13));
        //then
        assertThat(result).isEqualTo(645338524823718L);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1068781, 7,0",
            "1068781, 13, 1",
            "1068781, 59, 4"
    })
    void timestampOffset(long timestamp, int busline, int offset) {
        assertThat(Day13.timestampOffset(timestamp, busline)).isEqualTo(offset);
    }
}