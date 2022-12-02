package jab.aoc.day2;

import jab.aoc.Utils;
import java.util.stream.Stream;

/**
 * Solution for AOC 2022, Day 2
 * https://adventofcode.com/2022/day/2
 *
 */
public class Day2 {

    private static final String SEPARATOR = " ";

    public Integer getPart1Result(String fileName) {
        return Utils
            .loadFileToList(fileName)
            .stream()
            .map(line -> line.split(SEPARATOR))
            .map(arr -> new Game1(Column1.fromValue(arr[0]), Column2.fromValue(arr[1])))
            .map(Game1::getScore)
            .reduce(0, Integer::sum);
    }

    public Integer getPart2Result(String fileName) {
        return Utils
            .loadFileToList(fileName)
            .stream()
            .map(line -> line.split(SEPARATOR))
            .map(arr -> new Game2(Column1.fromValue(arr[0]), Column2.fromValue(arr[1])))
            .map(Game2::getScore)
            .reduce(0, Integer::sum);
    }
}
