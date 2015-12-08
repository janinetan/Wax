package org.morewax.leetcode;

import org.morewax.support.Utils;

/**
 * Problem
 * Rotate image (https://leetcode.com/problems/rotate-image/)
 *
 * You are given an n x n 2D matrix representing an image.

 * Rotate the image by 90 degrees (clockwise).

 * Follow up:
 * Could you do this in-place?

 * Example:
 * 1  2  3  4  5
 * 6  7  8  9  10
 * 11 12 13 14 15
 * 16 17 18 19 20
 * 21 22 23 24 25
 *
 * After rotation
 * 21  16  11  6  1
 * 22  17  12  7  2
 * 23  18  13  8  3
 * 24  19  14  9  4
 * 25  20  15 10  5
 *
 * Created by byuan on 12/7/15.
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        if (matrix.length <= 1) return;

        System.out.println("Before rotation");
        Utils.printMatrix(matrix);

        int n = matrix.length;
        int layers = n/2;

        for (int layer = 0; layer < layers; ++layer) {
            // rotate the image layer by layer
            for (int i = layer; i < n-1-layer; ++i) {
                int temp = matrix[layer][i];

                // move left to top
                matrix[layer][i] = matrix[n-1-i][layer];

                // move bottom to left
                matrix[n-1-i][layer] = matrix[n-1-layer][n-1-i];

                // move right to bottom
                matrix[n-1-layer][n-1-i] = matrix[i][n-1-layer];

                // move top to right
                matrix[i][n-1-layer] = temp;
            }
        }

        System.out.println("After rotation");
        Utils.printMatrix(matrix);
        System.out.println("");
    }

    public static void main(String[] args) {
        RotateImage s = new RotateImage();

        int[][] m1 = new int[][] {
                {1, 2},
                {3, 4}
        };
        s.rotate(m1);

        int[][] m2 = new int[][] {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        s.rotate(m2);

        int[][] m3 = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        s.rotate(m3);
    }
}
