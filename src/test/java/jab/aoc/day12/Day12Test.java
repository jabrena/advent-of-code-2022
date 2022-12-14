package jab.aoc.day12;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class Day12Test {

    @Test
    void given_sampleData_when_execute_day12_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day12/input-sample.txt";

        //When
        Day12 day12 = new Day12();
        var result = day12.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(31);
    }

    @Test
    void given_data_when_execute_day12_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day12/input.txt";

        //When
        Day12 day12 = new Day12();
        var result = day12.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(490);
    }

    @Test
    void given_sampleData_when_execute_day12_getPart2Solution_then_expected_result() {
        //Given
        String fileName = "day12/input-sample.txt";

        //When
        Day12 day12 = new Day12();
        var result = day12.getPart2Result(fileName);

        //Then
        then(result).isEqualTo(29);
    }

    @Test
    void given_data_when_execute_day12_getPart2Solution_then_expected_result() {
        //Given
        String fileName = "day12/input.txt";

        //When
        Day12 day12 = new Day12();
        var result = day12.getPart2Result(fileName);

        //Then
        then(result).isEqualTo(488);
    }
}
