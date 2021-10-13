package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class QuickSortTest {
    private Sorting sorting;

    @Before
    public void setUp() {
        sorting = new QuickSort();
    }

    @Test
    public void should_SortArray_When_ArrayHasOneElem() {
        //given
        double [] nums = {10};
        
        //when
        sorting.sort(nums);

        //then
        double [] expecteds = {10};
        assertArrayEquals(expecteds, nums, 0);
    }

    @Test
    public void should_SortArray_When_ArrayHasTwoElems() {
        //given
        double [] nums = {5, 1};
        
        //when
        sorting.sort(nums);

        //then
        double [] expecteds = {1, 5};
        assertArrayEquals(expecteds, nums, 0);
    }

    @Test
    public void should_SortArray_When_ArrayHasEvenNumOfElems() {
        //given
        double [] nums = {1, -10, 2, 5, 1, 4};
        
        //when
        sorting.sort(nums);

        //then
        double [] expecteds = {-10, 1, 1, 2, 4, 5};
        assertArrayEquals(expecteds, nums, 0);
    }

    @Test
    public void should_SortArray_When_ArrayHasOddNumOfElems() {
        //given
        double [] nums = {1, -10, 2, 5, 1};
        
        //when
        sorting.sort(nums);

        //then
        double [] expecteds = {-10, 1, 1, 2, 5};
        assertArrayEquals(expecteds, nums, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_ArrayIsNull() {
        //given
        double[] nums = null;
        
        //when
        sorting.sort(nums);
    }

    @Test
    public void should_SortArray_When_ArrayHasWorstCaseData() {
        //given
        double [] nums = {-10, -4, 2, 5, 10, 112};
        
        //when
        sorting.sort(nums);

        //then
        double [] expecteds = {-10, -4, 2, 5, 10, 112};
        assertArrayEquals(expecteds, nums, 0);
    }
}
