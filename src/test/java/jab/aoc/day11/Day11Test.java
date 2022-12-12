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
        then(result).isEqualTo(10605);
    }

    @Test
    void given_data_when_execute_day11_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day11/input.txt";

        //When
        Day11 day11 = new Day11();
        var result = day11.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(72884);
    }

    @Test
    void given_sampleData_when_execute_day11_getPart2Solution_then_expected_result() {
        //Given
        String fileName = "day11/input-sample.txt";

        //When
        Day11 day11 = new Day11();
        var result = day11.getPart2Result(fileName);

        //Then
        then(result).isEqualTo(2713310158L);
    }

    @Test
    void given_data_when_execute_day11_getPart2Solution_then_expected_result() {
        //Given
        String fileName = "day11/input.txt";

        //When
        Day11 day11 = new Day11();
        var result = day11.getPart2Result(fileName);

        //Then
        then(result).isEqualTo(15310845153L);
    }
}
