package jab.aoc.day6;

import jab.aoc.day5.Day5;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class Day6Test {

    @Test
    void given_sampleData_when_execute_day6_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day6/input-sample.txt";

        //When
        Day6 day6 = new Day6();
        var result = day6.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(10);
    }
}
