package org.morewax.lintcode;

/**
 * Problem
 * Merge Two Sorted Array (http://www.lintcode.com/en/problem/merge-sorted-array/)
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 *  Example
 *  A = [1, 2, 3, empty, empty], B = [4, 5]
 *  After merge, A will be filled as [1, 2, 3, 4, 5]
 *
 *  Created by Bing on 12/24/2015.
 */
public class MergeTwoSortedArray {
    /**
     * @param A: sorted integer array A which has m elements,
     *           but size of A is m+n
     * @param B: sorted integer array B which has n elements
     * @return: void
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        int ia = m-1;
        int ib = n-1;
        int index = m+n-1;

        while (ia >= 0 && ib >= 0) {
            if (A[ia] >= B[ib]) {
                A[index--] = A[ia--];
            } else {
                A[index--] = B[ib--];
            }
        }

        while (ib >= 0) {
            A[index--] = B[ib--];
        }
    }

    public void print(int[] A, int count) {
        System.out.print("[");

        for (int i = 0; i < count; ++i) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(A[i]);
        }

        System.out.println("]");
    }

    public static void main(String[] args) {
        final MergeTwoSortedArray s = new MergeTwoSortedArray();

        int[] a = new int[]{1, 2, 3, 0, 0, 0, 0};
        int[] b = new int[]{-7, -1, 4, 5};
        s.mergeSortedArray(a, 3, b, 4);
        s.print(a, 7);
    }
}
