package leetcode.google.arrays_strings.threesum;

import java.util.*;
import java.util.stream.Collectors;

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
        Map<Integer, Set<Integer>> twosum = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < nums.length; ++j) {
                if (i != j) { // prevent double counting
                    int key = nums[i] + nums[j];
                    Set<Integer> value = new HashSet<>();
                    value.add(i);
                    value.add(j);
                    twosum.put(key, value);
                }
            }
        }

        System.out.println(twosum.size());

        for (int key : twosum.keySet()) {
            int thirdNum = 0 - key;
            if (numIndexes.containsKey(thirdNum)) {
                List<Integer> triplet = new ArrayList<>();
                // doublecheck we aren't double counting
                // index of the matched third number
                Set<Integer> indexes = numIndexes.getOrDefault(thirdNum, new HashSet<>());
                // make sure this isn't already one of the two we added
                Set<Integer> twoSumIndexes = twosum.get(key);
                // i.e. there is an element in indexes that isn't in twosum value
                if (indexes.stream().anyMatch(i -> !twoSumIndexes.contains(i))) {
                    for (int twoSumIndex : twoSumIndexes) {
                        triplet.add(nums[twoSumIndex]);
                    }
                    triplet.add(thirdNum);
                    triplet = triplet.stream().sorted().collect(Collectors.toList()); // needs to be sorted to prevent dups

                    triplets.add(triplet);
                    System.out.println("added triplet " + triplet);
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
