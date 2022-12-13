package jab.aoc.day13;

import jab.aoc.day9.Day9;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class Day13Test {

    @Test
    void given_sampleData_when_execute_day13_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day13/input-sample.txt";

        //When
        Day13 day13 = new Day13();
        var result = day13.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(13);
    }
}
