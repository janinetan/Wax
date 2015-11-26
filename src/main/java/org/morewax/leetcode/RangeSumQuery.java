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
