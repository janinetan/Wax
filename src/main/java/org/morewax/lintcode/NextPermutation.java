package org.morewax.lintcode;

import org.morewax.support.Utils;

/**
 * Problem
 * Next Permutation (http://www.lintcode.com/en/problem/next-permutation/)
 * Given a list of integers, which denote a permutation.
 * Find the next permutation in ascending order.
 * Example
 *  For [1,3,2,3], the next permutation is [1,3,3,2]
 *  For [4,3,2,1], the next permutation is [1,2,3,4]
 *
 * Created by Bing on 12/28/2015.
 */
public class NextPermutation {
    /**
     * @param nums: an array of integers
     * @return: return nothing (void), do not return anything, modify nums in-place instead
     */
    public int[] nextPermutation(int[] nums) {
        // write your code here
        int k = nums.length-1;
        while (k > 0) {
            if (nums[k-1] < nums[k]) {
                break;
            }
            --k;
        }

        if (k == 0) {
            // this means "nums" is already is at the end of ascending permutation sequence,
            // the next permutation will be the one rolling back to the beginning of the sequence
            // see the second example above.
            swap(nums, 0, nums.length-1);
        } else {
            int j = nums.length-1;
            while (j >= k) {
                if (nums[j] > nums[k-1]) {
                    break;
                }
            }
            int temp = nums[j];
            nums[j] = nums[k-1];
            nums[k-1] = temp;
            swap(nums, k, nums.length-1);
        }

        return nums;
    }

    private void swap(int[] nums, int start, int end) {
        while (start <= end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            ++start;
            --end;
        }
    }

    public static void main(String[] args) {
        final NextPermutation s = new NextPermutation();

        int[] a1 = new int[]{1, 3, 2, 3};
        Utils.printArray(s.nextPermutation(a1), a1.length);

        int[] a2 = new int[]{4, 3, 2, 1};
        Utils.printArray(s.nextPermutation(a2), a2.length);

        int[] a3 = new int[]{1, 2};
        Utils.printArray(s.nextPermutation(a3), a3.length);
    }
}
