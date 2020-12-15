package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.List;
import java.util.stream.Collectors;

public class Day12 {
    public static long solve1(String puzzle) {
        final List<Instruction> instructions = Input.lines(puzzle)
                .map(Instruction::of)
                .collect(Collectors.toList());
        Vector ferry = new Vector(0, 0);
        Vector direction = new Vector(1, 0);
        for (var instruction : instructions) {
            switch (instruction.action) {
                case N -> ferry = ferry.add(0, instruction.value);
                case S -> ferry = ferry.add(0, -instruction.value);
                case E -> ferry = ferry.add(instruction.value, 0);
                case W -> ferry = ferry.add(-instruction.value, 0);
                case F -> ferry = ferry.add(direction.multiply(instruction.value));
                case L -> {
                    for (int i = 0; i < instruction.value / 90; i++) {
                        direction = direction.multiply(new Vector(0, 1));
                    }
                }
                case R -> {
                    for (int i = 0; i < instruction.value / 90; i++) {
                        direction = direction.multiply(new Vector(0, -1));
                    }
                }
            }
        }
        return ferry.distance();
    }

    public static long solve2(String puzzle) {
        final List<Instruction> instructions = Input.lines(puzzle)
                .map(Instruction::of)
                .collect(Collectors.toList());
        Vector ferry = new Vector(0, 0);
        Vector waypoint = new Vector(10, 1);
        for (var instruction : instructions) {
            switch (instruction.action) {
                case N -> waypoint = waypoint.add(0, instruction.value);
                case S -> waypoint = waypoint.add(0, -instruction.value);
                case E -> waypoint = waypoint.add(instruction.value, 0);
                case W -> waypoint = waypoint.add(-instruction.value, 0);
                case F -> ferry = ferry.add(waypoint.multiply(instruction.value));
                case L -> {
                    for (int i = 0; i < instruction.value / 90; i++) {
                        waypoint = new Vector(-waypoint.y, waypoint.x);
                    }
                }
                case R -> {
                    for (int i = 0; i < instruction.value / 90; i++) {
                        waypoint = new Vector(waypoint.y, -waypoint.x);
                    }
                }
            }
        }
        return ferry.distance();
    }

    public enum Action {
        N, S, E, W, L, R, F
    }


    public final record Vector(int x, int y) {
        public Vector add(int x, int y) {
            return new Vector(this.x + x, this.y + y);
        }

        public Vector add(Vector vector) {
            return add(vector.x, vector.y);
        }

        public Vector multiply(int factor) {
            return new Vector(x * factor, y * factor);
        }

        public Vector multiply(Vector vector) {
            return new Vector(x * vector.x - y * vector.y, x * vector.y + vector.x * y);
        }

        public long distance() {
            return Math.abs(x) + Math.abs(y);
        }
    }

    public record Instruction(Action action, int value) {
        public static Instruction of(final String input) {
            return new Instruction(
                    Action.valueOf(input.substring(0, 1)),
                    Integer.parseInt(input.substring(1)));
        }
    }
}
