package org.morewax.leetcode;

/**
 * Problem
 * Find the length of longest increasing subsequence (https://leetcode.com/problems/longest-increasing-subsequence/)
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

 For example,
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination,
 it is only necessary for you to return the length.

 Your algorithm should run in O(n2) complexity.
 * Created by byuan on 12/7/15.
 */
public class LengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        int result = 0;

        if (nums.length > 0) {
            int[] dp = new int[nums.length];
            dp[0] = 1;
            result = dp[0];

            for (int i = 1; i < nums.length; ++i) {
                for (int j = 0; j < i; ++j) {
                    if (nums[j] < nums[i] && dp[j] > dp[i]) {
                        dp[i] = dp[j];
                    }
                }
                dp[i] += 1;
                result = Math.max(dp[i], result);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        final LengthOfLIS s = new LengthOfLIS();

        int[] A1 = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(s.lengthOfLIS(A1));
    }
}
