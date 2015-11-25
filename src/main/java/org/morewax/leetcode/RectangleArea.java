package org.morewax.leetcode;

/**
 * Problem
 * Rectangle Area (https://leetcode.com/problems/rectangle-area/)
 *
 * Find the total area covered by two rectilinear rectangles in a 2D plane.

    Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

    Example : https://leetcode.com/static/images/problemset/rectangle_area.png

    Assume that the total area is never beyond the maximum possible value of int.
 *
 * Created by Bing on 11/25/2015.
 */
public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int r1 = (C-A)*(D-B);
        int r2 = (G-E)*(H-F);
        int r12 = (int) (Math.max(((long)Math.min(C, G) - (long)Math.max(A, E)), 0)*Math.max(((long)Math.min(D, H)-(long)Math.max(B,F)), 0)); // intersection area of R1 and R2

        return r1+r2-r12;
    }

    public static void main(String[] args) {
        int result;

        int A= -1500000001;
        int B = 0;
        int C = -1500000000;
        int D = 1;
        int E = 1500000000;
        int F = 0;
        int G = 1500000001;
        int H = 1;

        RectangleArea rectangleArea = new RectangleArea();
        result = rectangleArea.computeArea(A, B, C, D, E, F, G, H);
        System.out.println("Area: " + result);
    }
}
