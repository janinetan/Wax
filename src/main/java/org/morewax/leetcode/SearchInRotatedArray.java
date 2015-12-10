package org.morewax.leetcode;

/**
 * Problem
 * Search in Rotated Sorted Array (https://leetcode.com/problems/search-in-rotated-sorted-array/)
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.
 * Created by Bing on 12/9/2015.
 */
public class SearchInRotatedArray {
    public int search(int[] nums, int target) {
        int l = 0;
        int h = nums.length-1;

        while (l <= h) {
            int m = l + (h-l)/2;

            if (nums[m] == target) return m;

            if (nums[l] <= nums[m]) { // m is in the first half
                if (nums[m] < target || target < nums[l]) {
                    l = m+1;
                } else {
                    h = m-1;
                }
            } else {
                if (nums[m] > target || nums[h] < target) {
                    h = m-1;
                } else {
                    l = m+1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        final SearchInRotatedArray s = new SearchInRotatedArray();

        int[] A1 = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println("Index : " + s.search(A1, 1));
        System.out.println("Index : " + s.search(A1, 6));
        System.out.println("Index : " + s.search(A1, 3));

        int[] A2 = new int[]{0, 1, 2, 4, 5, 6, 7};
        System.out.println("Index : " + s.search(A2, 1));
        System.out.println("Index : " + s.search(A2, 6));
        System.out.println("Index : " + s.search(A2, 3));
    }
}
