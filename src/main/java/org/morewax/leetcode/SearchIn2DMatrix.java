package org.morewax.leetcode;

/** Problem
 *  Search in 2D Matrix (https://leetcode.com/problems/search-a-2d-matrix/)
 *  Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted from left to right.
 The first integer of each row is greater than the last integer of the previous row.
 For example,

 Consider the following matrix:

 [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 Given target = 3, return true.
 * Created by Bing on 12/20/2015.
 */
public class SearchIn2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;

        int l = -1;
        int h = matrix.length;

        while (l+1 < h) {
            int m = l + (h-l)/2;

            if (matrix[m][0] <= target) {
                l = m;
            } else {
                h = m;
            }
        }

        if (l == -1) {
            return false;
        }

        if (matrix[l][0] == target) return true;

        int row = l;

        l = 0;
        h = matrix[row].length-1;
        while (l <= h) {
            int m = l + (h-l)/2;

            if (matrix[row][m] == target) return true;
            if (matrix[row][m] < target) {
                l = m+1;
            } else {
                h = m-1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        final SearchIn2DMatrix s = new SearchIn2DMatrix();

        int[][] A1 = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };

        System.out.println(s.searchMatrix(A1, 3));
        System.out.println(s.searchMatrix(A1, 13));
        System.out.println(s.searchMatrix(A1, 0));
        System.out.println(s.searchMatrix(A1, 80));
    }
}
