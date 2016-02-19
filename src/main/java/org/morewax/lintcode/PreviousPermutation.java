/**
 * Problem
 * Previous Permutation (http://www.lintcode.com/en/problem/previous-permutation/)
 * Given a list of integers, which denote a permutation.
 *
 * Find the previous permutation in ascending order.
 * Example
 * For [1,3,2,3], the previous permutation is [1,2,3,3]
 * For [1,2,3,4], the previous permutation is [4,3,2,1]
 */
package org.morewax.lintcode;

import org.morewax.support.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by byuan on 2/17/16.
 */
public class PreviousPermutation {
    /**
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
        // write your code
        int k = nums.size()-1;

        while (k > 0) {
            if (nums.get(k-1) > nums.get(k)) {
                break;
            }
            --k;
        }

        if (k == 0) {
            Collections.reverse(nums);
        } else {
            int j = nums.size()-1;
            while (j >= k) {
                if (nums.get(j) < nums.get(k-1)) {
                    break;
                }
                --j;
            }

            int temp = nums.get(j);
            nums.set(j, nums.get(k-1));
            nums.set(k-1, temp);

            reverse(nums, k, nums.size()-1);
        }

        return nums;
    }

    private void reverse(ArrayList<Integer> nums, int start, int end) {
        while (start <= end) {
            int temp = nums.get(start);
            nums.set(start, nums.get(end));
            nums.set(end, temp);
            ++start;
            --end;
        }
    }

    public static void main(String[] args) {
        final PreviousPermutation s = new PreviousPermutation();

        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1, 3, 2, 3));
        Utils.print(s.previousPermuation(l1));

        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Utils.print(s.previousPermuation(l2));
    }
}
