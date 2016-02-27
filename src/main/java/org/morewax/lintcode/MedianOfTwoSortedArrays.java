package org.morewax.lintcode;

/**
 * Problem
 * Median of Two Sorted Arrays (http://www.lintcode.com/en/problem/median-of-two-sorted-arrays/)
 * There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.
 * Example
 * Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.
 * Given A=[1,2,3] and B=[4,5], the median is 3.
 * Created by byuan on 2/26/16.
 */
public class MedianOfTwoSortedArrays {
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        int m = A.length;
        int n = B.length;
        int k = (m+n+1)/2;
        boolean isOdd = (((m+n)&0x01) == 1)? true: false;

        int l = Math.max(0, k-n);
        int h = Math.min(m, k);

        while (l < h) {
            int x = l + (h-l)/2;
            int A_x_1 = (x < 1)? Integer.MIN_VALUE : A[x-1];
            int A_x = (x >= m)? Integer.MAX_VALUE : A[x];
            int B_k_x_1 = (k-x < 1)? Integer.MIN_VALUE : B[k-x-1];
            int B_k_x = (k-x >= n)? Integer.MAX_VALUE : B[k-x];

            if (A_x < B_k_x_1) {
                l = x+1;
            } else if (B_k_x < A_x_1) {
                h = x-1;
            } else {
                if (isOdd) {
                    return (double)Math.max(A_x_1, B_k_x_1);
                } else {
                    return (double)(Math.max(A_x_1, B_k_x_1) + Math.min(A_x, B_k_x))/2.0;
                }
            }
        }

        int A_l_1 = (l < 1)? Integer.MIN_VALUE : A[l-1];
        int A_l = (l >= m)? Integer.MAX_VALUE : A[l];
        int B_k_l_1 = (k-l < 1)? Integer.MIN_VALUE : B[k-l-1];
        int B_k_l = (k-l >= n)? Integer.MAX_VALUE : B[k-l];

        if (isOdd) {
            return (double)Math.max(A_l_1, B_k_l_1);
        } else {
            return (double)(Math.max(A_l_1, B_k_l_1) + Math.min(A_l, B_k_l))/2.0;
        }
    }

    public static void main(String[] args) {
        final MedianOfTwoSortedArrays s = new MedianOfTwoSortedArrays();

        System.out.println("Median: " + s.findMedianSortedArrays(new int[]{1,2,3,4,5,6}, new int[]{2, 3, 4, 5}));
        System.out.println("Median: " + s.findMedianSortedArrays(new int[]{1,2,3}, new int[]{4, 5}));
        System.out.println("Median: " + s.findMedianSortedArrays(new int[]{}, new int[]{1}));
    }
}
