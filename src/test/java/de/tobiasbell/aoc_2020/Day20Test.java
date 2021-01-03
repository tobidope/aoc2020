package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day20Test {
    @Test
    void matchingTiles() {
        // given
        var input = Input.readResource("day20-example.txt");
        var tiles = Day20.readTiles(input);
        // when
        final Map<Day20.Tile, Integer> matchingTiles = Day20.matchingTiles(tiles);
        //then
        assertThat(matchingTiles).isNotNull();
    }

    @Test
    void solve1() {
        // given
        var input = Input.readResource("day20-example.txt");
        // when
        final long result = Day20.solve1(input);
        //then
        assertThat(result).isEqualTo(20899048083289L);
        assertThat(Day20.solve1(Input.puzzleInput(20))).isEqualTo(18449208814679L);
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
    void parseTiles() {
        // given
        var input = Input.readResource("day20-example.txt");
        // when
        var tiles = Day20.readTiles(input);
        //then
        assertThat(tiles).hasSize(9);
    }
}