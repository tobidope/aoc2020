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
    void testRule() {
        // given
        var input = "class: 0-1 or 4-19";
        // when
        final var rule = Day16.Rule.of(input);
        //then
        assertThat(rule.getName()).isEqualTo("class");
        assertThat(rule)
                .accepts(0, 1, 4, 5, 6, 18, 19)
                .rejects(3, 2, -1, 20);

    }

    @Test
    void solve2() {
        assertThat(Day16.solve2(Input.puzzleInput(16))).isEqualTo(3765150732757L);
    }
}