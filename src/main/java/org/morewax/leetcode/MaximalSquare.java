package org.morewax.leetcode;

/**
 * Problem
 * Maximal Square
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
 *
 * For example, given the following matrix:
 *  1 0 1 0 0
 *  1 0 1 1 1
 *  1 1 1 1 1
 *  1 0 0 1 0
 *  Return 4.
 *
 * Created by byuan on 12/22/15.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        // dp[i][j] the maximal side length of square that ends at (i, j).
        // the other words, the bottom-right corner of the square is (i, j)
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxSideLength = 0;

        for (int i = 0; i < matrix.length; ++i) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                maxSideLength = 1;
            }
        }

        for (int i = 0; i < matrix[0].length; ++i) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                maxSideLength = 1;
            }
        }

        for (int i = 1; i < matrix.length; ++i) {
            for (int j = 1; j < matrix[0].length; ++j) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    if (dp[i][j] > maxSideLength) {
                        maxSideLength = dp[i][j];
                    }
                }
            }
        }

        return maxSideLength*maxSideLength;
    }

    public static void main(String[] args) {
        final MaximalSquare s = new MaximalSquare();

        char [][] m1 = new char[][] {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        System.out.println(s.maximalSquare(m1));
    }
}
