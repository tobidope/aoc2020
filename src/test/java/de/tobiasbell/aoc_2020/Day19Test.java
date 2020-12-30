package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class Day19Test {

    @Test
    void solve1() {
        assertThat(Day19.solve1(Input.puzzleInput(19))).isEqualTo(142);
    }

    @Test
    void solve2() {
        assertThat(Day19.solve2(Input.puzzleInput(19))).isEqualTo(142);
    }

    @Test
    void ruleMatch() {
        // given
        var example = """
                0: 4 1 5
                1: 2 3 | 3 2
                2: 4 4 | 5 5
                3: 4 5 | 5 4
                4: "a"
                5: "b"
                """;
        final Map<Integer, Day19.Rule> rules = Day19.parseRules(example);
        final Day19.Rule ruleZero = rules.get(0);
        //then
        assertThat(ruleZero.matches("ababbb", rules)).isEqualTo(Optional.of(""));
        assertThat(ruleZero.matches("abbbab", rules)).isEqualTo(Optional.of(""));
        assertThat(ruleZero.matches("bababa", rules)).isNotEqualTo(Optional.of(""));
        assertThat(ruleZero.matches("aaabbb", rules)).isNotEqualTo(Optional.of(""));
        assertThat(ruleZero.matches("aaaabbb", rules)).isNotEqualTo(Optional.of(""));
    }

    @Test
    void parseRules() {
        // given
        var example = """
                0: 4 1 5
                1: 2 3 | 3 2
                2: 4 4 | 5 5
                3: 4 5 | 5 4
                4: "a"
                5: "b"
                """;
        // when
        final Map<Integer, Day19.Rule> ruleMap = Day19.parseRules(example);
        //then
        assertThat(ruleMap)
                .hasSize(6)
                .contains(entry(0, new Day19.BackreferenceRule(List.of(4, 1, 5))),
                        entry(4, new Day19.LiteralRule('a')),
                        entry(1, new Day19.OrRule(
                                new Day19.BackreferenceRule(List.of(2, 3)),
                                new Day19.BackreferenceRule(List.of(3, 2)))
                        )
                );
    }
}