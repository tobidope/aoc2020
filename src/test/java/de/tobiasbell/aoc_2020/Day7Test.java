package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.InputReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day7Test {

    @Test
    void solve1() throws IOException {
        var example = """                
                light red bags contain 1 bright white bag, 2 muted yellow bags.
                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                bright white bags contain 1 shiny gold bag.
                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                faded blue bags contain no other bags.
                dotted black bags contain no other bags.
                """;
        final long result = Day7.solve1(example);
        assertThat(result).isEqualTo(4);
        assertThat(Day7.solve1(InputReader.getInput(7))).isEqualTo(326);
    }

    @Test
    void parseBagRuleReturnsEmpyListOnNoOtherBags() {
        // given
        var input = "faded blue bags contain no other bags.";
        // when
        final List<Day7.BagRule> rules = Day7.BagRule.parse(input);
        //then
        assertThat(rules).isEmpty();
    }

    @Test
    void parseBagGraph() {
        // given
        var example = """                
                light red bags contain 1 bright white bag, 2 muted yellow bags.
                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                bright white bags contain 1 shiny gold bag.
                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                faded blue bags contain no other bags.
                dotted black bags contain no other bags.
                """;
        // when
        final Map<String, List<Day7.BagRule>> graph = Day7.parseBagGraph(example);
        //then
        assertThat(graph).hasSize(9)
                .containsKey("light red")
                .containsEntry("shiny gold", List.of(
                        new Day7.BagRule("dark olive", 1),
                        new Day7.BagRule("vibrant plum", 2)));
    }

    @Test
    void parseBagRuleReturnsTwoRules() {
        // given
        var input = "light red bags contain 1 bright white bag, 2 muted yellow bags.";
        // when
        final List<Day7.BagRule> rules = Day7.BagRule.parse(input);
        //then
        assertThat(rules).containsExactly(
                new Day7.BagRule("bright white", 1),
                new Day7.BagRule("muted yellow", 2)
        );
    }

    @Test
    void solve2() throws IOException {
        // given
        var example = """
                shiny gold bags contain 2 dark red bags.
                dark red bags contain 2 dark orange bags.
                dark orange bags contain 2 dark yellow bags.
                dark yellow bags contain 2 dark green bags.
                dark green bags contain 2 dark blue bags.
                dark blue bags contain 2 dark violet bags.
                dark violet bags contain no other bags.
                """;
        // when
        final long result = Day7.solve2(example);
        //then
        assertThat(result).isEqualTo(126);

        assertThat(Day7.solve2(InputReader.getInput(7))).isEqualTo(5635L);
    }
}