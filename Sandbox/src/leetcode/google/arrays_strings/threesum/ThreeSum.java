package leetcode.google.arrays_strings.threesum;

import java.util.*;
import java.util.stream.Collectors;

/**
 * times out on test case 311/313
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Set<Integer>> numIndexes = new HashMap<>();
        //Set<Integer> numSet = new HashSet();

        //pre-process index lookup
        for (int i = 0; i < nums.length; ++i) {
            Set<Integer> indexes = numIndexes.getOrDefault(nums[i], new HashSet<>());
            indexes.add(i);
            numIndexes.put(nums[i], indexes);
            //numSet.add(nums[i]);
        }

        // use sets for triplet solutions
        Set<List<Integer>> triplets = new HashSet<>();

        // the remaining sum mapped to indexes
        // Map<Integer, Set<Integer>> twosum = new HashMap<>();

        //memoization
        //boolean[][] memo = new boolean[nums.length][nums.length];
        Set<Set<Integer>> alreadyTwoSummed = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i+1; j < nums.length; ++j) {
                // memoization check
                Set<Integer> twoSumCheck = new HashSet<>();
                twoSumCheck.add(nums[i]);
                twoSumCheck.add(nums[j]);

                if (!alreadyTwoSummed.contains(twoSumCheck)) {
                    int key = nums[i] + nums[j];
                    int thirdNum = 0 - key;
                    List<Integer> triplet = new ArrayList<>();
                    // doublecheck we aren't double counting
                    // index of the matched third number
                    Set<Integer> indexes = numIndexes.getOrDefault(thirdNum, new HashSet<>());
                    // make sure this isn't already one of the two we added
                    final int firstIndex = i;
                    final int secondIndex = j;
                    if (indexes.stream().anyMatch(thirdIndex -> thirdIndex != firstIndex && thirdIndex != secondIndex)) {
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        triplet.add(thirdNum);
                        triplet = triplet.stream().sorted().collect(Collectors.toList()); // needs to be sorted to prevent dups

                        triplets.add(triplet);
                        //System.out.println("added triplet " + triplet);
                    }
                    alreadyTwoSummed.add(twoSumCheck);
                }

            }
        }
        List<List<Integer>> result = triplets.stream().collect(Collectors.toList());

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result1 = new ThreeSum().threeSum(nums1);
        System.out.println(result1);
    }
}
