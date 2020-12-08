package de.tobiasbell.aoc_2020;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static de.tobiasbell.aoc_2020.util.InputReader.lines;

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

        while (true) {
            if (instructionPointer == instructions.size()) {
                return new Result(accumulator, false);
            }
            if (visited[instructionPointer]) {
                return new Result(accumulator, true);
            }
            final Instruction instruction = instructions.get(instructionPointer);
            visited[instructionPointer] = true;
            switch (instruction.operation()) {
                case "nop" -> instructionPointer++;
                case "acc" -> {
                    accumulator += instruction.argument();
                    instructionPointer++;
                }
                case "jmp" -> instructionPointer += instruction.argument();
                default -> throw new IllegalStateException("Unexpected value: " + instruction.operation());
            }
        }
    }

    public static Result evaluateWithFlipping(List<Instruction> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            final Instruction current = instructions.get(i);
            if (current.operation().equals("acc")) {
                continue;
            }
            instructions.set(i, current.flip());
            final Result result = evaluateInstructions(instructions);
            if (!result.looped()) {
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

    public static record Result(int result, boolean looped) {
    }

    public static record Instruction(String operation, int argument) {
        public static Instruction of(final String line) {
            final String[] split = line.split(" ");
            return new Instruction(split[0], Integer.parseInt(split[1]));
        }

        public Instruction flip() {
            return switch (operation) {
                case "nop" -> new Instruction("jmp", this.argument());
                case "jmp" -> new Instruction("nop", this.argument());
                default -> this;
            };
        }
    }
}
