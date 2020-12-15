package de.tobiasbell.aoc_2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day15Test {

    @Test
    void memoryGameSayNextNumber() {
        // given
        final Day15.MemoryGame game = new Day15.MemoryGame(List.of(0, 3, 6));
        //then
        assertThat(game.lastTurn()).isEqualTo(3);
        assertThat(game.lastNumberSpoken()).isEqualTo(6);
        assertThat(game.sayNextNumber()).isEqualTo(0);
        assertThat(game.lastNumberSpoken()).isEqualTo(0);
        assertThat(game.lastTurn()).isEqualTo(4);
        assertThat(game.sayNextNumber()).isEqualTo(3);
        assertThat(game.sayNextNumber()).isEqualTo(3);
        assertThat(game.sayNextNumber()).isEqualTo(1);
        assertThat(game.sayNextNumber()).isEqualTo(0);
        assertThat(game.sayNextNumber()).isEqualTo(4);
        assertThat(game.sayNextNumber()).isEqualTo(0);
    }

    @Test
    void memoryGameSayUntil() {
        // given
        final Day15.MemoryGame game = new Day15.MemoryGame(List.of(0, 3, 6));
        //then
        assertThat(game.sayUntilTurn(10)).isEqualTo(0);
        assertThat(new Day15.MemoryGame(List.of(1, 3, 2)).sayUntilTurn(2020)).isEqualTo(1);
        assertThat(new Day15.MemoryGame(List.of(2, 1, 3)).sayUntilTurn(2020)).isEqualTo(10);
        assertThat(new Day15.MemoryGame(List.of(0, 3, 6)).sayUntilTurn(30000000)).isEqualTo(175594);
    }

    @Test
    void solve1() {
        assertThat(Day15.solve1()).isEqualTo(1618L);
    }

    @Test
    void solve2() {
        assertThat(Day15.solve2()).isEqualTo(548531L);
    }
}