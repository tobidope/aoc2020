package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.InputReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day5Test {
    @Test
    void solve1() throws IOException {
        assertThat(Day5.solve1(InputReader.getInput(5))).isEqualTo(874L);
    }

    @Test
    void solve2() throws IOException {
        assertThat(Day5.solve2(InputReader.getInput(5))).isEqualTo(594);
    }
}