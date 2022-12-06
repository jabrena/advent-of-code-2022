package jab.aoc.day6;

import static jab.aoc.Utils.getUniqueHashSetString;

import jab.aoc.Day;
import jab.aoc.Utils;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Solution for AOC 2022, Day 6
 * https://adventofcode.com/2022/day/6
 *
 */
public class Day6 implements Day<List<Integer>> {

    private Predicate<String> areUniqueCharacters = param ->
        getUniqueHashSetString.apply(param).size() == param.length();

    private BiFunction<String, Integer, Integer> detectMarker = (line, markerLength) -> {
        Integer result = 0;

        //Sometimes is not possible to use Streams ¯\_(ツ)_/¯
        for (int i = 0; i < line.length(); i++) {
            String chunkToEvaluate = line.substring(i, i + markerLength);
            result = i + markerLength;
            if (areUniqueCharacters.test(chunkToEvaluate)) {
                break;
            }
        }
        return result;
    };

    // @formatter:off
    @Override
    public List<Integer> getPart1Result(String fileName) {
        return Utils.loadFileToList(fileName).stream()
                .map(line -> detectMarker.apply(line, 4))
                .toList();
    }

    @Override
    public List<Integer> getPart2Result(String fileName) {
        return Utils.loadFileToList(fileName).stream()
                .map(str -> detectMarker.apply(str, 14))
                .toList();
    }
    // @formatter:on
}
