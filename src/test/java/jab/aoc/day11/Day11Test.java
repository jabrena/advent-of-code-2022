package jab.aoc.day11;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class Day11Test {

    @Test
    void given_sampleData_when_execute_day11_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day11/input-sample.txt";

        //When
        Day11 day11 = new Day11();
        var result = day11.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(10);
    }
}
