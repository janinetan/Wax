package org.morewax.leetcode;

import java.util.Stack;

/**
 * Problem
 * Maximum Rectangle (https://leetcode.com/problems/maximal-rectangle/)
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 * Created by byuan on 12/23/15.
 */
public class MaxRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        int[][] heights = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (i == 0 || matrix[i-1][j] == '0' || matrix[i][j] == '0') {
                    heights[i][j] = matrix[i][j]-'0';
                } else {
                    heights[i][j] = heights[i-1][j] + 1;
                }
            }
        }

        int maxArea = 0;
        for (int i = 0; i < matrix.length; ++i) {
            maxArea = Math.max(maxArea, largestRectangle(heights[i]));
        }

        return maxArea;
    }

    private int largestRectangle(int[] height) {
        int n = height.length;
        Stack<Integer> s = new Stack<>();
        int[] width = new int[n];

        for (int i = 0; i < n; ++i) {
            while (!s.isEmpty() && height[s.peek()] >= height[i]) {
                s.pop();
            }

            int boundary;
            if (s.isEmpty()) {
                boundary = -1;
            } else {
                boundary = s.peek();
            }

            width[i] = i-boundary-1;
            s.push(i);
        }

        s.clear();

        int maxRect = 0;

        for (int i = n-1; i >= 0; --i) {
            while (!s.isEmpty() && height[s.peek()] >= height[i]) {
                s.pop();
            }

            int boundary;
            if (s.isEmpty()) {
                boundary = n;
            } else {
                boundary = s.peek();
            }

            width[i] += boundary - i;
            maxRect = Math.max(maxRect, height[i]*width[i]);
            s.push(i);
        }

        return maxRect;
    }

    public static void main(String[] args) {
        final MaxRectangle s = new MaxRectangle();

        char[][] m1 = new char[][]{
                {'0', '1'},
                {'1', '0'}
        };

        System.out.println(s.maximalRectangle(m1));
    }
}
