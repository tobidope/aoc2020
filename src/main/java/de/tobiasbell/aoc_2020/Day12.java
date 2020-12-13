package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.List;
import java.util.stream.Collectors;

public class Day12 {
    public static long solve1(String puzzle) {
        FerryState ferryState = new FerryState(0, 0, Direction.EAST);
        final List<Instruction> instructions = Input.lines(puzzle)
                .map(Instruction::of)
                .collect(Collectors.toList());
        for (var instruction : instructions) {
            ferryState = ferryState.move(instruction);
        }
        return ferryState.distance();
    }

    public enum Action {
        N, S, E, W, L, R, F
    }

    public enum Direction {
        NORTH, EAST, SOUTH, WEST;

        public Direction turn(Instruction instruction) {
            final int turns = instruction.value() / 90;
            int newDirection = switch (instruction.action()) {
                case L -> (ordinal() - turns) % values().length;
                case R -> (ordinal() + turns) % values().length;
                default -> throw new IllegalStateException("Unexpected value: " + instruction.action());
            };
            if (newDirection < 0) {
                newDirection = values().length + newDirection;
            }
            return values()[newDirection];
        }
    }

    public record Instruction(Action action, int value) {
        public static Instruction of(final String input) {
            return new Instruction(
                    Action.valueOf(input.substring(0, 1)),
                    Integer.parseInt(input.substring(1)));
        }
    }

    public record FerryState(int x, int y, Direction direction) {
        FerryState move(Instruction i) {
            return switch (i.action()) {
                case N -> new FerryState(x + i.value, y, direction);
                case S -> new FerryState(x - i.value, y, direction);
                case E -> new FerryState(x, y + i.value, direction);
                case W -> new FerryState(x, y - i.value, direction);
                case L, R -> new FerryState(x, y, direction.turn(i));
                case F -> moveForward(i.value);
            };
        }

        private FerryState moveForward(int value) {
            return switch (direction) {
                case NORTH -> new FerryState(x + value, y, direction);
                case SOUTH -> new FerryState(x - value, y, direction);
                case EAST -> new FerryState(x, y + value, direction);
                case WEST -> new FerryState(x, y - value, direction);
            };
        }

        public long distance() {
            return Math.abs(x) + Math.abs(y);
        }
    }
}
