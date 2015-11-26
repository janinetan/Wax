package org.morewax.leetcode;

/**
 * Problem
 * Range Sum Query - Mutable
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 The update(i, val) function modifies nums by updating the element at index i to val.
 Example:
 Given nums = [1, 3, 5]

 sumRange(0, 2) -> 9
 update(1, 2)
 sumRange(0, 2) -> 8
 Note:
 The array is only modifiable by the update function.
 You may assume the number of calls to update and sumRange function is distributed evenly.

 * Created by byuan on 11/25/15.
 *
 * Hint:
 *  1. Use Binary Index Tree or;
 *  2. Use Segment Tree
 */
public class RangeSumQuery {
    /**
     * Binary Index Tree
    private int[] num;
    private int[] bit;

    public RangeSumQuery(int[] nums) {
        num = new int[nums.length + 1];
        bit = new int[nums.length+1];

        for (int i = 0; i < nums.length; ++i) {
            update(i, nums[i]);
        }
    }

    public void update(int i, int val) {
        int diff = val - num[i + 1];
        for (int j = i + 1; j < num.length; j += (j&-j)) {
            bit[j] += diff;
        }
        num[i + 1] = val;
    }

    public int sumRange(int i, int j) {
        return getSum(j + 1) - getSum(i);
    }

    protected int getSum(int i) {
        int res = 0;

        for (int j = i; j > 0; j -= (j&-j)) {
            res += bit[j];
        }

        return res;
    }*/

    // Segment Tree Impl
    private int[] segmentTree;
    private int[] data; // for the original data

    public RangeSumQuery(int[] nums) {
        // make a copy of nums
        int n = nums.length;
        data = new int[n];

        System.arraycopy(nums, 0, data, 0, n);

        int segmentTreeHeight = (int)Math.ceil(Math.log(n)/Math.log(2));
        int segmentTreeSize = 2*(int)Math.pow(2, segmentTreeHeight) - 1;
        segmentTree = new int[segmentTreeSize];

        buildSegmentTree(0, n-1, 0);
    }

    public int sumRange(int i, int j) {
        return sumRangeHelper(0, data.length-1, i, j, 0);
    }

    public void update(int i, int val) {
        int diff = val - data[i];

        data[i] = val;

        updateHelper(0, data.length-1, i, diff, 0);
    }

    private int buildSegmentTree(int start, int end, int segIndex) {
        if (start == end) {
            segmentTree[segIndex] = data[start];
            return segmentTree[segIndex];
        }

        int m = getMiddle(start, end);
        segmentTree[segIndex] = buildSegmentTree(start, m, 2*segIndex+1) + buildSegmentTree(m+1, end, 2*segIndex+2);
        return segmentTree[segIndex];
    }

    private int sumRangeHelper(int ss, int se, int qs, int qe, int si) {
        if (qs <= ss && se <= qe) {
            return segmentTree[si];
        }

        if (se < qs || qe < ss) return 0;

        int mid = getMiddle(ss, se);

        return sumRangeHelper(ss, mid, qs, qe, 2*si+1) + sumRangeHelper(mid+1, se, qs, qe, 2*si+2);
    }

    private void updateHelper(int ss, int se, int i, int diff, int si) {
        if (i < ss || se < i) return;

        segmentTree[si] += diff;

        if (ss != se) {
            int mid = getMiddle(ss, se);

            updateHelper(ss, mid, i, diff, 2*si+1);
            updateHelper(mid+1, se, i, diff, 2*si+2);
        }
    }

    private int getMiddle(int start, int end) {
        return start + (end-start)/2;
    }

    public static void main(String[] args) {
        int result;

        int[] a1 = new int[]{1, 3, 5};
        RangeSumQuery rangeSumQuery = new RangeSumQuery(a1);
        result = rangeSumQuery.sumRange(0, 2);
        System.out.println("Result: " + result);
        rangeSumQuery.update(1, 2);
        result = rangeSumQuery.sumRange(0, 2);
        System.out.println("Result: " + result);

        System.out.println("Hello World!");
    }
}
