package jab.aoc.day8;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class Day8Test {

    @Test
    void given_sampleData_when_execute_day8_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day8/input-sample.txt";

        //When
        Day8 day8 = new Day8();
        var result = day8.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(100);
    }
}
