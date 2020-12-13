package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

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
}