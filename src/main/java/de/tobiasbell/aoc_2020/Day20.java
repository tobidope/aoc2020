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

    public static Map<Tile, Integer> matchingTiles(final List<Tile> tiles) {
        Map<Tile, Integer> matching = new HashMap<>();
        for (int i = 0; i < tiles.size() - 1; i++) {
            final Tile tile = tiles.get(i);
            for (Tile other : tiles.subList(i + 1, tiles.size())) {
                if (tile.bordersMatch(other)) {
                    matching.merge(tile, 1, Integer::sum);
                    matching.merge(other, 1, Integer::sum);
                }
            }
        }
        return matching;
    }

    public static long solve1(final String puzzle) {
        final List<Tile> tiles = readTiles(puzzle);
        final Map<Tile, Integer> tilesWithNeighbors = matchingTiles(tiles);
        return tilesWithNeighbors.entrySet().stream()
                .filter(entry -> entry.getValue().equals(2))
                .map(Map.Entry::getKey)
                .mapToLong(Tile::id)
                .reduce(1, (left, right) -> left * right);
    }

    public record Tile(int id, String top, String bottom, String right, String left, List<String> interior) {

        public static Tile parse(final String tile) {
            final String[] split = tile.trim().split("\\R");
            final String[] titleSplit = split[0].split(" ");
            final int id = Integer.parseInt(titleSplit[1].substring(0, titleSplit[1].length() - 1));
            StringBuilder right = new StringBuilder();
            StringBuilder left = new StringBuilder();
            List<String> interior = new ArrayList<>();
            for (int i = 1; i < split.length; i++) {
                var line = split[i];
                right.append(line.substring(line.length() - 1));
                left.append(line.charAt(0));
                interior.add(line.substring(1, line.length() - 2));
            }
            return new Tile(id, split[1],
                    split[split.length - 1],
                    right.toString(),
                    left.toString(),
                    interior);
        }

        private static String reverse(final String side) {
            return new StringBuilder(side).reverse().toString();
        }

        public boolean bordersMatch(final Tile other) {
            for (String a : borders()) {
                for (String b : other.borders()) {
                    if (a.equals(b) || a.equals(reverse(b))) {
                        return true;
                    }
                }
            }
            return false;
        }

        public Tile rotate() {
            return new Tile(
                    id,
                    reverse(left),
                    reverse(right),
                    top,
                    bottom,
                    interior
            );
        }

        public Tile flipVertical() {
            return new Tile(
                    id,
                    reverse(top),
                    reverse(bottom),
                    left,
                    right,
                    interior
            );
        }

        public Tile flipHorizontal() {
            return new Tile(
                    id,
                    bottom,
                    top,
                    reverse(right),
                    reverse(left),
                    interior
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
