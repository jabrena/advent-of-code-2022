package jab.aoc.day17;

import jab.aoc.Day;
import jab.aoc.Utils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class Day17 implements Day<Long> {

    @Override
    public Long getPart1Result(String fileName) {

        var commands = Utils.readFileToString(fileName).replaceAll("\\n", "");
        System.out.println(commands);
        System.out.println(commands.length());

        Deque<String> game = new LinkedList<>();

        IntStream.rangeClosed(0, commands.length() - 1).boxed()
                .map(commands::charAt)
                .forEach(System.out::println);

        return null;
    }

    @Override
    public Long getPart2Result(String fileName) {
        return null;
    }
}
