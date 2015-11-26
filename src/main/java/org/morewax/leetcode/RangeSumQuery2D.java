package org.morewax.leetcode;

/**
 * Problem Range Sum Query 2D - Immutable
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

 Range Sum Query 2D
 Example image: https://leetcode.com/static/images/courses/range_sum_query_2d.png

 The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

 Example:
 Given matrix = [
 [3, 0, 1, 4, 2],
 [5, 6, 3, 2, 1],
 [1, 2, 0, 1, 5],
 [4, 1, 0, 1, 7],
 [1, 0, 3, 0, 5]
 ]

 sumRegion(2, 1, 4, 3) -> 8
 sumRegion(1, 1, 2, 2) -> 11
 sumRegion(1, 2, 2, 4) -> 12
 * Created by byuan on 11/26/15.
 */
public class RangeSumQuery2D {
    private int[][] sums;

    public RangeSumQuery2D (int[][] matrix) {
        if (matrix.length > 0) {
            sums = new int[matrix.length][matrix[0].length];
            // compute sums
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix[0].length; ++j) {
                    sums[i][j] = getSum(i, j-1) + getSum(i-1, j) - getSum(i-1, j-1) + matrix[i][j];
                }
            }
        }
    }

    private int getSum(int i, int j) {
        if (i < 0 || j < 0) return 0;
        return sums[i][j];
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sums[row2][col2] - getSum(row1-1, col2) - getSum(row2, col1-1) + getSum(row1-1, col1-1);
    }


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);
}
