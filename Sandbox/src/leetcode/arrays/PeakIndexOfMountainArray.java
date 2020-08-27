package leetcode.arrays;

public class PeakIndexOfMountainArray {
    public int peakIndexInMountainArray(int[] A) {
        int i = 0;
        while (i < A.length-1 && A[i] < A[i+1]) {
            ++i;
        }
        return i;
    }
}
