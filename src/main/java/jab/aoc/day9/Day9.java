package jab.aoc.day9;

import jab.aoc.Utils;
import java.util.Arrays;

public class Day9 {

    public Long getPart1Result(String fileName) {
        RopePhysics ropePhysics = new RopePhysics();
        ropePhysics.create(25, 25);
        ropePhysics.setInitialState();
        ropePhysics.print();

        // @formatter:off
        Utils.readFileToList(fileName).stream()
                .map(Movement::fromString)
                .forEach(ropePhysics::execute);

        ropePhysics.printVisited();
        return Arrays.stream(ropePhysics.getCellsVisited())
                .flatMap(Arrays::stream)
                .filter(v -> v)
                .count();
        // @formatter:on
    }
}
