package org.morewax.leetcode;

/**
 * Problem
 * Ugly Number II (https://leetcode.com/problems/ugly-number-ii/)
 * Write a program to find the n-th ugly number.

 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

 Note that 1 is typically treated as an ugly number.

 Hint:

 The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
 An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
 The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
 Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
 * Created by byuan on 12/22/15.
 */
public class UglyNumber2 {
    public int nthUglyNumber(int n) {
        int[] uglyNumbers = new int[n];

        uglyNumbers[0] = 1;

        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        for (int i = 1; i < n; ++i) {
            int m2 = uglyNumbers[index2]*2;
            int m3 = uglyNumbers[index3]*3;
            int m5 = uglyNumbers[index5]*5;

            int min = Math.min(m2, Math.min(m3, m5));
            if (min == m2) ++index2;
            if (min == m3) ++index3;
            if (min == m5) ++index5;

            uglyNumbers[i] = min;
            System.out.println(i+1 + "th ugly number: " + uglyNumbers[i]);
        }

        return uglyNumbers[n-1];
    }

    public static void main(String[] args) {
        final UglyNumber2 s = new UglyNumber2();

        System.out.println(s.nthUglyNumber(10));
    }
}
