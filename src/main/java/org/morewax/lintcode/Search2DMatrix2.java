package org.morewax.lintcode;

/**
 * Problem
 * Search in 2D Matrix II (http://www.lintcode.com/en/problem/search-a-2d-matrix-ii/)
 * Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.
 * This matrix has the following properties:
 *  Integers in each row are sorted from left to right.
 *  Integers in each column are sorted from up to bottom.
 *  No duplicate integers in each row or column.
 *
 * Example
 * Consider the following matrix:
 *  [
 *   [1, 3, 5, 7],
 *   [2, 4, 7, 8],
 *   [3, 5, 9, 10]
 *  ]
 *
 * Given target = 3, return 2.
 *
 * Created by Bing on 12/25/2015.
 */
public class Search2DMatrix2 {
    /**
     * @param matrix: A list of lists of integers
     * @param: target A number you want to search in the matrix
     * @return: An integer indicate the occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] counter = new int[1];

        int i = 0;
        int j = matrix[0].length-1;

        while (i >=0 && i < matrix.length && j >= 0 && j < matrix[0].length) {
            if (matrix[i][j] == target) {
                ++counter[0];
                ++i;
                --j;
            } else if (matrix[i][j] > target) {
                --j;
            } else {
                ++i;
            }
        }

        return counter[0];
    }

    public static void main(String[] args) {
        final Search2DMatrix2 s = new Search2DMatrix2();

        int[][] m1 = new int[][] {
                {1, 3, 5, 7},
                {2, 4, 7, 8},
                {3, 5, 9, 10}
        };

        System.out.println(s.searchMatrix(m1, 3));
    }
}
