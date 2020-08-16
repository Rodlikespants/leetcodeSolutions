package leetcode.ds;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MyCircularQueue {
    private List<Integer> list;
    private int pStart;
    private int pEnd;
    private int size;
    private int totalSize;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        list = Stream.generate(() -> Integer.valueOf(0)).limit(k).collect(Collectors.toList());
        pStart = 0;
        pEnd = 0;
        size = 0;
        totalSize = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (size < totalSize) {
            list.set(pEnd, value);
            pEnd = (pEnd + 1) % totalSize;
            size++;
            return true;
        } else {
            return false;
        }

    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (size > 0) {
            pStart = (pStart + 1) % totalSize;
            size--;
            return true;
        } else {
            return false;
        }
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return list.get(pStart);
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return list.get((totalSize + pEnd-1) % totalSize);
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size >= totalSize;
    }

    public static void main(String[] args) {
        /*
        MyCircularQueue queue = new MyCircularQueue(3);
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        int val = queue.Rear();
        boolean isFull = queue.isFull();
        queue.deQueue();
        queue.enQueue(4);
        val = queue.Rear();
         */

        MyCircularQueue queue = new MyCircularQueue(3);
        queue.enQueue(7);
        queue.deQueue();
        queue.Front();
        queue.deQueue();
        queue.Front();
        queue.Rear();
        queue.enQueue(0);
        queue.isFull();
        queue.deQueue();
        int val = queue.Rear();
        boolean isFull = queue.isFull();
        queue.deQueue();
        queue.enQueue(4);
        val = queue.Rear();
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */