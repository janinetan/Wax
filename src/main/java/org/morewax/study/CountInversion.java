package org.morewax.study;

import org.morewax.support.Utils;

/**
 * Created by byuan on 1/14/16.
 */
public class CountInversion {
    public int countInversions(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] n1 = new int[nums.length];
        System.arraycopy(nums, 0, n1, 0, nums.length);
        System.out.println("Count of inversions(count smaller method): " + helper(n1, 0, n1.length));

        int[] n2 = new int[nums.length];
        System.arraycopy(nums, 0, n2, 0, nums.length);
        System.out.println("Count of inversions(bit method): " + bitHelper(n2));

        int[] n3 = new int[nums.length];
        System.arraycopy(nums, 0, n3, 0, nums.length);
        System.out.println("Count of inversions(clrs method): " + stdHelper(n3, 0, n3.length));

        return stdHelper(nums, 0, nums.length);
    }

    // The following code is inspired by the implementation for "Count Smaller Numbers" problem.
    private int helper(int[] nums, int start, int end) {
        if (start + 1 >= end) return 0;
        int mid = start + (end-start)/2;
        int count = helper(nums, start, mid) + helper(nums, mid, end);

        int[] temp = new int[end-start];
        int r = mid;
        int k = 0;
        int l = start;

        while (l < mid) {
            if (r == end || nums[l] < nums[r]) {
                count += r-mid;
                temp[k++] = nums[l++];
            } else {
                temp[k++] = nums[r++];
            }
        }
        System.arraycopy(temp, 0, nums, start, r-start);

        return count;
    }

    // This is the "standard" implementation in CLRS book.
    private int stdHelper(int[]nums, int start, int end) {
        if (start+1 >= end) return 0;

        int mid = start + (end-start)/2;
        int count = stdHelper(nums, start, mid) + stdHelper(nums, mid, end);

        int[] left = new int[mid-start+1];
        for (int i = 0; i < mid-start; ++i) {
            left[i] = nums[start+i];
        }

        int[] right = new int[end-mid+1];
        for (int i = 0; i < end-mid; ++i) {
            right[i] = nums[mid+i];
        }

        // add the sentinel element to both left and right
        left[mid-start] = right[end-mid] = Math.max(nums[mid-1], nums[end-1])+1;

        int l = 0;
        int r= 0;
        for (int k = start; k < end; ++k) {
            if (left[l] <= right[r]) {
                count += r;
                nums[k] = left[l++];
            } else {
                //count += mid-start-l;
                nums[k] = right[r++];
            }
        }

        return count;
    }

    // inspired by
    //  1. http://www.geeksforgeeks.org/count-inversions-array-set-3-using-bit/
    //  2. https://sanugupta.wordpress.com/2014/08/29/binary-indexed-tree-fenwick-tree/
    private int bitHelper(int[] nums) {
        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; ++i) {
            maxValue = Math.max(maxValue, nums[i]);
        }

        final BIT bit = new BIT(maxValue+1);

        int count = 0;

        for (int i = nums.length-1; i >= 0; --i) {
            count += bit.getSum(nums[i]-1);
            bit.updateValue(nums[i], 1);
        }

        return count;
    }

    public static void main(String[] args) {
        final CountInversion s = new CountInversion();

        int[] a1 = new int[]{1, 20, 6, 4, 5};
        System.out.println("Count of inversions: " + s.countInversions(a1));
        Utils.printArray(a1, a1.length);
    }
}
