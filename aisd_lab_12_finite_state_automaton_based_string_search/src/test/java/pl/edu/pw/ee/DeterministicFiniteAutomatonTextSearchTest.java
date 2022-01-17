package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class DeterministicFiniteAutomatonTextSearchTest {
    private DeterministicFiniteAutomatonTextSearch dSearch;

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_PatternIsEmpty() {
        // given
        String pattern = "";
        String text = "BAEGH";

        // when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_PatternIsNull() {
        // given
        String pattern = null;
        String text = "BAEGH";

        // when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TextIsNull() {
        // given
        String pattern = "OHO";
        String text = null;

        // when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = dSearch.findFirst(text);

        // then
        assert false;
    }

    @Test
    public void should_FindFirst_When_PatternAndTextAreEqualAndHaveOneLetter() {
        // given
        String pattern = "O";
        String text = "O";

        // when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = dSearch.findFirst(text);

        // then
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectVal_When_PatternAndTextAreNotEqualAndHaveOneLetter() {
        // given
        String pattern = "Q";
        String text = "O";

        // when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = dSearch.findFirst(text);

        // then
        int expected = -1;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectVal_When_TextIsEmpty() {
        // given
        String pattern = "ABA";
        String text = "";

        // when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = dSearch.findFirst(text);

        // then
        int expected = -1;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnFirstFind_When_TextHasMultiplePatterns() {
        // given
        String pattern = "ABA";
        String text = "ERABABABA";

        // when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = dSearch.findFirst(text);

        // then
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnFirstFind_When_TextHasPatternAtBegining() {
        // given
        String pattern = "ABA";
        String text = "ABAJKJHKL";

        // when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = dSearch.findFirst(text);

        // then
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnFirstFind_When_TextAndPatternHaveSpecialCharacters() {
        // given
        String newLinePattern = "\n";
        String tabPattern = "\t";
        String carriageReturnPattern = "\r";
        String winNewLinePattern = "\r\n";

        String text = "A\tSAJ\nBB\rMM\r\n";

        // when
        dSearch = new DeterministicFiniteAutomatonTextSearch(newLinePattern);
        int actualNewLine = dSearch.findFirst(text);

        dSearch = new DeterministicFiniteAutomatonTextSearch(tabPattern);
        int actualTab = dSearch.findFirst(text);

        dSearch = new DeterministicFiniteAutomatonTextSearch(carriageReturnPattern);
        int actualCarriageReturn = dSearch.findFirst(text);

        dSearch = new DeterministicFiniteAutomatonTextSearch(winNewLinePattern);
        int actualWinNewLine = dSearch.findFirst(text);

        // then
        int expectedNewLine = 5;
        int expectedTab = 1;
        int expectedCarriageReturn = 8;
        int expectedWinNewLine = 11;

        assertEquals(expectedNewLine, actualNewLine);
        assertEquals(expectedTab, actualTab);
        assertEquals(expectedCarriageReturn, actualCarriageReturn);
        assertEquals(expectedWinNewLine, actualWinNewLine);
    }

    @Test
    public void should_ReturnFirstFind_When_TextHasPatternAtTheEnd() {
        // given
        String pattern = "ABA";
        String text = "JKJHKLABA";

        // when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = dSearch.findFirst(text);

        // then
        int expected = 6;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnFirstFind_When_TextHasPatternInMiddle() {
        // given
        String pattern = "ABA";
        String text = "JKJABAHKL";

        // when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = dSearch.findFirst(text);

        // then
        int expected = 3;

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToFindAllInNullText() {
        // given
        String pattern = "AAA";
        String text = null;
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        int[] actual = dSearch.findAll(text);

        // then
        assert false;
    }

    @Test
    public void should_ReturnEmptyArray_When_FindAllDidNotFindPatternInText() {
        // given
        String pattern = "AAA";
        String text = "ABBA";
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        int[] actual = dSearch.findAll(text);

        // then
        int[] expected = new int[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectVal_When_TryingToFindAllInEmptyText() {
        // given
        String pattern = "AAA";
        String text = "";
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        int[] actual = dSearch.findAll(text);

        // then
        int[] expected = new int[0];

        assertArrayEquals(expected, actual);
    }

    @Test
    public void should_FindAll_When_TextHasDisjointPatterns() {
        // given
        String pattern = "ABA";
        String text = "dddABAGTHJJHABA";
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        int[] actual = dSearch.findAll(text);

        // then
        int[] expected = {3, 12};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void should_FindAll_When_TextHasNotDisjointPatterns() {
        // given
        String pattern = "ABA";
        String text = "dddABABABABAYYY";
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        int[] actual = dSearch.findAll(text);

        // then
        int[] expected = {3, 5, 7, 9};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void should_FindFirst_When_PatternIsEqualToText() {
        // given
        String pattern = "OJCZE";
        String text = "OJCZE";
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        int actual = dSearch.findFirst(text);

        // then
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectVal_When_PatternIsLongerThanText() {
        // given
        String pattern = "OJCZE";
        String text = "OJ";
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        int actual = dSearch.findFirst(text);

        // then
        int expected = -1;

        assertEquals(expected, actual);
    }

    @Test
    public void should_FindAll_When_TextConsistOfPatternThatIsSingleCharacter() {
        // given
        String pattern = "a";
        String text = "aaaaaaaa";
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        int[] actual = dSearch.findAll(text);

        // then
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void should_FindAll_When_TextIsAlmostAPattern() {
        // given
        String pattern = "abc";
        String text = "abd";
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        int actual = dSearch.findFirst(text);

        // then
        int expected = -1;
        assertEquals(expected, actual);
    }
}