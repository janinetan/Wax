package org.morewax.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem
 * 3Sum (https://leetcode.com/problems/3sum/)
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note:
 Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 The solution set must not contain duplicate triplets.
 For example, given array S = {-1 0 1 2 -1 -4},

 A solution set is:
 (-1, 0, 1)
 (-1, -1, 2)
 * Created by Bing on 11/27/2015.
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        if (nums.length > 0) {
            Arrays.sort(nums);
        }

        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            int a = nums[i];
            int target = -a;
            int j = i+1;
            int k = n-1;

            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum < target) {
                    ++j;
                } else if (sum > target) {
                    --k;
                } else {
                    List<Integer> result = new ArrayList<>();
                    result.add(a);
                    result.add(nums[j]);
                    result.add(nums[k]);
                    results.add(result);
                    ++j;
                    --k;

                    while (nums[j-1] == nums[j] && j < k) {
                        ++j;
                    }

                    while (nums[k] == nums[k+1] && j < k) {
                        --k;
                    }
                }
            }

            while (i < n-1 && nums[i] == nums[i+1]) {
                ++i;
            }
        }

        return results;
    }
}
