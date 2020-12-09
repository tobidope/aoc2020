package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day5Test {
    @Test
    void solve1() {
        assertThat(Day5.solve1(Input.puzzleInput(5))).isEqualTo(874L);
    }

    @Test
    void solve2() {
        assertThat(Day5.solve2(Input.puzzleInput(5))).isEqualTo(594);
    }
}