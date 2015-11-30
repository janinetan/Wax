package org.morewax.leetcode;

/**
 * Problem
 * Find Peek Element (https://leetcode.com/problems/find-peak-element/)
 *
 * A peak element is an element that is greater than its neighbors.

 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

 * You may imagine that num[-1] = num[n] = -∞.

 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * Created by Bing on 11/27/2015.
 */
public class FindPeekElement {
    public int findPeakElement(int[] nums) {
        if (nums.length == 0) return -1;

        if (nums.length == 1) return 0;

        int n = nums.length;
        int l = 0;
        int h = n-1;

        while (l <= h) {
            int m = l + (h-l)/2;

            if ((m == 0 || nums[m-1] < nums[m]) && (m == n-1 || nums[m] > nums[m+1])) {
                return m;
            } else if (m > 0 && nums[m-1] > nums[m]) {
                h = m-1;
            } else {
                l = m+1;
            }
        }

        return h;
    }
}
