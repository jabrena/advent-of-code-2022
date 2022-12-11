package jab.aoc.day10;

import jab.aoc.Utils;
import java.util.ArrayList;
import java.util.List;

public class Day10 {

    record Tuple(Integer cycle, Integer value) {}

    public static List<Tuple> process(List<String> data) {
        int value = 1;
        int cycle = 1;
        List<Tuple> list = new ArrayList<>();
        for (String line : data) {
            String[] split = line.split(" ");
            if (split[0].equals("noop")) {
                list.add(new Tuple(cycle, value));
                cycle += 1;
            } else {
                list.add(new Tuple(cycle, value));
                cycle += 1;
                list.add(new Tuple(cycle, value));
                cycle += 1;
                value += Integer.parseInt(split[1]);
            }
        }
        return list;
    }

    public Integer getPart1Result(String fileName) {

        var list = Utils.readFileToList(fileName);

        int[] cycles = {20, 60, 100, 140, 180, 220};
        int score = 0;
        var values = process(list);

        for(var c : cycles) {
            Tuple result = values.stream()
                    .filter(v -> v.cycle == c)
                    .findFirst()
                    .orElseThrow();
            score += c * result.value;
        }
        return score;
    }

    public String getPart2Result(String fileName) {

        var list = Utils.readFileToList(fileName);
        var values = process(list);

        List<String> screen = new ArrayList<>();
        screen.add("");
        for (var i : values) {
            if ((i.cycle - 1) % 40 == 0) {
                screen.add("");
            }
            screen.set(screen.size() - 1, screen.get(screen.size() - 1) +
                    (((i.cycle - 1) % 40 == i.value - 1
                            || (i.cycle - 1) % 40 == i.value
                            || (i.cycle - 1) % 40 == i.value + 1) ? "#" : "."));
        }
        return String.join("\n", screen);
    }
}
