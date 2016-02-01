package org.morewax.lintcode;

import java.util.ArrayList;

/**
 * Problem
 * Maximum Gap (http://www.lintcode.com/en/problem/maximum-gap/)
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Return 0 if the array contains less than 2 elements.
 * Example
 *  Given [1, 9, 2, 5], the sorted form of it is [1, 2, 5, 9], the maximum gap is between 5 and 9 = 4.
 * Note
 *  You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * Created by byuan on 1/27/16.
 */
public class MaximumGap {
    /**
     * @param nums: an array of integers
     * @return: the maximum difference
     */
    public int maximumGap(int[] nums) {
        // write your code here
        int n = nums.length;
        if (n < 2) return 0;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }

        if (max == min) return 0;

        int gap = (int)Math.ceil((max-min)*1.0/(n-1));
        int numOfBuckets = (max-min)/gap+1;

        ArrayList<Integer>[] buckets = new ArrayList[numOfBuckets];

        for (int num : nums) {
            int index = (num-min)/gap;
            if (buckets[index] == null) {
                buckets[index] = new ArrayList<>();
            }

            if (buckets[index].size() == 0) {
                buckets[index].add(num);
                buckets[index].add(num);
            } else {
                buckets[index].set(0, Math.min(buckets[index].get(0), num));
                buckets[index].set(1, Math.max(buckets[index].get(1), num));
            }
        }

        int result = 0;
        int previousBucket = -1;

        for (int i = 0; i < buckets.length; ++i) {
            if (buckets[i] == null || buckets[i].size() == 0) continue;
            result = Math.max(result, buckets[i].get(1)-buckets[i].get(0));
            if (previousBucket != -1) {
                result = Math.max(result, buckets[i].get(0) - buckets[previousBucket].get(1));
            }
            previousBucket = i;
        }

        return result;
    }

    public static void main(String[] args) {
        final MaximumGap s = new MaximumGap();

        System.out.println(s.maximumGap(new int[]{1, 9, 2 , 5}));
    }
}
