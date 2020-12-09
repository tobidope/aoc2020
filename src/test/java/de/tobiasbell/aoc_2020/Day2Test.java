package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {
    @Test
    void solve1() {
        assertThat(Day2.solve1(Input.puzzleInput(2))).isEqualTo(493L);
    }

    @Test
    void solve2() {
        assertThat(Day2.solve2(Input.puzzleInput(2))).isEqualTo(593L);
    }
}