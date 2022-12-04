package jab.aoc.day4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class Day4Test {

    @Test
    public void given_sampleData_when_execute_day4_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day4/input-sample.txt";

        //When
        Day4 day4 = new Day4();
        var result = day4.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(2);
    }
}
