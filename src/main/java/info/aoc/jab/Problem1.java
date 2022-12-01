package info.aoc.jab;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.LongStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution for AOC 2022, Day 1
 * https://adventofcode.com/2022/day/1
 *
 */
public class Problem1 {

    private static final Logger logger = LoggerFactory.getLogger(Problem1.class);

    private static final String GROUP_SEPARATOR = System.lineSeparator() + System.lineSeparator();
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static void main(String[] args) {
        BiFunction<String, Integer, Long> processData = (file, limit) ->
            Arrays
                .stream(file.split(GROUP_SEPARATOR))
                .map(group -> Arrays.stream(group.split(LINE_SEPARATOR)))
                .flatMapToLong(item -> LongStream.of(item.mapToLong(Long::parseLong).sum()))
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(limit)
                .reduce(0L, Long::sum);

        BiConsumer<String, Integer> showSolution = (fileName, limit) -> {
            var fileLoaded = Utils.readFileToString(fileName);
            var result = processData.apply(fileLoaded, limit);
            logger.info("Result2: " + result);
        };

        logger.info("Day 1: Calorie Counting");

        logger.info("Sample");
        String file = "problem1-input-sample.txt";
        showSolution.accept(file, 1);
        showSolution.accept(file, 3);

        logger.info("Problem");
        String file2 = "problem1-input.txt";
        showSolution.accept(file2, 1);
        showSolution.accept(file2, 3);
    }
}
