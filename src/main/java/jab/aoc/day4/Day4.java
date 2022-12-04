package jab.aoc.day4;

import jab.aoc.Utils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Day4 {

    //From: https://www.geeksforgeeks.org/find-whether-an-array-is-subset-of-another-array-set-1/
    private boolean isSubset(Integer[] arr1, Integer[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int i = 0;
        int j = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (arr2[i] == arr1[j]) {
                    break;
                }
            }
            if (j == m) {
                return false;
            }
        }
        return true;
    }

    // @formatter:off
    private Function<String, Integer[]> toIntArr = arr -> {
        String[] parts1 = arr.split("-");
        var list1 = IntStream
                .rangeClosed(Integer.parseInt(parts1[0]), Integer.parseInt(parts1[1]))
                .boxed()
                .toList();

        //TODO Refactor; Coupled to the method to detect subsets
        Integer[] parts1Arr = new Integer[list1.size()];
        parts1Arr = list1.toArray(parts1Arr);

        return parts1Arr;
    };

    // @formatter:on

    record Tuple(Integer[] part1, Integer[] part2) {}

    Function<String, Tuple> toTuple = param -> {
        String[] arr = param.split(",");
        return new Tuple(toIntArr.apply(arr[0]), toIntArr.apply(arr[1]));
    };

    public Long getPart1Result(String fileName) {
        Function<Tuple, Boolean> areSubset = tuple -> {
            if (isSubset(tuple.part1(), tuple.part2()) || isSubset(tuple.part2(), tuple.part1())) {
                return true;
            }
            return false;
        };

        // @formatter:off
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
        BiFunction<Integer[], Integer[], Boolean> overlap = (arr1, arr2) -> {
            HashSet<Integer> set2 = new HashSet<>();
            set2.addAll(Arrays.asList(arr1));
            set2.retainAll(Arrays.asList(arr2));

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
