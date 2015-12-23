package org.morewax.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Problem
 * Create Maximum Number ()
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length
 * k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return
 * an array of the k digits. You should try to optimize your time and space complexity.
 *
 * Example 1:
 *  nums1 = [3, 4, 6, 5]
 *  nums2 = [9, 1, 2, 5, 8, 3]
 *  k = 5
 *  return [9, 8, 6, 5, 3]
 *
 * Example 2:
 *  nums1 = [6, 7]
 *  nums2 = [6, 0, 4]
 *  k = 5
 *  return [6, 7, 6, 0, 4]
 *
 * Example 3:
 *  nums1 = [3, 9]
 *  nums2 = [8, 9]
 *  k = 3
 *  return [9, 8, 9]
 *
 * Created by byuan on 12/23/15.
 */
public class CreateMaxNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        Comparator<int[]> MyCompare = (a, b) -> {
            int len = a.length;
            for(int i = 0; i < len; ++i) {
                if(a[i]!=b[i]){
                    return b[i]-a[i];
                }
            }

            return 0;
        };

        // create a max heap
        Queue<int[]> queue = new PriorityQueue<>(MyCompare);

        for(int i = Math.max(k-n, 0); i<= Math.min(k,m); ++i) {
            ArrayList<Integer> val1 = helper(nums1, i);
            ArrayList<Integer> val2 = helper(nums2, k-i);

            int[] res;
            res = getMax(val1, val2);

            queue.add(res);
        }

        return queue.poll();
    }

    // merge two lists of selected numbers
    private int[] getMax(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        int m = arr1.size();
        int n = arr2.size();
        int[] res = new int[m+n];
        int ind1 = 0;
        int ind2 = 0;
        int count = 0;

        while(ind1 < m && ind2 < n) {
            if(arr1.get(ind1) > arr2.get(ind2)) {
                res[count++] = arr1.get(ind1);
                ++ind1;
            } else if(arr1.get(ind1) < arr2.get(ind2)) {
                res[count++]=arr2.get(ind2);
                ++ind2;
            } else {//arr1.get(ind1)==arr2.get(ind2)
                int pattern = getPatLen(arr1, arr2, ind1, ind2);

                if((ind1+pattern) < m && (ind2+pattern) < n) {
                    if(arr1.get(ind1+pattern) > arr2.get(ind2+pattern)) {
                        res[count++] = arr1.get(ind1);
                        ++ind1;
                    } else {
                        res[count++] = arr2.get(ind2);
                        ++ind2;
                    }
                } else {
                    if((ind1+pattern) < m) {
                        res[count++] = arr1.get(ind1);
                        ++ind1;
                    }
                    else if((ind2+pattern) < n) {
                        res[count++] = arr2.get(ind2);
                        ++ind2;
                    } else {
                        res[count++]=arr1.get(ind1);
                        ++ind1;
                    }
                }
            }
        }

        while(ind1 < m) {
            res[count++] = arr1.get(ind1);
            ++ind1;
        }

        while(ind2 < n) {
            res[count++] = arr2.get(ind2);
            ++ind2;
        }

        return res;
    }

    // select "len" number of maximal numbers from "nums"
    private ArrayList<Integer> helper(int[] nums, int len) {
        ArrayList<Integer> res = new ArrayList<>();
        int start = 0;
        int end = nums.length-len;
        int count =0;

        while(count < len) {
            int max = nums[start];
            int ind = start;
            for(int i = start+1; i <= end; ++i) {
                if(nums[i] > max) {
                    max = nums[i];
                    ind = i;
                }
            }
            res.add(max);
            start = ind+1;
            end = end+1;
            ++count;
        }

        return res;
    }

    private int getPatLen(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int ind1, int ind2) {
        int res = 0;

        while(ind1 < arr1.size() && ind2 < arr2.size()) {
            if(arr1.get(ind1) != arr2.get(ind2)){
                break;
            }

            ++res;
            ++ind1;
            ++ind2;
        }

        return res;
    }
}
