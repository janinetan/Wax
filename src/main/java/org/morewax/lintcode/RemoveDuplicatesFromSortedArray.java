package org.morewax.lintcode;

/**
 * Problem
 * Remove Duplicates From Sorted Array (http://www.lintcode.com/en/problem/remove-duplicates-from-sorted-array/)
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * Example
 *  Given input array A = [1,1,2],
 *  Your function should return length = 2, and A is now [1,2].
 *
 * Created by Bing on 12/24/2015.
 */
public class RemoveDuplicatesFromSortedArray {
    /**
     * @param nums: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) return 0;

        // this line is actually buggy because nums[0]-1 could overflow if nums[0] happens to be Integer.MIN_VALUE.
        // If this is the case, then converting nums[0] to long will solve the problem.
        int current = nums[0]-1;
        int slow = 0;
        int fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != current) {
                nums[slow++] = nums[fast];
                current = nums[fast];
            }

            ++fast;
        }

        return slow;
    }

    public void print(int[] A, int count) {
        System.out.print("[");

        for (int i = 0; i < count; ++i) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(A[i]);
        }

        System.out.println("]");
    }

    public static void main(String[] args) {
        final RemoveDuplicatesFromSortedArray s = new RemoveDuplicatesFromSortedArray();

        int[] A1 = new int[]{1,1,2};
        s.print(A1, s.removeDuplicates(A1));
    }
}
