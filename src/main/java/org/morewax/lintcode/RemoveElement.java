package org.morewax.lintcode;

/**
 * Problem
 * Remove Element ()
 * Given an array and a value, remove all occurrences of that value in place and return the new length.
 * The order of elements can be changed, and the elements after the new length don't matter.
 * Example
 * Given an array [0,4,4,0,0,2,4,4], value=4

 * return 4 and front four elements of the array is [0,0,0,2]
 *
 * Created by Bing on 12/24/2015.
 */
public class RemoveElement {
    /**
     *@param A: A list of integers
     *@param elem: An integer
     *@return: The new length after remove
     */
    public int removeElement(int[] A, int elem) {
        // write your code here
        if (A == null || A.length == 0) return 0;

        int slow = 0;
        int fast = 0;

        while (fast < A.length) {
            if (A[fast] != elem) {
                A[slow++] = A[fast];
            }
            ++fast;
        }

        return slow;
    }

    public int removeElement2(int[] A, int elem) {
        if (A == null || A.length == 0) return 0;

        int n = A.length;
        // [0, notEqual] A[j] != elem
        // [notEqual+1, i-1) A[j] == elem
        // [i, n) unknown
        int notEqual = -1;

        for (int i = 0; i < n; ++i) {
            if (A[i] != elem) {
                ++notEqual;
                int temp = A[i];
                A[i] = A[notEqual];
                A[notEqual] = temp;
            }
        }

        return notEqual+1;
    }

    public void print(int[] A, int count) {
        System.out.print("[");
        for (int i = 0; i < count; ++i) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(A[i]);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        final RemoveElement s = new RemoveElement();

        int[] A1 = new int[]{0,4,4,0,0,2,4,4};
        //s.print(A1, s.removeElement(A1, 4));
        s.print(A1, s.removeElement2(A1, 4));
    }
}
