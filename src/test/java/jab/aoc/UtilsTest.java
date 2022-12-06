package jab.aoc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void given_unknownFile_when_loadFile_then_expected_Ko() {
        //Given
        String fileName = "unknownFile.txt";

        //TODO Refactor the code to avoid this test
        //When
        // @formatter:off
        NullPointerException thrown =
                assertThrows(NullPointerException.class, () -> Utils.loadFileToList(fileName));

        // @formatter:on

        //Then
        then(thrown.getMessage()).contains("Cannot invoke \"java.net.URL.getFile()\"");
    }

    @Test
    void given_string_when_convertToSet_then_expected_result() {
        //Given
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        //When
        var result = Utils.getUniqueCharactersAsSet(alphabet);

        //Then
        then(result.size()).isEqualTo(alphabet.length());
    }

    @Test
    void given_string_when_convertToSet_nonHappyPath_then_expected_result() {
        //Given
        String sample = "AAA";

        //When
        var result = Utils.getUniqueCharactersAsSet(sample);

        //Then
        then(result.size()).isEqualTo(1);
    }

    @Test
    void given_string_when_convertToHashSet_then_expected_result() {
        //Given
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        //When
        var result = Utils.getUniqueCharactersAsHashSet(alphabet);

        //Then
        then(result.size()).isEqualTo(alphabet.length());
    }

    @Test
    void given_string_when_convertToHashSet_nonHappyPath_then_expected_result() {
        //Given
        String sample = "AAA";

        //When
        var result = Utils.getUniqueCharactersAsHashSet(sample);

        //Then
        then(result.size()).isEqualTo(1);
    }
}
