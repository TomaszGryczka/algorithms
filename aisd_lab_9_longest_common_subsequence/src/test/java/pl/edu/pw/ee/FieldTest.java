package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class FieldTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FieldValueIsLowerThan0() {
        // given
        int fieldValue = -1;

        // when
        Field field = new Field(fieldValue, Arrow.UPPER);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_ArrowIsNull() {
        // given
        int fieldValue = 10;

        // when
        Field field = new Field(fieldValue, null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_ComparingToNullvalue() {
        // given
        int fieldValue = 10;

        Field field = new Field(fieldValue, Arrow.UPPER);
        Field nullField = null;

        // when
        field.compareTo(nullField);

        // then
        assert false;
    }

    @Test
    public void should_ReturnFalse_When_FieldIsNull() {
        // given
        int fieldValue = 10;

        Field field = new Field(fieldValue, Arrow.UPPER);
        Field nullField = null;

        // when
        boolean actual = field.equals(nullField);

        // then
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnTrue_When_FieldHasIdenticalReference() {
        // given
        int fieldValue = 10;

        Field field = new Field(fieldValue, Arrow.UPPER);
        Field otherField = field;

        // when
        boolean actual = field.equals(otherField);

        // then
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnFalse_When_TryingToInvokeEqualsToDifferentType() {
        // given
        int fieldValue = 10;

        Field field = new Field(fieldValue, Arrow.UPPER);
        Integer otherObj = 10;

        // when
        boolean actual = field.equals(otherObj);

        // then
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnTrue_When_FieldHasIdenticalFieldVal() {
        // given
        int firstFieldVal = 10;
        int secondFieldVal = 10;

        Field firstField = new Field(firstFieldVal, Arrow.UPPER);
        Field secondField = new Field(secondFieldVal, Arrow.UPPER);

        // when
        boolean actual = firstField.equals(secondField);

        // then
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnTrue_When_FieldHasDifferentFieldVal() {
        // given
        int firstFieldVal = 10;
        int secondFieldVal = 11;

        Field firstField = new Field(firstFieldVal, Arrow.UPPER);
        Field secondField = new Field(secondFieldVal, Arrow.UPPER);

        // when
        boolean actual = firstField.equals(secondField);

        // then
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyCompareFields_When_FirstIsEqualToSecond() {
        // given
        int firstFieldVal = 10;
        int secondFieldVal = 10;

        Field firstField = new Field(firstFieldVal, Arrow.UPPER);
        Field secondField = new Field(secondFieldVal, Arrow.UPPER);

        // when
        int actual = firstField.compareTo(secondField);

        // then
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyCompareFields_When_FirstIsLowerThanSecond() {
        // given
        int firstFieldVal = 0;
        int secondFieldVal = 10;

        Field firstField = new Field(firstFieldVal, Arrow.UPPER);
        Field secondField = new Field(secondFieldVal, Arrow.UPPER);

        // when
        int actual = firstField.compareTo(secondField);

        // then
        int expected = -1;

        assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyCompareFields_When_FirstIsBiggerThanSecond() {
        // given
        int firstFieldVal = 12;
        int secondFieldVal = 10;

        Field firstField = new Field(firstFieldVal, Arrow.UPPER);
        Field secondField = new Field(secondFieldVal, Arrow.UPPER);

        // when
        int actual = firstField.compareTo(secondField);

        // then
        int expected = 1;

        assertEquals(expected, actual);
    }
}
