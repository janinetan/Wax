package org.morewax.lintcode;

import org.morewax.support.Utils;

/**
 * Problem
 * Remove Duplicates from Sorted Array II (http://www.lintcode.com/en/problem/remove-duplicates-from-sorted-array-ii/)
 * Follow up for "Remove Duplicates":
 *  What if duplicates are allowed at most twice?
 *  For example,
 *   Given sorted array A = [1,1,1,2,2,3],
 *   Your function should return length = 5, and A is now [1,1,2,2,3].
 *
 * Created by Bing on 12/25/2015.
 */
public class RemoveDuplicatesFromSortedArray2 {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] A) {
        // write your code here
        if (A == null || A.length == 0) return 0;

        // this line is actually buggy because nums[0]-1 could overflow if nums[0] happens to be Integer.MIN_VALUE.
        // If this is the case, then converting nums[0] to long will solve the problem.
        int current = A[0]-1;
        int slow = 0;
        int fast = 0;
        int counter = 0;

        while (fast < A.length) {
            if (A[fast] != current) {
                A[slow++] = A[fast];
                counter = 1;
                current = A[fast];
            } else {
                ++counter;
                if (counter <= 2) {
                    A[slow++] = A[fast];
                }
            }

            ++fast;
        }

        return slow;
    }

    public static void main(String[] args) {
        final RemoveDuplicatesFromSortedArray2 s = new RemoveDuplicatesFromSortedArray2();

        int[] A1 = new int[]{-14,-14,-14,-14,-14,-14,-14,
                -13,-13,-13,-13, -12, -11, -11, -11, -9,
                -9, -9, -7,-7,-7,-6,-6,-5,-5,-5,-4,-4,-4,
                -3,-3,-3,-2,-2,-2,-1,-1,0,0,0,0,1,1,1,2,
                2,2,2,3,3,3,3,3,4,4,4,5,5,6,6,6,7,7,7,7,
                8,8,8,8,9,9,10,10,11,11,11,11,11,12,12,
                12,12,13,13,13,13,14,14,15,16,17,18,18,
                18,20,20,21,21,21,21,21,22,22,22,22,23,24,24,25};
        Utils.printArray(A1, s.removeDuplicates(A1));
    }
}
