package org.morewax.leetcode;

import org.morewax.support.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem
 * Count Smaller Numbers After itself (https://leetcode.com/problems/count-of-smaller-numbers-after-self/)
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property
 * where counts[i] is the number of smaller elements to the right of nums[i].

 Example:

 Given nums = [5, 2, 6, 1]

 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.
 Return the array [2, 1, 1, 0].

 * Created by Bing on 12/8/2015.
 */
public class CountSmallerNumbers {
    private static class Item {
        public int val;
        public int index;
        public int counter;

        public Item(int val, int index) {
            this.val = val;
            this.index = index;
            this.counter = 0;
        }
    }

    private void MergeSort(Item[] items, int start, int end) {
        if (start+1 == end) return;

        int mid = start + (end-start)/2;

        MergeSort(items, start, mid);
        MergeSort(items, mid, end);
        Combine(items, start, mid, end);
    }

    // Combine two sorted sections: [start, mid) and [mid, end)
    private void Combine(Item[] items, int start, int mid, int end) {
        Item[] left = new Item[mid-start+1];
        Item[] right = new Item[end-mid+1];

        for (int i = 0; i < mid-start; ++i) {
            left[i] = items[start+i];
        }

        for(int i = 0; i < end-mid; ++i) {
            right[i] = items[mid+i];
        }

        left[mid-start] = right[end-mid] = new Item(Math.max(items[mid-1].val, items[end-1].val)+1, 0);

        int i = 0;
        int j = 0;
        for (int k = start; k < end; ++k) {
            if (left[i].val <= right[j].val) {
                items[k] = left[i];
                // this following code needs some explanations.
                // the reasoning is like this.
                // the loop invariant
                // before comparing left[i] and right[j], all right[0...j-1] are less than left[i]
                // in other words right[j] is the first element of right[0...end-mid] that is larger or equal to left[i].
                // Thus the number of elements, which are less than left[i] is j.
                items[k].counter += j;
                ++i;
            } else {
                items[k] = right[j];
                ++j;
            }
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        Item[] items = new Item[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            items[i] = new Item(nums[i], i);
        }

        MergeSort(items, 0, items.length);

        Integer[] counters = new Integer[items.length];
        for (int i = 0; i < items.length; ++i) {
            counters[items[i].index] = items[i].counter;
        }

        return Arrays.asList(counters);
    }

    public static void main(String[] args) {
        CountSmallerNumbers s = new CountSmallerNumbers();

        int[] A1 = new int[]{5, 2, 6, 1};
        Utils.print(s.countSmaller(A1));
    }
}
