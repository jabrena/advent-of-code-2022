package info.aoc.jab;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution for AOC 2022, Day 1
 * https://adventofcode.com/2022/day/1
 *
 */
public class Problem1 {

    private static final Logger logger = LoggerFactory.getLogger(Problem1.class);

    public static void main(String[] args) {

        Function<String, List<String>> loadFile = fileName -> {
            try {
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                File file = new File(classloader.getResource(fileName).toURI());
                return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }
        };

        /**
         * Create a list of elements.
         * The source has a list of numbers, and it is necessary
         * to group and sum them; the rule is a separator that appear in the file
         */
        //TODO Refactor with a better Java Stream usage.
        Function<List<String>, List<Long>> group = list -> {
            AtomicLong counter = new AtomicLong(0);
            List<Long> groups = new ArrayList<>();
            list
                .stream()
                .forEach(x -> {
                    if (x.length() != 0) {
                        counter.addAndGet(Long.parseLong(x));
                    } else {
                        groups.add(counter.get());
                        counter.set(0);
                    }
                });

            return groups;
        };

        // @formatter:off
        BiFunction<List<Long>, Integer, Long> top = (param, limit) -> param.stream()
                .sorted(Comparator.reverseOrder())
                .limit(limit)
                .reduce(0L, Long::sum);
        // @formatter:on

        BiConsumer<String, Integer> showSolution = (file, limit) -> {
            var groups = loadFile.andThen(group).apply(file);
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
