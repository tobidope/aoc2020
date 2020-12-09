package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.List;
import java.util.stream.Collectors;

public class Day3 {
    public static long solve1(final String input) {
        final List<String> list = Input.lines(input).collect(Collectors.toList());
        final TreeMap map = new TreeMap(list);
        return visitedTrees(map, 3, 1);
    }

    public static long solve2(String input) {
        final List<String> list = Input.lines(input).collect(Collectors.toList());
        final TreeMap map = new TreeMap(list);
        return visitedTrees(map, 1, 1)
                * visitedTrees(map, 3, 1)
                * visitedTrees(map, 5, 1)
                * visitedTrees(map, 7, 1)
                * visitedTrees(map, 1, 2);
    }

    public static long visitedTrees(final TreeMap map, int right, int down) {
        int x = 0;
        int y = 0;
        long trees = 0;
        do {
            x += down;
            y += right;
            if (map.hasTree(x, y)) {
                trees++;
            }
        } while (x < map.rows() - 1);
        return trees;
    }

    public static record TreeMap(List<String> map) {
        public static final char TREE = '#';

        public boolean hasTree(int x, int y) {
            return map.get(x).charAt(y % columns()) == TREE;
        }

        public int rows() {
            return map.size();
        }

        public int columns() {
            return map.get(0).length();
        }
    }
}
