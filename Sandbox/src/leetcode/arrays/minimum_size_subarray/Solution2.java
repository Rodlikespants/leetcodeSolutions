package leetcode.arrays.minimum_size_subarray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * solution using binary search
 * NOT WORKING -- trying to convert from C++
 */
public class Solution2 {
    int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int ans = Integer.MAX_VALUE;
        List<Integer> sums = new ArrayList<>(n+1); //size = n+1 for easier calculations
        //sums[0]=0 : Meaning that it is the sum of first 0 elements
        //sums[1]=A[0] : Sum of first 1 elements
        //ans so on...
        for (int i = 1; i <= n; i++)
            sums.add(i, sums.get(i - 1) + nums[i - 1]);
        for (int i = 1; i <= n; i++) {
            int to_find = s + sums.get(i - 1);
            int bound = Collections.binarySearch(sums, to_find);
            if (bound > 0) {
                ans = Math.min(ans, bound - (sums.get(0) + i - 1));
            }
        }
        return (ans != Integer.MAX_VALUE) ? ans : 0;
    }
}
