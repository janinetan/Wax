package org.morewax.leetcode;

/**
 * Problem
 * Patching Array (https://leetcode.com/problems/patching-array/)
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in
 * range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches
 * required.
 * Example 1:
 *  nums = [1, 3], n = 6
 *  Return 1.
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 *
 * Example 2:
 *  nums = [1, 5, 10], n = 20
 *  Return 2.
 *  The two patches can be [2, 4].
 *
 * Example 3:
 *  nums = [1, 2, 2], n = 5
 *  Return 0.
 * Created by byuan on 1/28/16.
 *
 * Analysis:
 * This article explains the idea: http://www.cnblogs.com/grandyang/p/5165821.html
 */
public class PatchArray {
    public int minPatches(int[] nums, int n) {
        int numOfPatches = 0;
        long firstMissingNumber = 1L;
        int index = 0;

        while (firstMissingNumber <= (long)n) {
            if (index < nums.length && (long)nums[index] <= firstMissingNumber) {
                firstMissingNumber += (long)nums[index++];
            } else {
                firstMissingNumber <<= 1;
                ++numOfPatches;
            }
        }

        return numOfPatches;
    }

    public static void main(String[] args) {
        final PatchArray s = new PatchArray();

        System.out.println(s.minPatches(new int[]{1, 3}, 6));

        System.out.println(s.minPatches(new int[]{1, 5, 10}, 20));

        System.out.println(s.minPatches(new int[]{1, 2, 2}, 5));
    }
}
