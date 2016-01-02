package org.morewax.leetcode;

import org.morewax.support.Utils;

import java.util.*;

/**
 * Problem
 * Wiggle Sort II (https://leetcode.com/problems/wiggle-sort-ii/)
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * Note:
 *  You may assume all input has valid answer.
 * Created by Bing on 12/30/2015.
 */
public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        Integer[] temp = new Integer[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            temp[i] = nums[i];
        }

        Arrays.sort(temp, Collections.reverseOrder());

        int j = 0;
        for (int i = 1; i < nums.length; i += 2) {
            nums[i] = temp[j++];
        }

        for (int i = 0; i < nums.length; i += 2) {
            nums[i] = temp[j++];
        }
    }

    // The O(N) solution
    public void wiggleSort2(int[] nums) {
        if(nums == null || nums.length == 0) return;

        double median = findMedian(nums);
        int firstHalfLen, secondHalfLen;
        if(nums.length % 2 == 0) {
            firstHalfLen = nums.length/2;
        } else {
            firstHalfLen = nums.length/2+1;
        }
        secondHalfLen = nums.length/2;

        List<Integer> firstHalf  = new ArrayList<Integer>();
        List<Integer> secondHalf = new ArrayList<Integer>();

        for(int i = 0; i < nums.length; i++) {
            if((double) nums[i] < median) firstHalf.add(nums[i]);
            else if((double) nums[i] > median) secondHalf.add(nums[i]);
        }

        while(firstHalf.size() < firstHalfLen) {
            firstHalf.add((int) median);
        }
        while(secondHalf.size() < secondHalfLen) {
            secondHalf.add((int) median);
        }

        for(int i = 0; i < firstHalf.size(); i++) {
            nums[i*2] = firstHalf.get(firstHalf.size()-1-i);
        }
        for(int i = 0; i < secondHalf.size(); i++) {
            nums[i*2+1] = secondHalf.get(i);
        }
    }

    private double findMedian(int[] nums) {
        if(nums.length % 2 == 1) return (double) findKth(nums, 0, nums.length-1, nums.length/2);
        else return ( (double) findKth(nums, 0, nums.length-1, nums.length/2 - 1) + (double) findKth(nums, 0, nums.length-1, nums.length/2) ) / 2;
    }

    private int findKth(int[] nums, int low, int high, int k) {
        Random rm = new Random();
        swap(nums, low, low + rm.nextInt(high - low + 1));
        int pivot = nums[low];
        int lb = low, hb = high, pt = low+1;;
        while(pt <= hb) {
            if(nums[pt] < pivot) swap(nums, lb++, pt++);
            else if(nums[pt] > pivot) swap(nums, pt, hb--);
            else pt++;
        }
        if(k < lb) return findKth(nums, low, lb-1, k);
        else if(k > hb) return findKth(nums, hb+1, high, k);
        else return pivot;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        final WiggleSortII s = new WiggleSortII();

        int[] a1 = new int[]{1, 5, 1, 1, 6, 4};
        s.wiggleSort(a1);
        Utils.printArray(a1, a1.length);

        int[] a2 = new int[]{1, 3, 2, 2, 3, 1};
        s.wiggleSort(a2);
        Utils.printArray(a2, a2.length);

        int[] a3 = new int[]{1,2,2,1,2,1,1,1,1,2,2,2};
        s.wiggleSort(a3);
        Utils.printArray(a3, a3.length);
    }
}
