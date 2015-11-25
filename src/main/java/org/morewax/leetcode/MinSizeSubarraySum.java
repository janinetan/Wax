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
    /**
     * O(N) solution
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
    }*/

    // O(nlogn) solution
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        int[] sums = new int[n+1];
        int minLength = n+1;

        for (int i = 0; i < n; ++i) {
            sums[i+1] = sums[i] + nums[i];

            if (sums[i+1] >= s) {
                int j = binarySearch(sums, 0, i, sums[i+1]-s);
                if (j != -1) {
                    minLength = Math.min(minLength, i+1-j);
                }
            }
        }

        return (minLength == n+1)? 0 : minLength;
    }

    // Search for the largest index i so that
    // A[i] <= target and start <= i && i <= end
    private int binarySearch(int[] A, int start, int end, int target) {
        int l = start-1;
        int h = end+1;

        while (l+1 < h) {
            int m = l + (h-l)/2;

            if (A[m] > target) {
                h = m;
            } else {
                l = m;
            }
        }

        return (l < start)? -1 : l;
    }

    public static void main(String[] args) {
        MinSizeSubarraySum solution = new MinSizeSubarraySum();
        int s;
        int result;

        int[] a1 = new int[]{2,3,1,2,4,3};
        s = 7;
        result = solution.minSubArrayLen(s, a1);
        System.out.println("Minimum size: " + result);

        int[] a2 = new int[]{1, 4, 4};
        s = 4;
        result = solution.minSubArrayLen(s, a2);
        System.out.println("Minimum size: " + result);
    }
}
