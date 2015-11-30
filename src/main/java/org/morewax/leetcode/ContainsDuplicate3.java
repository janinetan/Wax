package org.morewax.leetcode;

/**
 * Created by Bing on 11/28/2015.
 */

import java.util.TreeSet;

/**
 * Problem
 * Contains Duplicate III (https://leetcode.com/problems/contains-duplicate-iii/)
 * Given an array of integers, find out whether there are two distinct indices i and j in the array
 * such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
 */
public class ContainsDuplicate3 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;

        TreeSet<Integer> treeSet = new TreeSet<>();

        for(int i = 0; i < nums.length; i++) {
            int n = nums[i];
            Integer floor = treeSet.floor(n);
            Integer ceiling = treeSet.ceiling(n);

            if (floor != null && n <= t+floor.intValue() ||
                    ceiling != null && ceiling.intValue() <= t+n) {
                return true;
            }

            treeSet.add(n);

            if (i >= k) {
                treeSet.remove(nums[i-k]);
            }
        }

        return false;
    }
}