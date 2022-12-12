package jab.aoc.day12;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class Day12Test {

    @Test
    void given_sampleData_when_execute_day9_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day12/input-sample.txt";

        //When
        Day12 day12 = new Day12();
        var result = day12.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(10);
    }
}
