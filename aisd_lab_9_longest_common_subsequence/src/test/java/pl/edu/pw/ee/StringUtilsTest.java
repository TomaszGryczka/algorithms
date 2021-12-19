package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class StringUtilsTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToCreateEmptyStringWithSizeLowerThanZero() {
        // given
        int size = -1;

        // when
        StringUtils.emptyString(size);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToFormatLeftWithlengthLowerThanOne() {
        // given
        int length = -1;
        String text = "text";

        // when
        StringUtils.leftPad(text, length);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToRightFormatWithlengthLowerThanOne() {
        // given
        int length = -1;
        String text = "text";

        // when
        StringUtils.rightPad(text, length);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToLeftFormatNullString() {
        // given
        int length = 10;
        String text = null;

        // when
        StringUtils.leftPad(text, length);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToRightFormatNullString() {
        // given
        int length = 10;
        String text = null;

        // when
        StringUtils.rightPad(text, length);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyFormatLeftAndRightString() {
        // given
        String text = "text";
        int length = 10;

        // when
        String actualLeft = StringUtils.leftPad(text, length);
        String actualRight = StringUtils.rightPad(text, length);

        // then
        String expectedLeft = "      text";
        String expectedRight = "text      ";

        assertEquals(expectedLeft, actualLeft);
        assertEquals(expectedRight, actualRight);
    }

    @Test
    public void should_ReturnCorrectString_When_GivenSpecialCharacter() {
        // given
        char character = '\n';

        // when
        String actual = StringUtils.getSpecialCharacter(character);

        // then
        String expected = "\\n";

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectString_When_GivenNormalCharacter() {
        // given
        char character = 'n';

        // when
        String actual = StringUtils.getSpecialCharacter(character);

        // then
        String expected = "n";

        assertEquals(expected, actual);
    }
}