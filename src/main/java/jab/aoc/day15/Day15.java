package jab.aoc.day15;

import jab.aoc.Day;
import jab.aoc.Utils;
import java.util.regex.Pattern;

public class Day15 implements Day<Long> {

    //TODO Improvement
    //private static final Pattern PATTERN =
    //Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+):
    // closest beacon is at x=(-?\\d+), y=(-?\\d+)");

    private String[][] createMatrix(String fileName) {
        System.out.println("1. Calculate matrix dimensions");

        var minSensorX = Utils
            .readFileToList(fileName)
            .stream()
            .map(str -> str.split(":"))
            .map(arr -> arr[0])
            .map(str -> str.replaceAll("Sensor at x=", ""))
            .map(str -> str.split(","))
            .map(arr -> arr[0])
            //.peek(System.out::println)
            .mapToLong(Long::valueOf)
            .min()
            .orElseThrow();

        var maxSensorX = Utils
            .readFileToList(fileName)
            .stream()
            .map(str -> str.split(":"))
            .map(arr -> arr[0])
            .map(str -> str.replaceAll("Sensor at x=", ""))
            .map(str -> str.split(","))
            .map(arr -> arr[0])
            //.peek(System.out::println)
            .mapToLong(Long::valueOf)
            .max()
            .orElseThrow();

        var minSensorY = Utils
            .readFileToList(fileName)
            .stream()
            .map(str -> str.split(":"))
            .map(arr -> arr[0])
            .map(str -> str.replaceAll("Sensor at x=", ""))
            .map(str -> str.split(","))
            .map(arr -> arr[1])
            .map(str -> str.replaceAll("y=", ""))
            .map(String::trim)
            //.peek(System.out::println)
            .mapToLong(Long::valueOf)
            .min()
            .orElseThrow();

        var maxSensorY = Utils
            .readFileToList(fileName)
            .stream()
            .map(str -> str.split(":"))
            .map(arr -> arr[0])
            .map(str -> str.replaceAll("Sensor at x=", ""))
            .map(str -> str.split(","))
            .map(arr -> arr[1])
            .map(str -> str.replaceAll("y=", ""))
            .map(String::trim)
            //.peek(System.out::println)
            .mapToLong(Long::valueOf)
            .max()
            .orElseThrow();

        System.out.println("Sensor Matrix sizes:");
        System.out.println(minSensorX + "," + minSensorY);
        System.out.println(maxSensorX + "," + maxSensorX);
        System.out.println();

        var minBeaconX = Utils
            .readFileToList(fileName)
            .stream()
            .map(str -> str.split(":"))
            .map(arr -> arr[1])
            .map(str -> str.replaceAll("closest beacon is at x=", ""))
            .map(str -> str.split(","))
            .map(str -> str[0])
            .map(String::trim)
            .mapToLong(Long::valueOf)
            .min()
            .orElseThrow();

        var maxBeaconX = Utils
            .readFileToList(fileName)
            .stream()
            .map(str -> str.split(":"))
            .map(arr -> arr[1])
            .map(str -> str.replaceAll("closest beacon is at x=", ""))
            .map(str -> str.split(","))
            .map(str -> str[0])
            .map(String::trim)
            .mapToLong(Long::valueOf)
            .max()
            .orElseThrow();

        var minBeaconY = Utils
            .readFileToList(fileName)
            .stream()
            .map(str -> str.split(":"))
            .map(arr -> arr[1])
            .map(str -> str.replaceAll("closest beacon is at x=", ""))
            .map(str -> str.split(","))
            .map(str -> str[1])
            .map(str -> str.replaceAll("y=", ""))
            .map(String::trim)
            .mapToLong(Long::valueOf)
            .min()
            .orElseThrow();

        var maxBeaconY = Utils
            .readFileToList(fileName)
            .stream()
            .map(str -> str.split(":"))
            .map(arr -> arr[1])
            .map(str -> str.replaceAll("closest beacon is at x=", ""))
            .map(str -> str.split(","))
            .map(str -> str[1])
            .map(str -> str.replaceAll("y=", ""))
            .map(String::trim)
            .mapToLong(Long::valueOf)
            .max()
            .orElseThrow();

        System.out.println("Beacon Matrix sizes:");
        System.out.println(minBeaconX + "," + minBeaconY);
        System.out.println(maxBeaconX + "," + maxBeaconY);
        System.out.println();

        var absoluteMinX = Math.min(minSensorX, minBeaconX);
        var absoluteMaxX = Math.max(maxSensorX, maxBeaconX);
        var absoluteMinY = Math.min(minSensorY, minBeaconY);
        var absoluteMaxY = Math.max(maxSensorY, maxBeaconY);

        System.out.println("Matrix sizes:");
        System.out.println(absoluteMinX + "," + absoluteMinY);
        System.out.println(absoluteMaxX + "," + absoluteMaxY);
        System.out.println();

        Integer dimX = Math.toIntExact(Math.abs(absoluteMinX) + absoluteMaxX);
        Integer dimY = Math.toIntExact(absoluteMinY + absoluteMaxY);
        System.out.println(dimX + "," + dimY);

        String[][] matrix = new String[dimY + 1][dimX + 1];

        System.out.println("Load Sensors in the matrix");
        Utils
            .readFileToList(fileName)
            .stream()
            .map(str -> str.split(":"))
            .map(arr -> arr[0])
            .map(str -> str.replaceAll("Sensor at x=", ""))
            .forEach(str -> {
                var parts = str.split(",");
                int x = Integer.parseInt(parts[0].trim()) + (int) Math.abs(absoluteMinX);
                int y = Integer.parseInt(parts[1].trim().replaceAll("y=", ""));

                matrix[y][x] = "S";
            });

        System.out.println("Load Beacons in the matrix");
        Utils
            .readFileToList(fileName)
            .stream()
            .map(str -> str.split(":"))
            .map(arr -> arr[1])
            .map(str -> str.replaceAll("closest beacon is at x=", ""))
            .forEach(str -> {
                var parts = str.split(",");
                int x = Integer.parseInt(parts[0].trim()) + (int) Math.abs(absoluteMinX);
                int y = Integer.parseInt(parts[1].trim().replaceAll("y=", ""));

                matrix[y][x] = "B";
            });

        for (var y = 0; y < matrix.length; y++) {
            if (y < 10) {
                System.out.print(" ");
            }
            System.out.print(y);
            System.out.print(" ");
            for (var x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] == null) {
                    System.out.print(".");
                    continue;
                }
                if (matrix[y][x].equals("B")) {
                    System.out.print("B");
                    continue;
                }
                if (matrix[y][x].equals("S")) {
                    System.out.print("S");
                    continue;
                }
            }
            System.out.println();
        }

        return matrix;
    }

    record Point(int x, int y) {}

    private int distManhattan(Point origin, Point destination) {
        return Math.abs(origin.x - destination.x) + Math.abs(origin.y - destination.y);
    }

    @Override
    public Long getPart1Result(String fileName) {
        var y = 10;

        var matrix = createMatrix(fileName);

        return null;
    }

    @Override
    public Long getPart2Result(String fileName) {
        var y = 2000000;

        return null;
    }
}
