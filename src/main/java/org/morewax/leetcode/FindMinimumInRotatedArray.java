package org.morewax.leetcode;

/**
 * Problem
 * Find Minimum in Rotated Sorted Array ()
 *
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 * Find the minimum element.

 * You may assume no duplicate exists in the array.
 *
 * Created by Bing on 12/9/2015.
 */
public class FindMinimumInRotatedArray {
    public int findMin(int[] nums) {
        int l = 0;
        int h = nums.length-1;

        while (l < h) {
            int m = l + (h-l)/2;

            if (nums[m] < nums[l] || nums[l] < nums[h]) {
                h = m;
            } else {
                l = m+1;
            }
            /* the below code is equivalent as the above snippet. It's more verbose but easier to understand.
            if (nums[l] <= nums[m]) {
                if (nums[h] < nums[l]) {
                    l = m+1;
                } else {
                    h = m;
                }
            } else {
                h = m;
            }*/
        }

        return nums[l];
    }

    public static void main(String[] args) {
        final FindMinimumInRotatedArray s = new FindMinimumInRotatedArray();

        int[] A1 = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println("Min: " + s.findMin(A1));

        int[] A2 = new int[]{-1, 1, 2, 4, 5, 6, 7};
        System.out.println("Min: " + s.findMin(A2));

        int[] A3 = new int[]{4, 5, 6, 7, 8, 9, -2};
        System.out.println("Min: " + s.findMin(A3));

        int[] A4 = new int[]{4};
        System.out.println("Min: " + s.findMin(A4));

        int[] A5 = new int[]{3, 5};
        System.out.println("Min: " + s.findMin(A5));

        int[] A6 = new int[]{9, 7};
        System.out.println("Min: " + s.findMin(A6));

        int[] A7 = new int[]{3, 1, 2};
        System.out.println("Min: " + s.findMin(A7));
    }
}
