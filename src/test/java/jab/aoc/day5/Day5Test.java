package jab.aoc.day5;

import jab.aoc.day4.Day4;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class Day5Test {

    @Test
    public void given_sampleData_when_execute_day5_getPart1Solution_then_expected_result() {
        //Given
        String fileName = "day5/input-sample.txt";

        //When
        Day5 day5 = new Day5();
        var result = day5.getPart1Result(fileName);

        //Then
        then(result).isEqualTo("CMZ");
    }
}
