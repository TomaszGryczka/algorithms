package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting {
    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        Heap<Double> heap = new Heap<>();

        int n = nums.length;

        for (int i = 0; i < n; i++) {
            heap.put(nums[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            nums[i] = heap.pop();
        }
    }
}
