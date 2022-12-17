package jab.aoc.day17;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class Day17Test {

    @Test
    void given_sampleData_when_execute_day17_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day17/input-sample.txt";

        //When
        Day17 day17 = new Day17();
        var result = day17.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(3068);
    }

}
