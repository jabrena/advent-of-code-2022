package info.aoc.jab;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
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

    private static final String ELF_SEPARATOR = System.lineSeparator() + System.lineSeparator();
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static void main(String[] args) {
        /**
         * Create a list of elements.
         * The source has a list of numbers, and it is necessary
         * to group and sum them; the rule is a separator that appear in the file
         */
        Function<String, List<Long>> group = file ->
            Arrays
                .stream(file.split(ELF_SEPARATOR))
                .map(elf -> elf.split(LINE_SEPARATOR))
                .map(Arrays::stream)
                .map(elf -> elf.mapToLong(Long::parseLong))
                .mapToLong(LongStream::sum)
                .boxed()
                .collect(Collectors.toList());

        // @formatter:off
        BiFunction<List<Long>, Integer, Long> top = (param, limit) -> param.stream()
                .sorted(Comparator.reverseOrder())
                .limit(limit)
                .reduce(0L, Long::sum);
        // @formatter:on

        BiConsumer<String, Integer> showSolution = (file, limit) -> {
            var fileAsString = Utils.readFileToString.apply(file);
            var groups = group.apply(fileAsString);
            var result = top.apply(groups, limit);
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
