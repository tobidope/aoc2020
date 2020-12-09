package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class Day4Test {

    @Test
    void readPassportEntry() {
        var input = """
                ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
                byr:1937 iyr:2017 cid:147 hgt:183cm
                """;

        final Map<Day4.PassportEntry, String> map = Day4.readPassport(input);
        assertThat(map).hasSize(8)
                .contains(entry(Day4.PassportEntry.BYR, "1937"))
                .contains(entry(Day4.PassportEntry.HCL, "#fffffd"))
        ;
    }

    @Test
    void isValidPassportTrue() {
        var input = """
                ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
                byr:1937 iyr:2017 cid:147 hgt:183cm                
                """;
        final Map<Day4.PassportEntry, String> map = Day4.readPassport(input);
        assertThat(Day4.hasEnoughEntries(map)).isTrue();

    }

    @Test
    void isValidPassportFalse() {
        var input = """
                iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
                hcl:#cfa07d byr:1929
                """;
        final Map<Day4.PassportEntry, String> map = Day4.readPassport(input);
        assertThat(Day4.hasEnoughEntries(map)).isFalse();

    }

    @Test
    void isValidPassportCidMissingButTrue() {
        var input = """
                hcl:#ae17e1 iyr:2013
                eyr:2024
                ecl:brn pid:760753108 byr:1931
                hgt:179cm
                """;
        final Map<Day4.PassportEntry, String> map = Day4.readPassport(input);
        assertThat(Day4.hasEnoughEntries(map)).isTrue();

    }

    @Test
    void solve1() {
        assertThat(Day4.solve1(Input.puzzleInput(4))).isEqualTo(210);
    }

    @Test
    void solve2() {
        assertThat(Day4.solve2(Input.puzzleInput(4))).isEqualTo(131L);
    }
}
