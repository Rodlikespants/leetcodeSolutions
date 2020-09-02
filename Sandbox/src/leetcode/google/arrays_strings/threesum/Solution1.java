package leetcode.google.arrays_strings.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution1 {
    public void twoSum(int[] numbers, int i, List<List<Integer>> res) {
        int lo = i+1, hi = numbers.length - 1;
        while (lo < hi) {
            int curSum = numbers[lo] + numbers[hi] + numbers[i];
            if (curSum < 0) {
                lo++;
            } else if (curSum > 0) {
                hi--;
            } else {
                res.add(Arrays.asList(numbers[i], numbers[lo++], numbers[hi--]));
                while (lo < hi && numbers[lo] == numbers[lo-1]) {
                    ++lo;
                }
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[0] <= 0; ++i) {
            if (i == 0 || nums[i-1] != nums[i]) {
                twoSum(nums, i, res);
            }
        }
        return res;
    }

}
