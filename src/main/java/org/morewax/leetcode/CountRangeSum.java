package org.morewax.leetcode;

/**
 * Problem
 * Count Range Sum (https://leetcode.com/problems/count-of-range-sum/)
 *
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 * Note:
 * A naive algorithm of O(n2) is trivial. You MUST do better than that.
 * Example:
 * Given nums = [-2, 5, -1], lower = -2, upper = 2,
 * Return 3.
 * The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 *
 * Analysis:
 * This discussion explains the basic idea much better:
 * https://leetcode.com/discuss/79083/share-my-solution
 * Created by byuan on 1/12/16.
 */
public class CountRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n+1];
        for (int i = 0; i < n; ++i) {
            sums[i+1] = sums[i] + nums[i];
        }

        return countWhileMerge(sums, 0, n+1, lower, upper);
    }

    private int countWhileMerge(long[] sums, int start, int end, int lower, int upper) {
        if (start+1 >= end) return 0;

        int mid = start + (end-start)/2;

        int count = countWhileMerge(sums, start, mid, lower, upper) +
                countWhileMerge(sums, mid, end, lower, upper);

        int k = mid;
        int j = mid;
        int t = mid;
        long[] temp = new long[end-start];

        for (int i = start, r = 0; i < mid; ++i, ++r) {
            while (k < end && sums[k]-sums[i] < lower) ++k;
            while (j < end && sums[j]-sums[i] <= upper) ++j;
            while (t < end && sums[t] < sums[i]) temp[r++] = sums[t++];
            temp[r] = sums[i];
            count += j-k;
        }

        System.arraycopy(temp, 0, sums, start, t-start);

        return count;
    }

    public static void main(String[] args) {
        final CountRangeSum s = new CountRangeSum();

        System.out.println("Count: " + s.countRangeSum(new int[]{-2, 5, -1}, -2, 2));
    }
}
