package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.util.SortedMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

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
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            final long result = Day13.solve2(Input.puzzleInput(13));
            //then
            assertThat(result).isEqualTo(0);
        });
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

    @Test
    void findFirstMatchingOffsets() {
        // given
        var example = """
                939
                7,13,x,x,59,x,31,19
                """;
        final SortedMap<Integer, Integer> map = Day13.parse(example);
        // when
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            final long firstMatchingOffset = Day13.findFirstMatchingOffsets(map, 0);
            //then
            assertThat(firstMatchingOffset).isEqualTo(1068781);
        });
    }

    @Test
    void findFirstMatchingOffsetsA() {
        // given
        var example = """
                939
                17,x,13,19
                """;
        final SortedMap<Integer, Integer> map = Day13.parse(example);
        // when
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            final long firstMatchingOffset = Day13.findFirstMatchingOffsets(map, 0);
            //then
            assertThat(firstMatchingOffset).isEqualTo(3417);
        });
    }

    @Test
    void findFirstMatchingOffsetsLast() {
        // given
        var example = """
                939
                1789,37,47,1889
                """;
        final SortedMap<Integer, Integer> map = Day13.parse(example);
        // when
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            final long firstMatchingOffset = Day13.findFirstMatchingOffsets(map, 1000000000L);
            //then
            assertThat(firstMatchingOffset).isEqualTo(1202161486L);
        });
    }
}