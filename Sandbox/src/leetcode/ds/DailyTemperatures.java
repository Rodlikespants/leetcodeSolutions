package leetcode.ds;

import java.util.Stack;

public class DailyTemperatures {
    class Pair {
        int value;
        int index;
        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    /**
     * return a list of temperature increase occurrences
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        Stack<Pair> stack = new Stack<>();
        int[] resultArr = new int[T.length];
        for (int i = 0; i < resultArr.length; ++i) {
            resultArr[i] = 0;
        }

        for (int i = 0; i < T.length; ++i) {
            if (!stack.isEmpty()) {
                Pair top = stack.peek();
                while (top != null && T[i] > top.value) {
                    resultArr[top.index] = i - top.index;
                    stack.pop();
                    top = !stack.isEmpty() ? stack.peek() : null;
                }
            }
            stack.push(new Pair(T[i], i));
        }
        return resultArr;
    }
}
