package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {

    @Test
    void solve1() {
        // given
        var example = """                
                """;
        // when
        final long result = Day10.solve1(example);
        //then
        assertThat(result).isEqualTo(0);
        assertThat(Day10.solve1(Input.puzzleInput(10))).isEqualTo(0);
    }

    @Test
    void solve2() {
        // given
        var example = """          
                """;
        // when
        final long result = Day10.solve2(example);
        //then
        assertThat(result).isEqualTo(0);
        assertThat(Day10.solve2(Input.puzzleInput(10))).isEqualTo(0);
    }
}