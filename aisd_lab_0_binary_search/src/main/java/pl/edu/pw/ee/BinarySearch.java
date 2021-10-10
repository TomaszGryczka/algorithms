package pl.edu.pw.ee;

public class BinarySearch implements Searcher {


    public int search(double[] nums, double toFind) {
        int result = -1;
        
        if(nums == null) {
            return result;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        
        while(left <= right) {
            mid = left + (right - left) / 2;

            if(nums[mid] == toFind) {
                result = mid;
                break;
            } else if(nums[mid] > toFind) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}
