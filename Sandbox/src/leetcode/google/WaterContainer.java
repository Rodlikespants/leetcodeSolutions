package leetcode.google;

public class WaterContainer {
    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length-1;
        boolean isDone = false;
        int largestArea = 0;
        while (!isDone) {
            boolean leftMoved = false;
            boolean rightMoved = false;

            largestArea = Math.max(largestArea, calculateArea(height, start, end));

            // move shorter side to be the next longest line towards the inside
            if (height[start] < height[end]) {
                // move left ptr towards right
                int newStart = findNextHighestRight(height, start, end);
                leftMoved = newStart > start;
                start = newStart;
                int newArea = calculateArea(height, start, end);
                largestArea = Math.max(largestArea, newArea);
            } else {//if (height[end] < height[start]){
                // move right ptr towards left
                int newEnd = findNextHighestLeft(height, start, end);
                rightMoved = newEnd < end;
                end = newEnd;
                int newArea = calculateArea(height, start, end);
                largestArea = Math.max(largestArea, newArea);
            }
            isDone = !leftMoved && !rightMoved;
        }
        return largestArea;
    }

    public static int calculateArea(int[] height, int start, int end) {
        int y = Math.min(height[start], height[end]);
        int x = end - start;

        return x * y;
    }

    public int findNextHighestRight(int[] arr, int start, int end) {
        int startVal = arr[start];
        for (int i = start; i < end; ++i) {
            if (arr[i] > startVal) {
                return i;
            }
        }
        return start;
    }

    public int findNextHighestLeft(int[] arr, int start, int end) {
        int startVal = arr[end];
        for (int i = end; i > start; --i) {
            if (arr[i] > startVal) {
                return i;
            }
        }
        return end;
    }
    /* solution
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
     */

    public static void main(String[] args) {
        //int[] arr = {1,2,4,3};
        //int[] arr = {1,3,2,5,25,24,5};
        int[] arr = {1,3,2,5,25,24,5};
        int result = new WaterContainer().maxArea(arr);
        System.out.println(result);
    }
}
