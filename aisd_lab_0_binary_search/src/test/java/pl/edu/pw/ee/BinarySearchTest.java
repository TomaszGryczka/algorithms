package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTest {
    
    private Searcher searcher;

    @Before
    public void setUp() {
        searcher = new BinarySearch();
    }

    @Test
    public void should_ReturnNegativeValue_When_ArrayIsNull() {
        //given
        double[] nums = null;
        double toFind = 0;

        //when
        int result = searcher.search(nums, toFind);

        //then
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnNegativeValue_When_ArrayIsEmpty() {
        //given
        double[] nums = {};
        double toFind = 0;

        //when
        int result = searcher.search(nums, toFind);

        //then
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnCorrectIndex_When_ArrayHasOneElem() {
        //given
        double[] nums = {1};
        double toFind = 1;

        //when
        int result = searcher.search(nums, toFind);

        //then
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnNegativeValue_When_ElemNotExistsInOneElemArray() {
        //given
        double[] nums = {3};
        double toFind = 1;

        //when
        int result = searcher.search(nums, toFind);

        //then
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnCorrectIndex_When_ArrayHasTwoElems() {
        //given
        double[] nums = {1, 4};
        double toFind = 4;

        //when
        int result = searcher.search(nums, toFind);

        //then
        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnCorrectIndex_When_ArrayHasOddNumOfElems() {
        //given
        double[] nums = {1, 4, 6, 7, 10};
        double toFind = 4;

        //when
        int result = searcher.search(nums, toFind);

        //then
        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnCorrectIndex_When_ArrayHasEvenNumOfElems() {
        //given
        double[] nums = {1, 4, 6, 7};
        double toFind = 7;

        //when
        int result = searcher.search(nums, toFind);

        //then
        int expected = 3;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnCorrectIndex_When_SearchingMinNumFromArray() {
        //given
        double[] nums = {-10, 4, 5, 7};
        double toFind = -10;

        //when
        int result = searcher.search(nums, toFind);

        //then
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnCorrectIndex_When_SearchingMaxNumFromArray() {
        //given
        double[] nums = {-10, 4, 5, 7};
        double toFind = 7;

        //when
        int result = searcher.search(nums, toFind);

        //then
        int expected = 3;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnNegativeValue_When_SearchingSmallerThanMinNumFromArray() {
        //given
        double[] nums = {-10, 4, 5, 7};
        double toFind = -12;

        //when
        int result = searcher.search(nums, toFind);

        //then
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnNegativeValue_When_SearchingBiggerThanMaxNumFromArray() {
        //given
        double[] nums = {-10, 4, 5, 7};
        double toFind = 20;

        //when
        int result = searcher.search(nums, toFind);

        //then
        int expected = -1;
        assertEquals(expected, result);
    }
}
