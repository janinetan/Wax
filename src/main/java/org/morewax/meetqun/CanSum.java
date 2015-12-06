package org.morewax.meetqun;

/**
 * Problem
 * Given an array of positive integers (excluding zero) and a target number. Detect whether there is a set of consecutive elements in the array that add up to the target.

 Example: a = {1, 3, 5, 7, 9}
 target = 8

 output = true ({3, 5})

 or target = 15
 output = true : {3, 5, 8}

 but if target = 6, output would be false. since 1 and 5 are not next to each other.
 * Created by Bing on 12/5/2015.
 */
public class CanSum {
    public boolean canSum(int[] A, int target) {
        int currentSum = 0;
        int start = 0;
        int end = 0;

        while (end < A.length) {
            currentSum += A[end];
            if (currentSum == target) {
                return true;
            } else if (currentSum < target) {
                ++end;
            } else { // currentSum > target
                while (start <= end && currentSum > target) {
                    currentSum -= A[start];
                    ++start;
                }
                if (currentSum == target) {
                    return true;
                }
                ++end;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CanSum s = new CanSum();

        int[] a1 = new int[]{1, 3, 5, 7, 9};
        System.out.println(s.canSum(a1, 8));
        System.out.println(s.canSum(a1, 7));
        System.out.println(s.canSum(a1, 9));
        System.out.println(s.canSum(a1, 6));
        System.out.println(s.canSum(a1, 15));

        int[] a2 = new int[]{3, 10, 8};
        System.out.println(s.canSum(a2, 21));
    }
}
