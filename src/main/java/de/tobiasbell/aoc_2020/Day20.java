package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.*;
import java.util.stream.Collectors;

public class Day20 {
    private Day20() {
    }

    public static List<Tile> readTiles(final String puzzle) {
        return Input.splitByEmptyLines(puzzle)
                .map(Tile::parse)
                .collect(Collectors.toList());
    }

    public static Map<String, Set<Tile>> matchingSides(final List<Tile> tiles) {
        Map<String, Set<Tile>> map = new HashMap<>();
        for (var tile : tiles) {
            for (var orientation : tile.orientations()) {
                for (var border : orientation.borders()) {
                    map.putIfAbsent(border, new HashSet<>());
                    map.get(border).add(tile);
                }
            }
        }
        return map;
    }

    public record Tile(int id, String top, String bottom, String right, String left) {

        public static Tile parse(final String tile) {
            final String[] split = tile.trim().split("\\R");
            final String[] titleSplit = split[0].split(" ");
            final int id = Integer.parseInt(titleSplit[1].substring(0, titleSplit[1].length() - 1));
            StringBuilder right = new StringBuilder();
            StringBuilder left = new StringBuilder();
            for (int i = 1; i < split.length; i++) {
                var line = split[i];
                right.append(line.substring(line.length() - 1));
                left.append(line.charAt(0));
            }
            return new Tile(id, split[1], split[split.length - 1], right.toString(), left.toString());
        }

        private static String reverse(final String side) {
            return new StringBuilder(side).reverse().toString();
        }

        public Tile rotate() {
            return new Tile(
                    id,
                    reverse(left),
                    reverse(right),
                    top,
                    bottom
            );
        }

        public Tile flipVertical() {
            return new Tile(
                    id,
                    reverse(top),
                    reverse(bottom),
                    left,
                    right
            );
        }

        public Tile flipHorizontal() {
            return new Tile(
                    id,
                    bottom,
                    top,
                    reverse(right),
                    reverse(left)

            );
        }

        public Set<Tile> orientations() {
            Set<Tile> tiles = new HashSet<>();
            var currentTile = this;
            for (int i = 0; i < 4; i++) {
                tiles.add(currentTile);
                tiles.add(currentTile.flipHorizontal());
                tiles.add(currentTile.flipVertical());
                tiles.add(currentTile.flipVertical().flipHorizontal());
                currentTile = currentTile.rotate();
            }
            return tiles;
        }

        public Set<String> borders() {
            return Set.of(
                    top, bottom, right, left
            );
        }
    }
}
