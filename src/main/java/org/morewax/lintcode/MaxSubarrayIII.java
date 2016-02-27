package org.morewax.lintcode;

/**
 * Problem
 * Maximum Subarray III (http://www.lintcode.com/en/problem/maximum-subarray-iii/)
 * Given an array of integers and a number k, find knon-overlapping subarrays which have the largest sum.
 * The number in each subarray should be contiguous.
 * Return the largest sum.
 *
 * Note
 * The subarray should contain at least one number
 *
 * Example
 * Given [-1,4,-2,3,-2,3],k=2, return 8
 *
 * Analysis:
 * d[i][j]代表0->i-1元素中j个subarray的maxsum  (注意不包含元素i)
 * d[i][j] = max(d[i][j], d[m][j-1] + max) (m = j-1 .... i-1;
 * max需要单独求，是从元素i-1到m的max subarray, 用求max subarray的方法，需要从后往前算）
 *
 * Created by byuan on 2/26/16.
 */
public class MaxSubarrayIII {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        // write your code here
        int n = nums.length;
        int[][] d = new int[n+1][k+1];
        for (int j = 1; j <= k; j++) {
            for (int i = j; i <= n; i++) {
                d[i][j] = Integer.MIN_VALUE;
                int max = Integer.MIN_VALUE;
                int localMax = 0;
                for (int m = i-1; m >= j-1; m--) {
                    localMax = Math.max(nums[m], nums[m]+localMax);
                    max = Math.max(localMax, max);
                    d[i][j] = Math.max(d[i][j], d[m][j-1] + max);
                }
            }
        }
        return d[n][k];
    }
}
