package org.morewax.mitbbs;

import org.morewax.support.Utils;

import java.util.Arrays;

/**
 * Problem
 * Find pair of numbers that the difference between them is closest to the given target
 * Allegedly it's a on-site question asked at G.
 * Created by byuan on 1/29/16.
 */
public class ClosestDiffToTarget {
    public int[] solve(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;

        if (nums == null || nums.length < 2) return result;

        int diff = Integer.MAX_VALUE;
        int i = 0;
        int j = 1;
        Arrays.sort(nums);

        while (j < nums.length) {
            int temp = Math.abs(nums[j]-nums[i]-target);
            if (temp == 0) {
                result[0] = i;
                result[1] = j;
                break;
            }

            if (temp < diff) {
                result[0] = i;
                result[1] = j;
                diff = temp;
            }

            if (nums[j]-nums[i] > target) {
                ++i;
                if (i == j) {
                    ++j;
                }
            } else {
                ++j;
            }
        }

        result[0] = nums[result[0]];
        result[1] = nums[result[1]];
        return result;
    }

    public int[] comparison(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;

        if (nums == null || nums.length < 2) return result;

        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i+1; j < nums.length; ++j) {
                int temp = Math.abs(Math.abs(nums[j]-nums[i])-target);
                if (temp < diff) {
                    diff = temp;
                    result[0] = i;
                    result[1] = j;
                }
            }
        }

        result[0] = nums[result[0]];
        result[1] = nums[result[1]];

        return result;
    }

    public static void main(String[] args) {
        final ClosestDiffToTarget s = new ClosestDiffToTarget();

        Utils.printArray(s.solve(new int[]{-10, 0, 8, -32, -2, 18, 5}, 8), -1);
        Utils.printArray(s.comparison(new int[]{-10, 0, 8, -32, -2, 18, 5}, 8), -1);
    }
}
