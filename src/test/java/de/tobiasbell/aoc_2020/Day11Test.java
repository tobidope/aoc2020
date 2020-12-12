package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {

    @Test
    void mutate() {
        // given
        final Day11.Grid start = Day11.Grid.parse("""
                L.LL.LL.LL
                LLLLLLL.LL
                L.L.L..L..
                LLLL.LL.LL
                L.LL.LL.LL
                L.LLLLL.LL
                ..L.L.....
                LLLLLLLLLL
                L.LLLLLL.L
                L.LLLLL.LL
                """);
        // when
        final Day11.Grid firstGrid = Day11.mutate(start);
        //then
        assertThat(firstGrid).isEqualTo(Day11.Grid.parse("""
                #.##.##.##
                #######.##
                #.#.#..#..
                ####.##.##
                #.##.##.##
                #.#####.##
                ..#.#.....
                ##########
                #.######.#
                #.#####.##
                """));
        // when
        final Day11.Grid secondGrid = Day11.mutate(firstGrid);
        // then
        assertThat(secondGrid).isEqualTo(Day11.Grid.parse("""
                #.LL.L#.##
                #LLLLLL.L#
                L.L.L..L..
                #LLL.LL.L#
                #.LL.LL.LL
                #.LLLL#.##
                ..L.L.....
                #LLLLLLLL#
                #.LLLLLL.L
                #.#LLLL.##
                """));
    }

    @Test
    void solve1() {
        // given
        var example = """
                L.LL.LL.LL
                LLLLLLL.LL
                L.L.L..L..
                LLLL.LL.LL
                L.LL.LL.LL
                L.LLLLL.LL
                ..L.L.....
                LLLLLLLLLL
                L.LLLLLL.L
                L.LLLLL.LL
                """;
        // when
        final long occupiedSeats = Day11.solve1(example);
        //then
        assertThat(occupiedSeats).isEqualTo(37);
        assertThat(Day11.solve1(Input.puzzleInput(11))).isEqualTo(2093L);
    }

    @Test
    void seatsInSightAreOccupied() {
        // given
        var g = Day11.Grid.parse("""
                .......#.
                ...#.....
                .#.......
                .........
                ..#L....#
                ....#....
                .........
                #........
                ...#.....
                """);
        // when
        final int seatsInSight = g.seatsInSight(4, 3);
        //then
        assertThat(seatsInSight).isEqualTo(8);
    }

    @Test
    void seatsInSightAreEmpty() {
        // given
        var g = Day11.Grid.parse("""
                .............
                .L.L.#.#.#.#.
                .............
                """);
        final int seatsInSight = g.seatsInSight(1, 1);
        //then
        assertThat(seatsInSight).isEqualTo(0);
    }

    @Test
    void seatsInSightAreEmptyToo() {
        // given
        var g = Day11.Grid.parse("""
                .##.##.
                #.#.#.#
                ##...##
                ...L...
                ##...##
                #.#.#.#
                .##.##.
                """);
        final int seatsInSight = g.seatsInSight(3, 3);
        //then
        assertThat(seatsInSight).isEqualTo(0);
    }

    @Test
    void solve2() {
        var example = """
                L.LL.LL.LL
                LLLLLLL.LL
                L.L.L..L..
                LLLL.LL.LL
                L.LL.LL.LL
                L.LLLLL.LL
                ..L.L.....
                LLLLLLLLLL
                L.LLLLLL.L
                L.LLLLL.LL
                """;
        // when
        final long occupiedSeats = Day11.solve2(example);
        //then
        assertThat(occupiedSeats).isEqualTo(26);
        assertThat(Day11.solve2(Input.puzzleInput(11))).isEqualTo(1862L);
    }
}