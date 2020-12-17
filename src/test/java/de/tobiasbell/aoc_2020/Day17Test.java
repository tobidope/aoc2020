package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day17Test {

    @Test
    void mutate() {
        // given
        var grid = Day17.parseActiveCubes("""
                .#.
                ..#
                ###
                """, Day17.Coordinate2D::create);
        // when
        grid = Day17.mutate(grid);
        //then
        assertThat(grid).hasSize(11);
        // when
        grid = Day17.mutate(grid);
        //then
        assertThat(grid).hasSize(21);
    }

    @Test
    void solve1() {
        // given
        var grid = """
                .#.
                ..#
                ###
                """;

        // when
        final var activeCubes = Day17.solve1(grid);
        //then
        assertThat(activeCubes).isEqualTo(112);
        assertThat(Day17.solve1(Input.puzzleInput(17))).isEqualTo(215);
    }

    @Test
    void solve2() {
        assertThat(Day17.solve2(Input.puzzleInput(17))).isEqualTo(1728L);
    }

    @Test
    void surroundingCoordinates() {
        // given
        final var coordinate = new Day17.Coordinate2D(0, 0, 0);
        // when
        final var coordinates = coordinate.neighbors();
        //then
        assertThat(coordinates).hasSize(26)
                .contains(new Day17.Coordinate2D(-1, 0, 0),
                        new Day17.Coordinate2D(1, 1, 1));
    }

    @Test
    void parseActiveCubes() {
        // given
        var example = """
                .#.
                ..#
                ###
                """;
        // when
        final var coordinates = Day17.parseActiveCubes(example, Day17.Coordinate2D::create);
        //then
        assertThat(coordinates).hasSize(5)
                .contains(
                        new Day17.Coordinate2D(1, 0, 0),
                        new Day17.Coordinate2D(2, 1, 0),
                        new Day17.Coordinate2D(0, 2, 0),
                        new Day17.Coordinate2D(1, 2, 0),
                        new Day17.Coordinate2D(2, 2, 0)
                );
    }
}