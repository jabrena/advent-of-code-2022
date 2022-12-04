package jab.aoc.day4;

import jab.aoc.Utils;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Day4 {

    /**
     * Detect if one list is a Subset of another
     */
    private BiFunction<List<Integer>, List<Integer>, Boolean> isSubset = (list1, list2) -> {
        return list1.containsAll(list2);
    };

    // @formatter:off
    private Function<String, List<Integer>> toListOfIntegers = arr -> {
        String[] parts1 = arr.split("-");
        return IntStream
                .rangeClosed(Integer.parseInt(parts1[0]), Integer.parseInt(parts1[1]))
                .boxed()
                .toList();
    };

    // @formatter:on

    private record Tuple(List<Integer> part1, List<Integer> part2) {}

    private Function<String, Tuple> toTuple = param -> {
        String[] arr = param.split(",");
        return new Tuple(toListOfIntegers.apply(arr[0]), toListOfIntegers.apply(arr[1]));
    };

    public Long getPart1Result(String fileName) {
        // @formatter:off
        Function<Tuple, Boolean> areSubset = tuple -> {
            if (isSubset.apply(tuple.part1(), tuple.part2())
                || isSubset.apply(tuple.part2(), tuple.part1())) {
                return true;
            }
            return false;
        };

        return Utils.loadFileToList(fileName)
                .stream()
                .map(toTuple)
                .map(areSubset)
                .filter(value -> value)
                .count();
        // @formatter:on
    }

    public Long getPart2Result(String fileName) {
        /**
         * Detect if 2 Arrays Overlaps
         */
        BiFunction<List<Integer>, List<Integer>, Boolean> overlap = (list1, list2) -> {
            HashSet<Integer> set2 = new HashSet<>();
            set2.addAll(list1);
            set2.retainAll(list2);

            if (set2.size() > 0) {
                return true;
            }
            return false;
        };

        return Utils
            .loadFileToList(fileName)
            .stream()
            .map(toTuple)
            .map(tuple -> overlap.apply(tuple.part1(), tuple.part2()))
            .filter(value -> value)
            .count();
    }
}
