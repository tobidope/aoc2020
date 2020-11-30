package de.tobiasbell.aoc_2020.day1;

import de.tobiasbell.aoc_2020.util.InputReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Test
    void solve1() throws IOException {
        assertThat(Day1.solve1(InputReader.getInput(1))).isEqualTo(0);
    }

    @Test
    void solve2() throws IOException {
        assertThat(Day1.solve2(InputReader.getInput(1))).isEqualTo(0);
    }
}