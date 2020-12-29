package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day18Test {

    @Test
    void solve1() {
        assertThat(Day18.solve1(Input.puzzleInput(18))).isEqualTo(53660285675207L);
    }

    @Test
    void solve2() {
        assertThat(Day18.solve2(Input.puzzleInput(18))).isEqualTo(141993988282687L);
    }

    @ParameterizedTest
    @CsvSource({
            "32+, 5",
            "32+3*, 15",
            "123*+456+*+, 51"
    })
    void evaluate(String postfix, long expected) {
        assertThat(Day18.evaluate(postfix)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "3 + 2, 32+",
            "3 + 2 * 3, 32+3*",
            "1 + (2 * 3) + (4 * (5 + 6)), 123*+456+*+"
    })
    void toPostfix(final String infix, final String postfix) {
        // when
        final var result = Day18.toPostfix(infix, Map.of());
        //then
        assertThat(result).isEqualTo(postfix);
    }
}