package org.morewax.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Problem
 * Subarray Sum Closest to Zero (http://www.lintcode.com/en/problem/subarray-sum-closest/)
 * Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.
 * Example
 *  Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].
 *
 * Created by Bing on 12/25/2015.
 */
public class SubarraySumClosest {
    private static class Item {
        public int partialSum;
        public int endIndex;

        public Item() {
            this.partialSum = 0;
            this.endIndex = 0;
        }

        public Item(int partialSum, int endIndex) {
            this.partialSum = partialSum;
            this.endIndex = endIndex;
        }
    }

    private static class ItemComparator implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.partialSum - o2.partialSum;
        }
    }

    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) return null;

        int n = nums.length;
        if (n == 1) return new int[2];

        List<Item> partialSums = new ArrayList<>();
        partialSums.add(new Item(0, -1));

        for (int i = 0; i < n; ++i) {
            Item item = new Item();
            item.endIndex = i;
            item.partialSum = partialSums.get(i).partialSum + nums[i];
            partialSums.add(item);
        }

        Collections.sort(partialSums, new ItemComparator());

        int start = 0;
        int end = 0;
        int best = Integer.MAX_VALUE;

        for (int i = 0; i < n; ++i) {
            int diff = Math.abs(partialSums.get(i+1).partialSum - partialSums.get(i).partialSum);
            if (diff < best) {
                best = diff;
                start = Math.min(partialSums.get(i+1).endIndex, partialSums.get(i).endIndex)+1;
                end = Math.max(partialSums.get(i+1).endIndex, partialSums.get(i).endIndex);
            }
        }

        int[] result = new int[2];
        result[0] = start;
        result[1] = end;

        return result;
    }

    public static void main(String[] args) {
        final SubarraySumClosest s = new SubarraySumClosest();
        int[] result = null;

        int[] A1 = new int[]{-3, 1, 1, -3, 5};
        result = s.subarraySumClosest(A1);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
    }
}
