package org.morewax.leetcode;

/**
 * Problem
 * Search in 2D Matrix II
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted in ascending from left to right.
 Integers in each column are sorted in ascending from top to bottom.
 For example,

 Consider the following matrix:

 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]
 Given target = 5, return true.

 Given target = 20, return false.
 * Created by Bing on 12/20/2015.
 */
public class SearchIn2DMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int lr = 0;
        int hr = rows-1;
        int lc = 0;
        int hc = cols-1;

        return helper(matrix, lr, hr, lc, hc, target);
    }

    private boolean helper(int[][] matrix, int lr, int hr, int lc, int hc, int target) {
        if (lr > hr || lc > hc) return false;

        int mr = lr + (hr-lr)/2;
        int mc = lc + (hc-lc)/2;

        if (matrix[mr][mc] == target) return true;

        if (matrix[mr][mc] < target) {
            return helper(matrix, mr+1, hr, lc, mc, target) ||
                    helper(matrix, lr, mr, mc+1, hc, target) ||
                    helper(matrix, mr+1, hr, mc+1, hc, target);
        } else {
            return helper(matrix, lr, mr-1, lc, mc-1, target) ||
                    helper(matrix, lr, mr-1, mc, hc, target) ||
                    helper(matrix, mr, hr, lc, mc-1, target);
        }
    }
}
