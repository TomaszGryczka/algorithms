package pl.edu.pw.ee;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class HeapSortTimeTest {
    private long start;
    private long end;

    private Sorting sorting;
    private Random rand;

    private final int seed = 10;

    @Before
    public void setUp() {
        sorting = new HeapSort();

        rand = new Random(seed);
    }

    @Test
    public void measureExecutionTime_When_ArrayHasBestCaseData() {

        for (int i = 10; i < 101; i += 10) {
            measureTimeForBestCaseData(i);
        }

        for (int i = 100; i < 501; i += 100) {
            measureTimeForBestCaseData(i);
        }

        for (int i = 1000; i < 50001; i += 1000) {
            measureTimeForBestCaseData(i);
        }

    }

    private void measureTimeForBestCaseData(int i) {
        double[] nums = new double[i];
        generateBestCaseData(nums, i);

        start = System.nanoTime();
        sorting.sort(nums);
        end = System.nanoTime();

        System.out.println(i + ", " + (end - start) / 1000);
    }

    private void generateBestCaseData(double[] nums, int numOfElems) {
        Heap<Integer> heap = new Heap<>();

        for (int i = 0; i < numOfElems; i++) {
            heap.put(i);
        }

        List<Integer> items = heap.getItems();

        for (int i = 0; i < numOfElems; i++) {
            nums[i] = items.get(i);
        }
    }

    /*@Test
    public void measureExecutionTime_When_ArrayHasWorstCaseData() {
        for (int i = 10; i < 101; i += 10) {
            measureTimeForWorstCaseData(i);
        }

        for (int i = 100; i < 501; i += 100) {
            measureTimeForWorstCaseData(i);
        }

        for (int i = 1000; i < 50001; i += 1000) {
            measureTimeForWorstCaseData(i);
        }
    }

    private void measureTimeForWorstCaseData(int i) {
        double[] nums = new double[i];
        generateWorstCase(nums, i);

        start = System.nanoTime();
        sorting.sort(nums);
        end = System.nanoTime();

        System.out.println(i + ", " + (end - start) / 1000);
    }

    private void generateWorstCase(double[] nums, int numOfElems) {
        double[] arr = new double[numOfElems + 1];
        int length = 1;
        int j;
        arr[1] = 1;

        for (int i = 2; i <= numOfElems; i++) {
            j = length;
            while (j > 1) {
                arr[j] = arr[j / 2];
                j /= 2;
            }
            arr[1] = i;
            arr[++length] = 1;
        }

        for(int i = 1; i < numOfElems; i++) {
            nums[i] = arr[i + 1];
        }
    }*/

    @Test
    public void measureExecutionTime_When_ArrayHasRandomData() {
        for (int i = 10; i < 101; i += 10) {
            measureTimeForRandomData(i);
        }

        for (int i = 100; i < 501; i += 100) {
            measureTimeForRandomData(i);
        }

        for (int i = 1000; i < 50001; i += 1000) {
            measureTimeForRandomData(i);
        }
    }

    private void measureTimeForRandomData(int i) {
        double[] nums = new double[i];
        generateRandomData(nums, i);

        start = System.nanoTime();
        sorting.sort(nums);
        end = System.nanoTime();

        System.out.println(i + ", " + (end - start) / 1000);
    }

    private void generateRandomData(double[] nums, int numOfElems) {

        for (int i = 0; i < numOfElems; i++) {
            nums[i] = rand.nextDouble();
        }
    }
}
