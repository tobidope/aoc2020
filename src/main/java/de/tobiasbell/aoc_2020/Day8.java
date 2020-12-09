package de.tobiasbell.aoc_2020;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static de.tobiasbell.aoc_2020.util.Input.lines;

public class Day8 {
    private Day8() {
    }

    public static List<Instruction> parseInstructions(String input) {
        return lines(input)
                .map(Instruction::of)
                .collect(Collectors.toList());
    }

    public static Result evaluateInstructions(List<Instruction> instructions) {
        int instructionPointer = 0;
        int accumulator = 0;
        boolean[] visited = new boolean[instructions.size()];
        Arrays.fill(visited, false);

        while (instructionPointer < instructions.size()) {
            if (visited[instructionPointer]) {
                return new Result(accumulator, State.LOOPS);
            }
            final Instruction instruction = instructions.get(instructionPointer);
            visited[instructionPointer] = true;
            switch (instruction.operation()) {
                case NOP -> instructionPointer++;
                case ACC -> {
                    accumulator += instruction.argument();
                    instructionPointer++;
                }
                case JMP -> instructionPointer += instruction.argument();
            }
        }
        return new Result(accumulator, State.FINISHED);
    }

    public static Result evaluateWithFlipping(List<Instruction> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            final Instruction current = instructions.get(i);
            if (current.operation() == Operation.ACC) {
                continue;
            }
            instructions.set(i, current.flip());
            final Result result = evaluateInstructions(instructions);
            if (result.state() == State.FINISHED) {
                return result;
            }
            instructions.set(i, current);
        }
        throw new IllegalArgumentException("Couldn't find a solution");
    }

    public static long solve1(final String input) {
        final List<Instruction> instructions = parseInstructions(input);
        return evaluateInstructions(instructions).result();
    }

    public static long solve2(final String input) {
        final List<Instruction> instructions = parseInstructions(input);
        return evaluateWithFlipping(instructions).result();

    }

    public enum Operation {
        NOP, JMP, ACC;

        public static Operation of(final String operation) {
            return Arrays.stream(values())
                    .filter(o -> o.name().equalsIgnoreCase(operation))
                    .findFirst()
                    .orElseThrow();
        }
    }

    public enum State {
        FINISHED, LOOPS
    }

    public static record Result(int result, State state) {
    }

    public static record Instruction(Operation operation, int argument) {
        public static Instruction of(final String line) {
            final String[] split = line.split(" ");
            return new Instruction(Operation.of(split[0]), Integer.parseInt(split[1]));
        }

        public Instruction flip() {
            return switch (operation) {
                case NOP -> new Instruction(Operation.JMP, this.argument());
                case JMP -> new Instruction(Operation.NOP, this.argument());
                default -> this;
            };
        }
    }
}
