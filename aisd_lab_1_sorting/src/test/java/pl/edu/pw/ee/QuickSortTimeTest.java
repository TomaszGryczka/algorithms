package pl.edu.pw.ee;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class QuickSortTimeTest {
    private long start;
    private long end;

    private Sorting sorting;
    private Random rand;

    private final int seed = 12;

    @Before
    public void setUp() {
        sorting = new QuickSort();

        rand = new Random(seed);
    }

    @Test
    public void measureExecutionTime_For_10Elems() {
        //given
        int numOfElems = 10000000;
        double[] nums = new double[numOfElems];

        for(int i = 0; i < numOfElems; i++) {
            nums[i] = rand.nextDouble();
        }
        
        //when
        start = System.nanoTime();
        sorting.sort(nums);
        end = System.nanoTime();

        System.out.println("Elapsed time in seconds: " + (end - start)/1000000000);

    }
}