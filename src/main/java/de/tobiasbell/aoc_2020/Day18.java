package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.LongBinaryOperator;

import static java.lang.Character.isDigit;
import static java.lang.Character.isSpaceChar;

public class Day18 {

    public static long solve1(final String puzzle) {
        return Input.lines(puzzle)
                .map(line -> toPostfix(line, Map.of()))
                .map(Day18::evaluate)
                .mapToLong(Long::longValue)
                .sum();
    }

    public static long evaluate(String postfixExpression) {
        Deque<Long> stack = new ArrayDeque<>();
        final Map<Character, LongBinaryOperator> operators = Map.of(
                '+', Long::sum,
                '*', (a, b) -> a * b);
        for (var c : postfixExpression.toCharArray()) {
            if (isDigit(c)) {
                stack.add(Long.valueOf(Character.getNumericValue(c)));
            } else if (c == '+' || c == '*') {
                final var operator = operators.get(c);
                stack.add(operator.applyAsLong(stack.removeLast(), stack.removeLast()));
            }
        }
        return stack.removeLast();
    }

    public static String toPostfix(final String line, Map<Character, Integer> precedence) {
        final Deque<Character> stack = new ArrayDeque<>();
        final StringBuilder output = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (isSpaceChar(c)) {
                continue;
            }
            if (isDigit(c)) {
                output.append(c);
            } else if (c == '(') {
                stack.add(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peekLast() != '(') {
                    output.append(stack.removeLast());
                }
                stack.removeLast();
            } else if (c == '+' || c == '*') {
                if (stack.isEmpty() || stack.peekLast() == '(') {
                    stack.add(c);
                } else {
                    while (!stack.isEmpty() && stack.peekLast() != '('
                            && precedence.getOrDefault(c, 0) <= precedence.getOrDefault(stack.peekLast(), 0)) {
                        output.append(stack.removeLast());
                    }
                    stack.add(c);
                }
            }
        }
        while (!stack.isEmpty()) {
            output.append(stack.removeLast());
        }
        return output.toString();
    }

    public static long solve2(final String puzzle) {
        final Map<Character, Integer> precedence = Map.of(
                '+', 2,
                '*', 1);
        return Input.lines(puzzle)
                .map(line -> toPostfix(line, precedence))
                .map(Day18::evaluate)
                .mapToLong(Long::longValue)
                .sum();
    }

}