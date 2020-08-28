package leetcode.google.sorting_searching.merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return intervals;
        }

        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);

        // process first interval
        //int startInterval = intervals[0];
        int start = intervals[0][0];
        int end = intervals[0][1];

        //PriorityQueue<Integer> pq = new PriorityQueue<Integer>(intervals.length, (Integer a, Integer b) -> a - b);
        List<int[]> newIntervals = new ArrayList<>();
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] <= end && intervals[i][1] > end) {
                end = intervals[i][1]; // extending the end of the interval
            } else if (intervals[i][0] > end) {
                int[] interval = {start, end};
                newIntervals.add(interval);
                start = intervals[i][0];
                end = intervals[i][1];
            }
            // otherwise the interval gets swallowed by the previous one
        }

        // one more add for the last interval
        int[] interval = {start, end};
        newIntervals.add(interval);

        int[][] ret = new int[newIntervals.size()][2];
        for (int i = 0; i < newIntervals.size(); ++i) {
            int[] newInterval = newIntervals.get(i);
            ret[i] = newInterval;
        }
        return ret;
    }
}
