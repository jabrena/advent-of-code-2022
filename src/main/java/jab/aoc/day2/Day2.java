package jab.aoc.day2;

import jab.aoc.Utils;
import java.util.List;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution for AOC 2022, Day 2
 * https://adventofcode.com/2022/day/2
 *
 */
public class Day2 {

    private static final Logger logger = LoggerFactory.getLogger(Day2.class);

    private static String SEPARATOR = " ";

    public static void main(String[] args) {
        Function<List<String>, Integer> getPart1Result = fileContent -> {
            return fileContent
                .stream()
                .map(line -> line.split(SEPARATOR))
                .map(arr -> new Game1(Column1.fromValue(arr[0]), Column2.fromValue(arr[1])))
                .map(Game1::getScore)
                .reduce(0, Integer::sum);
        };

        Function<List<String>, Integer> getPart2Result = fileContent -> {
            return fileContent
                .stream()
                .map(line -> line.split(SEPARATOR))
                .map(arr -> new Game2(Column1.fromValue(arr[0]), Column2.fromValue(arr[1])))
                .map(Game2::getScore)
                .reduce(0, Integer::sum);
        };

        logger.info("Day 2: Rock Paper Scissors");

        var content = Utils.loadFileToList("day2/day2-input-sample.txt");
        var content2 = Utils.loadFileToList("day2/day2-input.txt");

        logger.info("Sample");
        logger.info("Result: " + getPart1Result.apply(content));
        logger.info("Result: " + getPart2Result.apply(content));

        logger.info("Problem");
        logger.info("Result: " + getPart1Result.apply(content2));
        logger.info("Result: " + getPart2Result.apply(content2));
    }
}
