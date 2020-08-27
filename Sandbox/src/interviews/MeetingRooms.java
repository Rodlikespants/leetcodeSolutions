package interviews;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Programs Creek Solution, also see leetcode section
 */
public class MeetingRooms {
    public static int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing((int[] itv) -> itv[0]));

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int count = 0;
        for (int[] itv : intervals) {
            if (heap.isEmpty()) {
                count++;
                heap.offer(itv[1]);
            } else {
                if (itv[0] >= heap.peek()) {
                    heap.poll();
                } else {
                    count++;
                }

                heap.offer(itv[1]);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // [2,15], [36,45], [9.29], [16,23], [4,9]
        int[][] intervals = {{2,15}, {36,45}, {16,23}, {4,9}, {10, 17}};
        int result = minMeetingRooms(intervals);
        System.out.println(result);
    }
}
