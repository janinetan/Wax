package org.morewax.lintcode;

/**
 * Problem
 * Minimum Size Subarray Sum (http://www.lintcode.com/en/problem/minimum-size-subarray-sum/)
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the
 * sum ≥ s. If there isn't one, return -1 instead.
 * Example
 * Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint.
 * Challenge
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 * Created by byuan on 1/28/16.
 */
public class MinsizeSubarraySum {
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        // write your code here
        if (nums == null || nums.length == 0) return -1;

        int result = -1;
        int start = 0;
        int end = -1;
        int sumSoFar = 0;

        for (int i = 0; i < nums.length; ++i) {
            sumSoFar += nums[i];
            if (sumSoFar < s) {
                continue;
            } else {
                end = i;
                while (sumSoFar >= s) {
                    sumSoFar -= nums[start++];
                }
                if (result == -1) {
                    result = end-start+2;
                } else {
                    result = Math.min(result, end-start+2);
                }
            }
        }

        return result;
    }

    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     * @Remark: this is the O(nlogn) solution
     */
    public int minimumSize2(int[] nums, int s) {
        if (nums == null || nums.length == 0) return -1;

        int result = Integer.MAX_VALUE;
        int n = nums.length;
        int[] sums = new int[n+1];
        for (int i = 0; i < n; ++i) {
            sums[i+1] = sums[i] + nums[i];

            if (sums[i+1] >= s) {
                int j = upperBound(sums, 0, i, sums[i+1]-s+1);
                if (j != -1) {
                    result = Math.min(result, i+1-j);
                }
            }
        }

        return (result == Integer.MAX_VALUE)? -1 : result;
    }

    private int upperBound(int[] sums, int start, int end, int target) {
        int l = start-1;
        int h = end+1;

        while (l+1 < h) {
            int m = l + (h-l)/2;

            if (sums[m] < target) {
                l = m;
            } else {
                h = m;
            }
        }

        return (l == start-1)? -1 : l;
    }

    public static void main(String[] args) {
        final MinsizeSubarraySum s = new MinsizeSubarraySum();

        System.out.println(s.minimumSize(new int[]{2,3,1,2,4,3}, 7));
        System.out.println(s.minimumSize2(new int[]{2,3,1,2,4,3}, 7));
    }
}
