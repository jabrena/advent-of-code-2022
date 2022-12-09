package jab.aoc.day9;

import jab.aoc.Utils;
import java.util.Arrays;

public class Day9 {

    public Long getPart1Result(String fileName) {
        RopePhysics ropePhysics = new RopePhysics();
        //ropePhysics.create(6,5);
        ropePhysics.create(25, 25);
        ropePhysics.setInitialState();
        //ropePhysics.print();

        Utils
            .readFileToList(fileName)
            .stream()
            //.limit(1)
            .map(Movement::fromString)
            .forEach(movement -> {
                ropePhysics.execute(movement);
            });

        ropePhysics.printVisited();
        return Arrays
            .stream(ropePhysics.getCellsVisited())
            .flatMap(i -> Arrays.stream(i))
            .filter(v -> v == true)
            .count();
    }
}
