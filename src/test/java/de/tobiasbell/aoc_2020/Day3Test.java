package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {
    @Test
    void solve1() {
        assertThat(Day3.solve1(Input.puzzleInput(3))).isEqualTo(259);
    }

    @Test
    void solve2() {
        assertThat(Day3.solve2(Input.puzzleInput(3))).isEqualTo(2_224_913_600L);
    }
}