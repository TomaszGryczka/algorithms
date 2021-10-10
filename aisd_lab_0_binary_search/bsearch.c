#include <stdio.h>

int bsearch(int *nums, int n, int toFind) {
	if(nums == NULL) {
		return -1;
	}
	int l = 0, p = n - 1, mid = 0;
	while(l <= p) {
		mid = (l+p)/2;
		if(nums[mid] > toFind) {
			p = mid - 1;			
		} else if(nums[mid] < toFind) {
			l = mid + 1;
		}
		else {
			return mid;
		}

	}
	return -1;
}

int main(int arc, char **argv) {
	int nums[4] = {1, 2, 3, 4};

	int * nums2 = NULL;

	printf("%d\n", bsearch(nums, 4, 1));
	printf("%d\n", bsearch(nums, 4, 3));
	printf("%d\n", bsearch(nums, 4, 4));
	printf("%d\n", bsearch(nums2, 4, 10));
	printf("%d\n", bsearch(nums, 4, 100));

}
