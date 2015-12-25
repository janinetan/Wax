package org.morewax.lintcode;

import org.morewax.support.Utils;

import java.util.ArrayList;

/**
 * Problem
 * Product of Array Exclude itself (http://www.lintcode.com/en/problem/product-of-array-exclude-itself/)
 * Given an integers array A.
 *  Define B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1], calculate B WITHOUT divide operation.
 *  Example
 *  For A = [1, 2, 3], return [6, 3, 2].
 *
 * Created by Bing on 12/24/2015.
 */
public class ProductOfArray {
    /**
     * @param A: Given an integers array A
     * @return: A Long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
        // write your code
        if (A.isEmpty()) return new ArrayList<>();

        ArrayList<Long> result = new ArrayList<>();
        A.forEach(i -> result.add(1L)); // java is so freaking silly. C++ shines here.

        for (int i = 1; i < A.size(); ++i) {
            result.set(i, result.get(i-1)*(long)A.get(i-1));
        }

        long right = A.get(A.size()-1);
        for (int i = A.size()-2; i >= 0; --i) {
            result.set(i, result.get(i)*right);
            right *= A.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        final ProductOfArray s = new ProductOfArray();

        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(0);
        Utils.print(s.productExcludeItself(a1));
    }
}
