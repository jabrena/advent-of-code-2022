package info.aoc.jab;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Solution for AOC 2022, Day 1
 * https://adventofcode.com/2022/day/1
 *
 */
public class Problem1 {

    public static void main( String[] args ) {
        System.out.println( "Day 1: Calorie Counting" );

        Function<String, List<String>> loadFile = (fileName) -> {
            try {
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                URL resource = classloader.getResource(fileName);
                File file = new File(resource.toURI());
                return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        //TODO Refactor with a better Java Stream usage.
        Function<List<String>, List<Long>> group = (list) -> {
            AtomicLong counter = new AtomicLong(0);
            List<Long> groups = new ArrayList<>();
            list.stream()
                    .forEach(x -> {
                        if(x.length() != 0) {
                            counter.addAndGet(Long.parseLong(x));
                        } else {
                            groups.add(counter.get());
                            counter.set(0);
                        }
                    });

            return groups;
        };

        Function<List<Long>, Long> top1 = param -> param.stream()
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Katakroker"));

        Function<List<Long>, Long> top3 = param -> param.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                //.peek(System.out::println)
                .reduce(0L, (a, b) -> a + b);

        Consumer<String> showSolution1 = param -> {
            Long result = loadFile.andThen(group).andThen(top1).apply(param);
            System.out.println("Result1: " + result);
        };

        Consumer<String> showSolution2 = param -> {
            Long result = loadFile.andThen(group).andThen(top3).apply(param);
            System.out.println("Result2: " + result);
        };

        System.out.println("Sample");
        String param = "problem1-input-sample.txt";
        showSolution1.accept(param);
        showSolution2.accept(param);

        System.out.println("Problem sample");
        String param2 = "problem1-input.txt";
        showSolution1.accept(param2);
        showSolution2.accept(param2);
    }
}
