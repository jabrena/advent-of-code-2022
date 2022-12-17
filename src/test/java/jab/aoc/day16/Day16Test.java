package jab.aoc.day16;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class Day16Test {

    @Test
    void given_sampleData_when_execute_day16_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day16/input-sample.txt";

        //When
        Day16 day16 = new Day16();
        var result = day16.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(1651);
    }

    @Test
    void given_data_when_execute_day16_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day16/input.txt";

        //When
        Day16 day16 = new Day16();
        var result = day16.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(1651);
    }
}
