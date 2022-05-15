package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class MatrixTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FirstSizeIsLowerThanOne() {
        // given
        int firstStrSize = -1;
        int secondStrSize = 2;

        // when
        Matrix matrix = new Matrix(firstStrSize, secondStrSize);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_SecondSizeIsLowerThanOne() {
        // given
        int firstStrSize = 1;
        int secondStrSize = -5;

        // when
        Matrix matrix = new Matrix(firstStrSize, secondStrSize);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_BothSizesAreLowerThanOne() {
        // given
        int firstStrSize = -1;
        int secondStrSize = -5;

        // when
        Matrix matrix = new Matrix(firstStrSize, secondStrSize);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToSetNullField() {
        // given
        int firstStrSize = 10;
        int secondStrSize = 40;

        int i = 4;
        int j = 3;

        Field field = null;

        Matrix matrix = new Matrix(firstStrSize, secondStrSize);

        // when
        matrix.setField(i, j, field);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToSetIndexThatDoesNotExist() {
        // given
        int firstStrSize = 2;
        int secondStrSize = 2;

        int i = 4;
        int j = 3;

        Field field = new Field(10, Arrow.UPPER);

        Matrix matrix = new Matrix(firstStrSize, secondStrSize);

        // when
        matrix.setField(i, j, field);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToGetIndexThatDoesNotExist() {
        // given
        int firstStrSize = 2;
        int secondStrSize = 2;

        int i = 4;
        int j = 3;

        Matrix matrix = new Matrix(firstStrSize, secondStrSize);

        // when
        matrix.getField(i, j);

        // then
        assert false;
    }

    @Test
    public void should_SetAndGetField() {
        // given
        int firstStrSize = 20;
        int secondStrSize = 20;

        int i = 4;
        int j = 3;

        Field field = new Field(10, Arrow.UPPER);

        Matrix matrix = new Matrix(firstStrSize, secondStrSize);

        // when
        matrix.setField(i, j, field);

        Field actual = matrix.getField(i, j);

        // then
        Field expected = field;

        assertEquals(expected, actual);
        ;
    }
}