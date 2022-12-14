package jab.aoc.day14;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class Day14Test {

    @Test
    void given_sampleData_when_execute_day14_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day14/input-sample.txt";

        //When
        Day14 day14 = new Day14();
        var result = day14.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(13);
    }
}
