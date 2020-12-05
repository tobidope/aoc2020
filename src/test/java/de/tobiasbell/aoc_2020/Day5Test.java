package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.InputReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day5Test {

    @Test
    void computeSeatId() {
        assertThat(Day5.computeSeat("FBFBBFFRLR")).isEqualTo(new Day5.Seat(44, 5));
        assertThat(Day5.computeSeat("BFFFBBFRRR")).isEqualTo(new Day5.Seat(70, 7));
    }

    @Test
    void seatId() {
        assertThat(new Day5.Seat(44, 5).seatId()).isEqualTo(357);
    }

    @Test
    void solve1() throws IOException {
        assertThat(Day5.solve1(InputReader.getInput(5))).isEqualTo(874L);
    }

    @Test
    void solve2() throws IOException {
        assertThat(Day5.solve2(InputReader.getInput(5))).isEqualTo(594);
    }

    @Test
    void findMissingSeat() throws IOException {
        final Day5.Seat missingSeat = Day5.findMissingSeat(InputReader.getInput(5));
        assertThat(missingSeat).isEqualTo(new Day5.Seat(74, 2));
    }
}