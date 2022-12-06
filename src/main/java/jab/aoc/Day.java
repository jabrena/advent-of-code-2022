package jab.aoc;

/**
 * Advent of Code define a set of problems every December.
 * Every day, the website publish 2 problems to solve.
 * This interface define the problems to solve per Day.
 *
 * @param <T>
 */
public interface Day<T> {
    T getPart1Result(String fileName);

    T getPart2Result(String fileName);
}
