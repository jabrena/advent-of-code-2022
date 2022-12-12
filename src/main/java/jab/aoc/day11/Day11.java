package jab.aoc.day11;

import static jab.aoc.Utils.GROUP_SEPARATOR_PATTERN;
import static jab.aoc.Utils.LINE_SEPARATOR_PATTERN;

import jab.aoc.Day;
import jab.aoc.Utils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day11 implements Day<Long> {

    @Override
    public Long getPart1Result(String fileName) {

        /*
        var data = Utils.readFileToString(fileName);
        var monkeyGroups = GROUP_SEPARATOR_PATTERN.split(data);
        Map<String, String> monkeys = new HashMap<>();
        Arrays.stream(monkeyGroups)
                .map(str -> LINE_SEPARATOR_PATTERN.split(str))
                .forEach(arr ->{
                    var monkeyKey = arr[0].replaceAll(":", "").toLowerCase();
                    System.out.println(monkeyKey);
                    monkeys.put(monkeyKey, "");
                    var items = arr[1].replaceAll("  Starting items: ", "");
                    var line2 = arr[2].replaceAll("  Operation: new = old ", "").split(" ");
                    var operator = line2[0];
                    var divisible = Integer.parseInt(arr[3].replaceAll("  Test: divisible by ", "").trim());
                    Arrays.stream(items.split(","))
                            .map(String::trim)
                            .map(Integer::valueOf)
                            .map(i -> {
                                var value = 0;
                                if(line2[1].equals("old")) {
                                    value = i;
                                } else {
                                    value = Integer.parseInt(line2[1]);
                                }
                                if (operator.equals("*")) {
                                    return i * value;
                                } else {
                                    return i + value;
                                }
                            })
                            .forEach(i -> {
                                Integer result = (int) Math.floor(i / 3);
                                System.out.println(result);
                                if ((result % divisible) == 0) {
                                    var monkey = arr[4].replaceAll("    If true: throw to ", "");
                                    monkeys.put(monkey, monkeys.get(monkey) + " " + result);
                                } else {
                                    var monkey = arr[5].replaceAll("    If false: throw to ", "");
                                    monkeys.put(monkey, monkeys.get(monkey) + " " + result);
                                }
                            });
                });

        for (Map.Entry<String, String> entry : monkeys.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

         */

        Solution solution = new Solution();
        var result = solution.runPart1(fileName);

        return result;
    }

    @Override
    public Long getPart2Result(String fileName) {
        Solution solution = new Solution();
        var result = solution.runPart2(fileName);

        return result;
    }
}
