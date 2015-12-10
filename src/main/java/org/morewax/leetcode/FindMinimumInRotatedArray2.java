package org.morewax.leetcode;

/**
 * Problem
 * Find Minimum in rotated sorted array II (https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/)
 * Follow up for "Find Minimum in Rotated Sorted Array":
 What if duplicates are allowed?

 Would this affect the run-time complexity? How and why?
 Suppose a sorted array is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Find the minimum element.

 The array may contain duplicates.

 * Created by Bing on 12/9/2015.
 */
public class FindMinimumInRotatedArray2 {
    public int findMin(int[] nums) {
        int l = 0;
        int h = nums.length-1;

        while (l < h) {
            int m = l + (h-l)/2;

            if (nums[m] < nums[l] || nums[l] < nums[h]) {
                h = m;
            } else if (nums[m] > nums[l]) {
                l = m+1;
            } else {
                // Following the same reasoning as "Search in Rotated Sorted Array II" problem,
                // When nums[m] == nums[l], there is no way to tell which part nums[m] belongs to.
                ++l;
            }
        }

        return nums[l];
    }
}
