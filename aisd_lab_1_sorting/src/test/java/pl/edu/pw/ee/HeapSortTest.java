package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;

import java.util.Random;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class HeapSortTest {
    private Sorting sorting;
    private Random rand1, rand2;
    
    @Before
    public void setUp() {
        sorting = new QuickSort();

        int seed = 12;
        rand1 = new Random(seed);
        rand2 = new Random(seed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_ArrayIsNull() {
        // given
        double[] nums = null;

        // when
        sorting.sort(nums);
    }

    @Test
    public void should_SortArray_When_ArrayHasOneElem() {
        // given
        double[] nums = { 10 };

        // when
        sorting.sort(nums);

        // then
        double[] expecteds = { 10 };
        assertArrayEquals(expecteds, nums, 0);
    }

    @Test
    public void should_SortArray_When_ArrayHasTwoElems() {
        // given
        double[] nums = { 5, 1 };

        // when
        sorting.sort(nums);

        // then
        double[] expecteds = { 1, 5 };
        assertArrayEquals(expecteds, nums, 0);
    }
}
