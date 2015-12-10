package org.morewax.leetcode;

/**
 * Problem
 * Search in Rotated Sorted Array II (https://leetcode.com/problems/search-in-rotated-sorted-array-ii/)
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?

 * Would this affect the run-time complexity? How and why?

 * Write a function to determine if a given target is in the array.
 * Created by Bing on 12/9/2015.
 */
public class SearchInRotatedArray2 {
    public boolean search(int[] nums, int target) {
        int l = 0;
        int h = nums.length-1;

        while (l <= h) {
            int m = l + (h-l)/2;

            if (nums[m] == target) return true;

            if (nums[l] < nums[m]) { // m is in the first half
                if (nums[m] < target || target < nums[l]) {
                    l = m+1;
                } else {
                    h = m-1;
                }
            } else if (nums[l] > nums[m]) { // m is in the second half.
                if (nums[m] > target || nums[h] < target) {
                    h = m-1;
                } else {
                    l = m+1;
                }
            } else {
                // Since there could be duplicates, thus:
                // when nums[l] == nums[m], it's impossible to determine whether nums[m] is
                // in first half or not.
                // the only option is to increment l.
                ++l;
            }
        }

        return false;
    }
}
