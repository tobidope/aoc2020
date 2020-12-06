package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.InputReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day6Test {

    @Test
    void distinctAnswers() {
        var answers = "abc";
        assertThat(Day6.distinctAnswers(answers)).isEqualTo(3);
        answers = """
                a
                a
                a
                a                                
                """;
        assertThat(Day6.distinctAnswers(answers)).isEqualTo(1);
    }

    @Test
    void everoneAnsweredYes() {
        var answers = """
                abc
                """;
        assertThat(Day6.everyoneAnsweredYes(answers)).isEqualTo(3);
        answers = """
                a
                b
                c
                """;
        assertThat(Day6.everyoneAnsweredYes(answers)).isEqualTo(0);
        answers = """
                a
                a
                a
                a
                """;
        assertThat(Day6.everyoneAnsweredYes(answers)).isEqualTo(1);
        answers = """
                ab
                ac
                """;
        assertThat(Day6.everyoneAnsweredYes(answers)).isEqualTo(1);
        answers = """
                b
                """;
        assertThat(Day6.everyoneAnsweredYes(answers)).isEqualTo(1);

    }

    @Test
    void solve1() throws IOException {
        assertThat(Day6.solve1(InputReader.getInput(6))).isEqualTo(6742L);
    }

    @Test
    void solve2() throws IOException {
        var example = """
                abc
                                
                a
                b
                c
                                
                ab
                ac
                                
                a
                a
                a
                a
                                
                b""";
        assertThat(Day6.solve2(example)).isEqualTo(6L);
        assertThat(Day6.solve2(InputReader.getInput(6))).isEqualTo(3447L);
    }
}