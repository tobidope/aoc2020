package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class Day20Test {
    @Test
    void matchingSides() {
        // given
        var input = Input.readResource("day20-example.txt");
        var tiles = Day20.readTiles(input);
        // when
        final Map<String, Set<Day20.Tile>> matchingSides = Day20.matchingSides(tiles);
        //then
        assertThat(matchingSides).isNotNull();
    }

    @Test
    void parseTile() {
        // given
        var example = """
                Tile 2311:
                ..##.#..#.
                ##..#.....
                #...##..#.
                ####.#...#
                ##.##.###.
                ##...#.###
                .#.#.#..##
                ..#....#..
                ###...#.#.
                ..###..###
                """;
        // when
        final Day20.Tile tile = Day20.Tile.parse(example);
        //then
        assertThat(tile).isNotNull()
                .hasFieldOrPropertyWithValue("id", 2311)
                .hasFieldOrPropertyWithValue("top", "..##.#..#.")
                .hasFieldOrPropertyWithValue("bottom", "..###..###")
                .hasFieldOrPropertyWithValue("right", "...#.##..#")
                .hasFieldOrPropertyWithValue("left", ".#####..#.");

    }

    @Test
    void rotateTile() {
        // given
        final Day20.Tile tile = new Day20.Tile(1, "..##.#..#.", "..###..###", "...#.##..#", ".#####..#.");
        // when
        var rotated2Times = tile.rotate().rotate();
        var rotated4Times = tile.rotate().rotate().rotate().rotate();
        //then
        assertThat(tile.right()).isEqualTo(rotated2Times.left());
        assertThat(rotated4Times).isEqualTo(tile);
    }

    @Test
    void parseTiles() {
        // given
        var input = Input.readResource("day20-example.txt");
        // when
        var tiles = Day20.readTiles(input);
        //then
        assertThat(tiles).hasSize(9);
    }
}