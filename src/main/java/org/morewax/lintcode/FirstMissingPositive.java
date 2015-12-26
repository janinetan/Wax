package org.morewax.lintcode;

/**
 * Problem
 * First Missing Positive (http://www.lintcode.com/en/problem/first-missing-positive/)
 * Given an unsorted integer array, find the first missing positive integer.
 * Example
 *  Given [1,2,0] return 3,
 *  and [3,4,-1,1] return 2.
 *
 * Created by Bing on 12/25/2015.
 */
public class FirstMissingPositive {
    /**
     * @param A: an array of integers
     * @return: an integer
     */
    public int firstMissingPositive(int[] A) {
        // write your code here
        if (A == null || A.length == 0) return 1;

        int start = split(A);
        int n = A.length;

        for (int i = start; i < n; ++i) {
            if (Math.abs(A[i]) + start <= n && A[Math.abs(A[i]) + start - 1] > 0) {
                A[Math.abs(A[i]) + start - 1] = -A[Math.abs(A[i]) + start - 1];
            }
        }

        for (int i = start; i < n; ++i) {
            if (A[i] > 0) {
                return i - start + 1;
            }
        }

        return n - start + 1;
    }

    private int split(int[] A) {
        // [0, positive-1] <= 0;
        // [positive, i-1] > 0
        // [i, A.length) unknown
        int positive = 0;

        for (int i = 0; i < A.length; ++i) {
            if (A[i] <= 0) {
                int temp = A[positive];
                A[positive] = A[i];
                A[i] = temp;
                ++positive;
            }
        }

        return positive;
    }

    public static void main(String[] args) {
        final FirstMissingPositive s = new FirstMissingPositive();

        int[] A1 = new int[]{1, 2, 0};
        System.out.println(s.firstMissingPositive(A1));

        int[] A2 = new int[]{3, 4, -1, 1};
        System.out.println(s.firstMissingPositive(A2));
    }
}