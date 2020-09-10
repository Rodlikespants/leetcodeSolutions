package leetcode.arrays.minimum_size_subarray;

public class MinimumSizeSubarray {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // length 1 case is implicitly handled

        int start = 0;
        int end = 0;
        int len = nums.length;
        int sum = nums[0];
        int minLen = Integer.MAX_VALUE;

        // outer loop
        while (end < len-1) {
            // continue widening the window until we match the sum
            while (sum < s && end < len-1) {
                end++;
                sum += nums[end];
            }
            // optimize sum for minimum length
            while (sum >= s && start <= end) {
                int currLen = end - start + 1;
                if (currLen < minLen) {
                    minLen = currLen;
                    if (minLen == 1) {
                        return minLen; // not going to get better than this
                    }
                }
                sum -= nums[start];
                start++;
            }

        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
