package jab.aoc.day6;

import static jab.aoc.Utils.getUniqueHashSetString;

import jab.aoc.Day;
import jab.aoc.Utils;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Day6 implements Day {

    private Function<String, Boolean> areUniqueCharacters = param ->
        getUniqueHashSetString.apply(param).size() == param.length();

    private BiFunction<String, Integer, Integer> detectMarker = (line, markerLength) -> {
        Integer result = 0;

        //Sometimes is not possible to use Streams ¯\_(ツ)_/¯
        for (int i = 0; i < line.length(); i++) {
            String chunkToEvaluate = line.substring(i, i + markerLength);
            result = i + markerLength;
            if (areUniqueCharacters.apply(chunkToEvaluate)) {
                break;
            }
        }
        return result;
    };

    // @formatter:off
    @Override
    public List<Integer> getPart1Result(String fileName) {
        Integer markerLength = 4;

        return Utils.loadFileToList(fileName).stream()
                .map(line -> detectMarker.apply(line, markerLength))
                .toList();
    }

    @Override
    public List<Integer> getPart2Result(String fileName) {
        Integer markerLength = 14;

        return Utils.loadFileToList(fileName).stream()
                .map(str -> detectMarker.apply(str, markerLength))
                .toList();
    }
    // @formatter:on
}
