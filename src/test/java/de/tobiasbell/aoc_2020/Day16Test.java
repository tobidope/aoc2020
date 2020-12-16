package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day16Test {
    @Test
    void solve1() {
        // given
        var example = """
                class: 1-3 or 5-7
                row: 6-11 or 33-44
                seat: 13-40 or 45-50
                                
                your ticket:
                7,1,14
                                
                nearby tickets:
                7,3,47
                40,4,50
                55,2,20
                38,6,12""";
        // when
        final long result = Day16.solve1(example);
        //then
        assertThat(result).isEqualTo(71);
        assertThat(Day16.solve1(Input.puzzleInput(16))).isEqualTo(23044);
    }

    @Test
    void solve2() {
        // given
        var example = """
                class: 0-1 or 4-19
                row: 0-5 or 8-19
                seat: 0-13 or 16-19
                               
                your ticket:
                11,12,13
                               
                nearby tickets:
                3,9,18
                15,1,5
                5,14,9""";
        // when
        final long result = Day16.solve2(example);
        //then
        //assertThat(result).isEqualTo(71);
        assertThat(Day16.solve2(Input.puzzleInput(16))).isEqualTo(23044);
    }
}