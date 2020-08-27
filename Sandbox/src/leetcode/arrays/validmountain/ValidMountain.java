package leetcode.arrays.validmountain;

public class ValidMountain {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }
        int lastVal = A[0];
        Boolean ascending = null;
        boolean firstAscentDone = false;
        for (int i = 1; i < A.length; ++i) {
            if (A[i] > lastVal) {
                if (ascending == null) {
                    ascending = true;
                } else if (firstAscentDone) {
                    return false;
                }
            } else if (A[i] < lastVal) {
                if (ascending == null) {
                    return false;
                } else if (ascending) {
                    ascending = false;
                    firstAscentDone = true;
                }
            } else {
                return false;
            }
            lastVal = A[i];
        }
        return ascending != null && !ascending;
    }
}
