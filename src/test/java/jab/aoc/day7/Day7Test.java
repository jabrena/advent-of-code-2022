package jab.aoc.day7;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class Day7Test {

    @Test
    void given_sampleData_when_execute_day6_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day7/input-sample.txt";

        //When
        Day7 day7 = new Day7();
        var result = day7.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(95437);
    }
}
