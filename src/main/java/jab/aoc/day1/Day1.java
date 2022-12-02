package jab.aoc.day1;

import jab.aoc.Utils;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.stream.LongStream;

/**
 * Solution for AOC 2022, Day 1
 * https://adventofcode.com/2022/day/1
 *
 */
public class Day1 {

    private static final String GROUP_SEPARATOR = System.lineSeparator() + System.lineSeparator();
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private BiFunction<String, Integer, Long> processData = (file, limit) ->
        Arrays
            .stream(file.split(GROUP_SEPARATOR))
            .map(group -> Arrays.stream(group.split(LINE_SEPARATOR)))
            .flatMapToLong(item -> LongStream.of(item.mapToLong(Long::parseLong).sum()))
            .boxed()
            .sorted(Comparator.reverseOrder())
            .limit(limit)
            .reduce(0L, Long::sum);

    public Long getPart1Result(String fileName) {
        var fileLoaded = Utils.readFileToString(fileName);
        return processData.apply(fileLoaded, 1);
    }

    public Long getPart2Result(String fileName) {
        var fileLoaded = Utils.readFileToString(fileName);
        return processData.apply(fileLoaded, 3);
    }
}
