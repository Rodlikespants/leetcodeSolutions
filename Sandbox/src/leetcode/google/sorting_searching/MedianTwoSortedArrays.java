package leetcode.google.sorting_searching;

/**
 * explanation for best solution here: https://medium.com/@hazemu/finding-the-median-of-2-sorted-arrays-in-logarithmic-time-1d3f2ecbeb46
 */
public class MedianTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0.0;
        }
        else if (nums1.length == 0 || nums2.length == 0) {
            if (nums1.length == 0) {
                return findMedian(nums2);
            } else {
                return findMedian(nums1);
            }
        }

        int[] merged = new int[nums1.length + nums2.length];
        int i = 0;
        int ptr1 = 0;
        int ptr2 = 0;
        while (ptr1 < nums1.length && ptr2 < nums2.length) {
            if (nums1[ptr1] < nums2[ptr2]) {
                merged[i] = nums1[ptr1];
                ++ptr1;
            } else {
                merged[i] = nums2[ptr2];
                ++ptr2;
            }
            ++i;
        }

        while (ptr1 < nums1.length) {
            merged[i] = nums1[ptr1];
            ++ptr1;
            ++i;
        }

        while (ptr2 < nums2.length) {
            merged[i] = nums2[ptr2];
            ++ptr2;
            ++i;
        }

        return findMedian(merged);
    }

    private double findMedian(int[] merged) {
        int len = merged.length;
        int halfIndex = (len-1)/2;
        if (len % 2 == 0) {
            return (merged[halfIndex] + merged[halfIndex+1])/2.0;
        } else {
            return merged[halfIndex];
        }
    }
}