package org.morewax.meetqun;

/**
 * Created by Bing on 12/5/2015.
 */
public class CircularQueue {
    private static int DEFAULT_CAPACITY = 100;
    private int capacity;
    private int counter;
    private int[] data;
    private int start;
    private int end;

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }

        this.capacity = capacity;
        this.data = new int[capacity];
        this.counter = 0;
        this.start = 0;
        this.end = 0;
    }

    public boolean offer(Integer x) {
        if (!isFull()) {
            data[end++] = x;
            end = end%capacity;
            ++counter;
            return true;
        } else {
            return false;
        }
    }

    public Integer peek() {
        if (!isEmpty()) {
            return this.data[start];
        } else {
            return null;
        }
    }

    public Integer poll() {
        if (!isEmpty()) {
            Integer ret = data[start++];
            start = start%capacity;
            --counter;
            return ret;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return (counter == 0);
    }

    public int size() {
        return this.counter;
    }

    private boolean isFull() {
        return (counter == capacity);
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(7);

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);

        System.out.println(queue.peek());
        queue.poll();
        queue.poll();
        queue.offer(8);
        queue.offer(9);

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

    }
}
