package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSortTest {
    private Sorting sorting;

    @Before
    public void setUp() {
        sorting = new SelectionSort();
    }

    @Test
    public void should_ReturnSortedArray_When_ArrayHasEvenNumsOfElems() {
        //given
        double [] nums = {1, 4, 2, 1};
        
        //when
        sorting.sort(nums);

        //then
        double [] expecteds = {1, 1, 2, 4};
        assertArrayEquals(expecteds, nums, 0);
    }
}
