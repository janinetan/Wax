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

    // The O(N) + O(N) space solution
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

    // O(N) + O(1) space solution
    // Explanation: https://leetcode.com/discuss/77133/o-n-o-1-after-median-virtual-indexing
    public void wiggleSort3(int[] nums) {
        int n = nums.length;

        int median = (int)findMedian(nums);

        int i = 0;
        int j = 0;
        int k = n-1;

        while (j <= k) {
            int virtualIndex = getVirtualIndex(j, n);
            if (nums[virtualIndex] > median) {
                swap(nums, getVirtualIndex(i++, n), getVirtualIndex(j++, n));
            } else if (nums[virtualIndex] < median) {
                swap(nums, getVirtualIndex(j, n), getVirtualIndex(k--, n));
            } else {
                ++j;
            }
        }
    }

    private int getVirtualIndex(int i, int n) {
        return (1+2*i)%((n|1));
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

        int[] a11 = new int[]{1, 5, 1, 1, 6, 4};
        s.wiggleSort(a11);
        Utils.printArray(a11, a11.length);

        int[] a12 = new int[]{1, 5, 1, 1, 6, 4};
        s.wiggleSort2(a12);
        Utils.printArray(a12, a12.length);

        int[] a13 = new int[]{1, 5, 1, 1, 6, 4};
        s.wiggleSort3(a13);
        Utils.printArray(a13, a13.length);


        int[] a21 = new int[]{1, 3, 2, 2, 3, 1};
        s.wiggleSort(a21);
        Utils.printArray(a21, a21.length);

        int[] a22 = new int[]{1, 3, 2, 2, 3, 1};
        s.wiggleSort2(a22);
        Utils.printArray(a22, a22.length);

        int[] a23 = new int[]{1, 3, 2, 2, 3, 1};
        s.wiggleSort3(a23);
        Utils.printArray(a23, a23.length);

        int[] a31 = new int[]{1,2,2,1,2,1,1,1,1,2,2,2};
        s.wiggleSort2(a31);
        Utils.printArray(a31, a31.length);

        int[] a32 = new int[]{1,2,2,1,2,1,1,1,1,2,2,2};
        s.wiggleSort2(a32);
        Utils.printArray(a32, a32.length);

        int[] a33 = new int[]{1,2,2,1,2,1,1,1,1,2,2,2};
        s.wiggleSort3(a33);
        Utils.printArray(a33, a33.length);
    }
}
