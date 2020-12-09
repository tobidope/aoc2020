package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day8Test {

    @Test
    void solve1() {
        // given
        var example = """
                nop +0
                acc +1
                jmp +4
                acc +3
                jmp -3
                acc -99
                acc +1
                jmp -4
                acc +6
                """;
        // when
        final long result = Day8.solve1(example);
        //then
        assertThat(result).isEqualTo(5);
        assertThat(Day8.solve1(Input.puzzleInput(8))).isEqualTo(1814L);
    }

    @Test
    void solve2() {
        // given
        var example = """
                nop +0
                acc +1
                jmp +4
                acc +3
                jmp -3
                acc -99
                acc +1
                jmp -4
                acc +6
                """;
        // when
        final long result = Day8.solve2(example);
        //then
        assertThat(result).isEqualTo(8);
        assertThat(Day8.solve2(Input.puzzleInput(8))).isEqualTo(1056L);
    }
}