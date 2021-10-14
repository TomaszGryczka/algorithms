package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSortTest {
    private Sorting sorting;
    private Random rand1, rand2;

    @Before
    public void setUp() {
        sorting = new SelectionSort();

        int seed = 12;

        rand1 = new Random(seed);
        rand2 = new Random(seed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_ArrayIsNull() {
        //given
        double[] nums = null;
        
        //when
        sorting.sort(nums);
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

    @Test
    public void should_SortArray_When_ArrayHasBestCaseData() {
        //given
        double [] nums = {1, 3, 4, 18, 21};
        
        //when
        sorting.sort(nums);

        //then
        double [] expecteds = {1, 3, 4, 18, 21};
        assertArrayEquals(expecteds, nums, 0);
    }

    @Test
    public void should_SortArray_When_ArrayHasWorstCaseData() {
        //given
        double [] nums = {21, 18, 4, 3, 1};
        
        //when
        sorting.sort(nums);

        //then
        double [] expecteds = {1, 3, 4, 18, 21};
        assertArrayEquals(expecteds, nums, 0);
    }

    @Test
    public void should_SortArray_When_ArrayIsUnsorted() {
        //given
        double [] nums = {-2, -5, 12, 1, 1, 92, -3, 4, 1};
        
        //when
        sorting.sort(nums);

        //then
        double [] expecteds = {-5, -3, -2, 1, 1, 1, 4, 12, 92};
        assertArrayEquals(expecteds, nums, 0);
    }

    @Test
    public void should_SortArray_WhenNumsAreRandomlyGenerated() {
        //given
        double[] nums = new double[100000];
        
        for(int i = 0; i < 100000; i++) {
            nums[i] = 10000 * rand1.nextDouble();
        }

        //when
        sorting.sort(nums);


        //then
        double[] result = new double[100000];
        for(int i = 0; i < 100000; i++) {
            result[i] = 10000 * rand2.nextDouble();
        }
        Arrays.sort(result);

        assertArrayEquals(result, nums, 0);
        
    }
}
