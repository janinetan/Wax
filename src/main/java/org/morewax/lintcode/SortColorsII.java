package org.morewax.lintcode;

import org.morewax.support.Utils;

/**
 * Problem
 * Sort Colors II (http://www.lintcode.com/en/problem/sort-colors-ii/)
 * Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same
 * color are adjacent, with the colors in the order 1, 2, ... k.
 * Example
 * Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].
 * Created by byuan on 2/22/16.
 */
public class SortColorsII {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // write your code here
        int start = 0;
        for (int i = 1; i <= k; ++i) {
            start = split(colors, start, i);
        }
    }

    private int split(int[] colors, int start, int color) {
        // [start, sameColor] == color
        // [sameColor+1, i-1] <> color;
        // [i, colors.length-1] unknown
        int sameColor = start-1;

        for (int i = start; i < colors.length; ++i) {
            if (colors[i] == color) {
                int temp = colors[sameColor+1];
                colors[sameColor+1] = colors[i];
                colors[i] = temp;
                ++sameColor;
            } else {
                continue;
            }
        }

        return sameColor+1;
    }

    public static void main(String[] args) {
        final SortColorsII s = new SortColorsII();

        int[] cs1 = new int[]{2,1,1,2,2};
        s.sortColors2(cs1, 2);
        Utils.printArray(cs1, -1);
    }
}
