package de.tobiasbell.aoc_2020.day1;

import de.tobiasbell.aoc_2020.day1.util.InputReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {
    @Test
    void solve1() throws IOException {
        assertThat(Day2.solve1(InputReader.getInput(2))).isEqualTo(493L);
    }

    @Test
    void solve2() throws IOException {
        assertThat(Day2.solve2(InputReader.getInput(2))).isEqualTo(0);
    }
}