package org.morewax.leetcode;

import java.util.PriorityQueue;

/**
 * Problem
 * Super Ugly Number ()
 * Write a program to find the nth super ugly number.

 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
 * Note:
 *  (1) 1 is a super ugly number for any given primes.
 *  (2) The given numbers in primes are in ascending order.
 *  (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.

 * Created by byuan on 12/22/15.
 */
public class SuperUglyNumber {
    private static class Item implements Comparable<Item> {
        public int index;
        public int val;
        public int prime;

        public Item(int index, int val, int prime) {
            this.index = index;
            this.val = val;
            this.prime = prime;
        }

        @Override
        public int compareTo(Item item) {
            return this.val - item.val;
        }
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;

        PriorityQueue<Item> heap = new PriorityQueue<>();
        for (int i = 0; i < primes.length; ++i) {
            heap.add(new Item(0, primes[i], primes[i]));
        }

        for (int i = 1; i < n; ++i) {
            Item item = heap.peek();
            uglyNumbers[i] = item.val;

            do {
                item = heap.poll();
                item.val = uglyNumbers[++item.index]*item.prime;
                heap.add(item);
            } while (!heap.isEmpty() && heap.peek().val == uglyNumbers[i]);
        }

        return uglyNumbers[n-1];
    }

    public static void main(String[] args) {
        final SuperUglyNumber s = new SuperUglyNumber();

        int[] primes1 = new int[]{2};
        System.out.println(s.nthSuperUglyNumber(2, primes1));
    }
}
