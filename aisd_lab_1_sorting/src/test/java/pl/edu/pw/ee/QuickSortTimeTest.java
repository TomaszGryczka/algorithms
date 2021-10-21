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
        generateBestCase(nums, i);

        start = System.nanoTime();
        sorting.sort(nums);
        end = System.nanoTime();

        System.out.println(i + ", " + (end - start) / 1000);
    }

    private void generateBestCase(double[] nums, int numOfElems) {
        fillArray(nums);

        generate(nums, 0, numOfElems);
    }

    private void generate(double[] arr, int begin, int end) {
        int count = end - begin;
        if (count < 3)
            return;

        int middle = begin + (count - 1) / 2;

        generate(arr, begin, middle);

        swap(arr, begin, middle);

        generate(arr, ++middle, end);
    }

    private void swap(double[] arr, int i, int j) {
        double t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private void fillArray(double[] arr) {
        for (int i = 0; i != arr.length; ++i)
            arr[i] = i + 1;
    }

    @Test
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
        for (int i = 0; i < numOfElems; i++) {
            nums[i] = i;
        }
    }

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