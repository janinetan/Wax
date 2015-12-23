package org.morewax.leetcode;

import java.util.Stack;

/**
 * Problem
 * Largest Rectangle in Histogram ()
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * Example image: http://www.leetcode.com/wp-content/uploads/2012/04/histogram.png
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * Example image: http://www.leetcode.com/wp-content/uploads/2012/04/histogram_area.png
 *
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 *
 * Created by byuan on 12/23/15.
 */
public class LargestRectangleHistogram {
    public int largestRectangleArea(int[] height) {
        if (height.length == 0) return 0;

        int n = height.length;
        Stack<Integer> s = new Stack<>();
        int[] width  = new int[n];

        for (int i = 0; i < n; ++i) {
            int boundary;

            while (!s.isEmpty() && height[s.peek()] >= height[i]) {
                s.pop();
            }

            if (s.isEmpty()) {
                boundary = -1;
            } else {
                boundary = s.peek();
            }

            width[i] = i-boundary-1;

            s.push(i);
        }

        s.clear();

        int maxArea = 0;

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

            width[i] += boundary-i;

            s.push(i);

            maxArea = Math.max(maxArea, height[i]*width[i]);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        final LargestRectangleHistogram s = new LargestRectangleHistogram();

        int[] h1 = new int[]{2,1,5,6,2,3};
        System.out.println(s.largestRectangleArea(h1));

        int[] h2 = new int[]{1, 1};
        System.out.println(s.largestRectangleArea(h2));
    }
}
