package org.morewax.leetcode;

/**
 * Problem:
 * Minimum Size Subarray Sum (https://leetcode.com/problems/minimum-size-subarray-sum/)
 *
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 *
 * Created by byuan on 11/24/15.
 */
public class MinSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;

        int n = nums.length;
        int minLength = n+1;
        int start = 0;
        int sum = 0;

        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            if (sum < s) {
                continue;
            } else {
                while (sum >= s) {
                    minLength = Math.min(minLength, i-start+1);
                    sum -= nums[start++];
                }
            }
        }

        return (minLength == n+1)? 0 : minLength;
    }

    public static void main(String[] args) {
        MinSizeSubarraySum solution = new MinSizeSubarraySum();
        int s;
        int result;

        int[] a1 = new int[]{2,3,1,2,4,3};
        s = 7;

        result = solution.minSubArrayLen(s, a1);
        System.out.println("Minimum size: " + result);
    }
}
