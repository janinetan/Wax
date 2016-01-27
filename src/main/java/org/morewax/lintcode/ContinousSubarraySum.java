package org.morewax.lintcode;

import org.morewax.support.Utils;

import java.util.ArrayList;

/**
 * Problem
 * Continous Subarray Sum (http://www.lintcode.com/en/problem/continuous-subarray-sum/)
 * Given an integer array, find a continuous subarray where the sum of numbers is the biggest.
 * Your code should return the index of the first number and the index of the last number. (If their are duplicate answer, return anyone)
 * Example
 * Give [-3, 1, 3, -3, 4], return [1,4].
 * Created by byuan on 1/26/16.
 */
public class ContinousSubarraySum {
    /**
     * @param A an integer array
     * @return  A list of integers includes the index of the first number and the index of the last number
     */
    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        // Write your code here
        ArrayList<Integer> result = new ArrayList<>();

        if (A != null && A.length != 0) {
            int maxSum = Integer.MIN_VALUE;
            int sumSoFar = Integer.MIN_VALUE;
            int start = 0;
            int end = 0;
            int first = 0;

            for (int i = 0; i < A.length; ++i) {
                if (sumSoFar < 0) {
                    sumSoFar = 0;
                    first = i;
                }
                sumSoFar += A[i];

                if (sumSoFar > maxSum) {
                    maxSum = sumSoFar;
                    start = first;
                    end = i;
                }
            }

            result.add(start);
            result.add(end);
        }

        return result;
    }

    public static void main(String[] args) {
        final ContinousSubarraySum s = new ContinousSubarraySum();

        Utils.print(s.continuousSubarraySum(new int[]{-3, 1, 3, -3, 4}));
    }
}
