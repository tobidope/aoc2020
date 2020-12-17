package de.tobiasbell.aoc_2020;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class Day17 {

    public static final char CUBE = '#';

    public static Set<Coordinate> parseActiveCubes(final String map,
                                                   final BiFunction<Integer, Integer, Coordinate> creator) {
        Set<Coordinate> cubeMap = new HashSet<>();
        final var lines = map.split("\\R");
        for (int i = 0; i < lines.length; i++) {
            final var line = lines[i];
            for (int j = 0; j < line.length(); j++) {
                final var cube = line.charAt(j);
                if (cube == CUBE) {
                    final var coordinate = creator.apply(j, i);
                    cubeMap.add(coordinate);
                }
            }
        }
        return cubeMap;
    }

    public static Set<Coordinate> mutate(final Set<Coordinate> grid) {
        Set<Coordinate> emptyVisited = new HashSet<>();
        Set<Coordinate> newGrid = new HashSet<>();

        for (var cube : grid) {
            var neighbors = cube.neighbors();
            int activeNeighbors = 0;
            for (var neighbor : neighbors) {
                if (grid.contains(neighbor)) {
                    activeNeighbors++;
                } else {
                    emptyVisited.add(neighbor);
                }
            }
            // A cube stays active if exactly 2 or 3 neighbors are active
            if (activeNeighbors >= 2 && activeNeighbors <= 3) {
                newGrid.add(cube);
            }
        }

        for (var emptyCubes : emptyVisited) {
            var neighbors = emptyCubes.neighbors();
            int activeNeighbors = 0;
            for (var neighbor : neighbors) {
                if (grid.contains(neighbor)) {
                    activeNeighbors++;
                }
            }
            // An inactive cube gets active if exactly 3 neighbors are active
            if (activeNeighbors == 3) {
                newGrid.add(emptyCubes);
            }
        }

        return newGrid;
    }

    public static long solve1(String puzzle) {
        var grid = parseActiveCubes(puzzle, Coordinate2D::create);
        for (int i = 0; i < 6; i++) {
            grid = mutate(grid);
        }
        return grid.size();
    }

    public static long solve2(String puzzle) {
        var grid = parseActiveCubes(puzzle, Coordinate3D::create);
        for (int i = 0; i < 6; i++) {
            grid = mutate(grid);
        }
        return grid.size();
    }

    public interface Coordinate {
        List<Coordinate> neighbors();
    }

    public record Coordinate2D(int x, int y, int z) implements Coordinate {
        public static Coordinate create(int x, int y) {
            return new Coordinate2D(x, y, 0);
        }

        @Override
        public List<Coordinate> neighbors() {
            List<Coordinate> neighbors = new ArrayList<>();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        if (i == 0 && j == 0 && k == 0) {
                            continue;
                        }
                        neighbors.add(new Coordinate2D(x + i, y + j, z + k));
                    }
                }
            }
            return neighbors;
        }
    }

    public record Coordinate3D(int x, int y, int z, int w) implements Coordinate {
        public static Coordinate create(int x, int y) {
            return new Coordinate3D(x, y, 0, 0);
        }

        @Override
        public List<Coordinate> neighbors() {
            List<Coordinate> neighbors = new ArrayList<>();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (i == 0 && j == 0 && k == 0 && l == 0) {
                                continue;
                            }
                            neighbors.add(new Coordinate3D(x + i, y + j, z + k, w + l));
                        }
                    }
                }
            }
            return neighbors;
        }
    }
}
