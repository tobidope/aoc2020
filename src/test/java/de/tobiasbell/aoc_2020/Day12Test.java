package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.Day12.FerryState;
import de.tobiasbell.aoc_2020.Day12.Instruction;
import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import static de.tobiasbell.aoc_2020.Day12.Action.F;
import static de.tobiasbell.aoc_2020.Day12.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {

    @Test
    void eastTurnRight90IsSouth() {
        // given
        final Instruction i = new Instruction(Day12.Action.R, 90);
        // when
        var d = EAST.turn(i);
        //then
        assertThat(d).isEqualTo(SOUTH);
    }

    @Test
    void northTurnLeft90IsWest() {
        // given
        final Instruction i = new Instruction(Day12.Action.L, 90);
        // when
        var d = NORTH.turn(i);
        //then
        assertThat(d).isEqualTo(WEST);
    }

    @Test
    void move() {
        // given
        final FerryState state = new FerryState(0, 0, EAST);
        final Instruction instruction = new Instruction(F, 10);
        // when
        final FerryState newState = state.move(instruction);
        //then
        assertThat(newState).isEqualTo(new FerryState(0, 10, EAST));
    }

    @Test
    void solve1() {
        // given
        var example = """
                F10
                N3
                F7
                R90
                F11
                """;
        // when
        final long result = Day12.solve1(example);
        //then
        assertThat(result).isEqualTo(25);
        assertThat(Day12.solve1(Input.puzzleInput(12))).isEqualTo(845L);
    }
}