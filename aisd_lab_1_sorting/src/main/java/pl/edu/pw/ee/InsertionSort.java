package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        double tmp = 0;

        int i = 1;
        int j = 0;

        while (i < nums.length) {
            tmp = nums[i];
            j = i - 1;

            while (j >= 0 && nums[j] > tmp) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = tmp;
            i++;
        }
    }
}
