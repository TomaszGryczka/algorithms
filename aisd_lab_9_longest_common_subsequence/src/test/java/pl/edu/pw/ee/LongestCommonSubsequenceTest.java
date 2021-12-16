package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class LongestCommonSubsequenceTest {

    private final PrintStream oldOut = System.out;

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FirstStrIsNull() {
        // given
        String firstStr = null;
        String secondStr = "i love you too";

        // when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_SecondStrIsNull() {
        // given
        String firstStr = "i hate you";
        String secondStr = null;

        // when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FirstAndSecondStrsAreNull() {
        // given
        String firstStr = null;
        String secondStr = null;

        // when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FirstStrHas0Length() {
        // given
        String firstStr = "";
        String secondStr = "i do not know you";

        // when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_SecondStrHas0Length() {
        // given
        String firstStr = "";
        String secondStr = "i do not know you";

        // when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FirstAndSecondStrHave0Length() {
        // given
        String firstStr = "";
        String secondStr = "";

        // when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);

        // then
        assert false;
    }

    @Test
    public void should_findLCS_When_StringsHaveOneChar() {
        // given
        String firstStr = "a";
        String secondStr = "a";

        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);

        // when

        String actual = lcs.findLCS();

        // then
        String expected = "a";
        assertEquals(expected, actual);
    }

    @Test
    public void should_displayMatrix_When_StringsHaveOneChar() {
        // given
        String firstStr = "a";
        String secondStr = "a";

        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        lcs.findLCS();
        lcs.display();

        String actual = output.toString();

        System.setOut(oldOut);

        // then
        String expected = "0 0\r\n"
                + " \\ \r\n"
                + "0 1\r\n";
        assertEquals(expected, actual);
    }

    @Test
    public void should_findLCS_When_FirstStringHasEvenNumOfCharsAndSecondHasOddNum() {
        // given
        String firstStr = "ab";
        String secondStr = "a";

        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);

        // when

        String actual = lcs.findLCS();

        // then
        String expected = "a";
        assertEquals(expected, actual);
    }

    @Test
    public void should_displayMatrix_When_StringsDoNotHaveCommonSubsequence() {
        // given
        String firstStr = "b";
        String secondStr = "a";

        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        lcs.findLCS();
        lcs.display();

        String actual = output.toString();

        System.setOut(oldOut);

        // then
        String expected = "0 0\r\n"
                + " ^ \r\n"
                + "0 0\r\n";
        assertEquals(expected, actual);
    }
}
