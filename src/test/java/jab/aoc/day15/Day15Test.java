package jab.aoc.day15;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

public class Day15Test {

    @Test
    void given_sampleData_when_execute_day15_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day15/input-sample.txt";

        //When
        Day15 day13 = new Day15();
        var result = day13.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(26);
    }
}
