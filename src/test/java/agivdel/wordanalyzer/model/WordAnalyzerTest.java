package agivdel.wordanalyzer.model;

import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class WordAnalyzerTest {

    @Test
    public void getMostCommonLetter_returns_the_most_common_letter_and_its_index() {
        String input = "test";
        Map<Character, Integer> output = Map.of('t', 2);

        assertEquals(output, new WordAnalyzer().getMostCommonLetter(input));
    }

    @Test
    public void getMostCommonLetter_returns_the_last_of_the_most_common_letters_and_its_index() {
        String input = "tester";
        Map<Character, Integer> output = Map.of('e', 2);

        assertEquals(output, new WordAnalyzer().getMostCommonLetter(input));
    }

    @Test
    public void getMostCommonLetter_case_of_letters_does_not_matter() {
        String input = "testeredTest";
        Map<Character, Integer> output = Map.of('t', 4);

        assertEquals(output, new WordAnalyzer().getMostCommonLetter(input));
    }

    @Test
    public void getMostCommonLetter_exception_when_word_does_not_contain_any_letters() {
        String input = "1";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            Map<Character, Integer> result = new WordAnalyzer().getMostCommonLetter(input);
            throw new IllegalArgumentException();
        });

        assertEquals(String.format("The word '%s' does not contain any letter.", input), e.getMessage());
    }
}