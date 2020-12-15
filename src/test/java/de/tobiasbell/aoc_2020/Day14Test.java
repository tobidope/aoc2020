package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day14Test {
    @ParameterizedTest
    @CsvSource({
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X, 11, 73",
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X, 101, 101",
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X, 0, 64"
    })
    public void applyBitmask(final String mask, final long value, final long expected) {
        assertThat(Day14.applyMask(mask, value)).isEqualTo(expected);
    }

    @Test
    void generateAdresses() {
        // given
        var bitMask = "000000000000000000000000000000X1001X";
        var adress = 42L;
        // when
        final List<Long> adresses = Day14.generateAdresses(bitMask, adress);
        //then
        assertThat(adresses).hasSize(4)
                .contains(26L, 27L, 58L, 59L);
    }

    @Test
    void generateAdressesNext() {
        // given
        var bitMask = "00000000000000000000000000000000X0XX";
        var adress = 26L;
        // when
        final List<Long> adresses = Day14.generateAdresses(bitMask, adress);
        //then
        assertThat(adresses).hasSize(8)
                .contains(16L, 17L, 18L, 19L, 24L, 25L, 26L, 27L);
    }

    @Test
    void solve1() {
        // given
        final String puzzle = """
                mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
                mem[8] = 11
                mem[7] = 101
                mem[8] = 0
                """;
        // when
        final long result = Day14.solve1(puzzle);
        //then
        assertThat(result).isEqualTo(165);
        assertThat(Day14.solve1(Input.puzzleInput(14))).isEqualTo(11501064782628L);
    }

    @Test
    void solve2() {
        // given
        final String puzzle = """
                mask = 000000000000000000000000000000X1001X
                mem[42] = 100
                mask = 00000000000000000000000000000000X0XX
                mem[26] = 1
                """;
        // when
        final long result = Day14.solve2(puzzle);
        //then
        assertThat(result).isEqualTo(208L);
        assertThat(Day14.solve2(Input.puzzleInput(14)))
                .isEqualTo(5142195937660L);
    }
}