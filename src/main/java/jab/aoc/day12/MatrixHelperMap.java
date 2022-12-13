package jab.aoc.day12;

import jab.aoc.Utils;

import java.util.concurrent.atomic.AtomicInteger;

class MatrixHelperMap {

    public static String[][] getMatrix(String fileName) {
        // @formatter:off
        //Initialize Matrix
        var list = Utils.readFileToList(fileName);
        Integer noOfRows = list.size();
        Integer noOfColumns = list.stream()
                .limit(1)
                .mapToInt(String::length)
                .findAny()
                .orElseThrow();
        String[][] matrix = new String[noOfRows][noOfColumns];

        //Populate Matrix
        AtomicInteger x = new AtomicInteger(0);
        AtomicInteger y = new AtomicInteger(0);
        Utils.readFileToList(fileName).stream()
            .map(Utils::getCharactersAsList)
            .forEach(row -> {
                row.stream()
                   .forEach(column -> {

                        matrix[y.get()][x.get()] = column;
                        x.incrementAndGet();
                    });
                x.set(0);
                y.incrementAndGet();
            });

        return matrix;
        // @formatter:on
    }
}
