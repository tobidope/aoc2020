package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.InputReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {
    @Test
    void solve1() throws IOException {
        assertThat(Day3.solve1(InputReader.getInput(3))).isEqualTo(259);
    }

    @Test
    void solve2() throws IOException {
        assertThat(Day3.solve2(InputReader.getInput(3))).isEqualTo(2_224_913_600L);
    }
}