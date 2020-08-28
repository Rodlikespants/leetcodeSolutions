package interviews.design;

import java.util.Stack;

class MinStack {
    Stack<Integer> stack;
    Stack<Integer> mins;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        mins = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        insertMin(x);
    }

    public void pop() {
        stack.pop();
        mins.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return mins.peek();
    }

    private void insertMin(int x) {
        if (mins.isEmpty()) {
            mins.push(x);
        } else {
            int min = mins.peek();
            int toPush = x < min ? x : min;
            mins.push(toPush);
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
