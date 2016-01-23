package org.morewax.meetqun;

import org.morewax.support.Utils;

import java.util.Arrays;

/**
 * Problem
 * 一个sort的数组 通过f(x) = ax^2+bx+c之后再输出一个有序数组
 * Source: http://www.meetqun.com/thread-13249-1-1.html
 * Created by byuan on 1/22/16.
 */
public class GetSortedOutput {
    public int[] solve(int[] nums, int a , int b, int c) {
        if (nums == null || nums.length == 0) return null;

        int n = nums.length;
        int[] result = new int[n];

        if (a == 0) {
            if (b < 0) {
                for (int i = n-1; i >= 0; --i) {
                    result[n-1-i] = b*nums[i]+c;
                }
            } else {
                for (int i = 0; i < n; ++i) {
                    result[i] = b*nums[i]+c;
                }
            }
        }

        int peek = (int)Math.ceil((double)-b/(((double)2*a)));
        int pivotIndex = lowerBound(nums, peek);

        int[] left = calculateResults(nums, a, b, c, 0, pivotIndex-1, (a>0)? true : false);
        int[] right = calculateResults(nums, a, b, c, pivotIndex, n-1, (a>0)? false : true);

        merge(left, right, result);

        return result;
    }

    public int[] comparisonSolve(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length == 0) return null;

        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; ++i) {
            result[i] = a*nums[i]*nums[i] + b*nums[i] + c;
        }

        Arrays.sort(result);

        return result;
    }

    // this is the equivalent of STL::lower_bound function.
    // To my surprise, this is none in Java for such IMPORTANT function
    private int lowerBound(int[] nums, int x) {
        int l = -1;
        int h = nums.length;

        while (l+1 < h) {
            int m = l + (h-l)/2;
            if (nums[m] < x) {
                l = m;
            } else {
                h = m;
            }
        }

        return h;
    }

    private int[] calculateResults(int[] nums, int a, int b, int c, int start, int end, boolean isReversed) {
        if (start > end) return null;
        int[] result = new int[end-start+1];

        if (isReversed) {
            for (int i = end; i >= start; --i) {
                result[end-i] = a*nums[i]*nums[i] + b*nums[i] + c;
            }
        } else {
            for (int i = start; i <= end; ++i) {
                result[i-start] = a*nums[i]*nums[i] + b*nums[i] + c;
            }
        }

        return result;
    }

    private void merge(int[] left, int[] right, int[] result) {
        if (left == null) {
            for (int i = 0; i < right.length; ++i) {
                result[i] = right[i];
            }
        } else if (right == null) {
            for (int i = 0; i < left.length; ++i) {
                result[i] = left[i];
            }
        } else {
            int i = 0;
            int j = 0;
            int k = 0;

            while (k < result.length && i < left.length && j < right.length) {
                if (left[i] < right[j]) {
                    result[k++] = left[i++];
                } else {
                    result[k++] = right[j++];
                }
            }

            while (i < left.length) {
                result[k++] = left[i++];
            }

            while (j < right.length) {
                result[k++] = right[j++];
            }
        }
    }

    public static void main(String[] args) {
        final GetSortedOutput s = new GetSortedOutput();

        int[] a1 = new int[]{-10, -7, 0, 2, 17, 58};
        int[] r1 = s.solve(a1, 3, -17, 10);
        int[] r2 = s.comparisonSolve(a1, 3, -17, 10);
        Utils.printArray(r1, -1);
        Utils.printArray(r2, -1);

        int[] a2 = new int[]{-10, -7, 0, 2, 17, 58};
        int[] r3 = s.solve(a2, -3, 17, 10);
        int[] r4 = s.comparisonSolve(a2, -3, 17, 10);
        Utils.printArray(r3, -1);
        Utils.printArray(r4, -1);
    }
}
