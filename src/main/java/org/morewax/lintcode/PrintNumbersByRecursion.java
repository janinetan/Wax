package org.morewax.lintcode;

import org.morewax.support.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem
 * Print Numbers by Recursion ()
 * Print numbers from 1 to the largest number with N digits by recursion.
 *  Example
 *   Given N = 1, return [1,2,3,4,5,6,7,8,9].
 *   Given N = 2, return [1,2,3,4,5,6,7,8,9,10,11,12,...,99].
 *
 * Created by byuan on 1/20/16.
 */
public class PrintNumbersByRecursion {
    /**
     * @param n: An integer.
     * return : An array storing 1 to the largest number with n digits.
     */
    public List<Integer> numbersByRecursion(int n) {
        // write your code here
        List<Integer> results = new ArrayList<>();
        if (n == 0) return results;
        List<Integer> prev = new ArrayList<>();
        prev.add(0);

        helper(prev, 1, n, results);

        return results;
    }

    private void helper(List<Integer> prev, int current, int totalDigits, List<Integer> results) {
        List<Integer> next = new ArrayList<>();
        for (Integer i : prev) {
            for (int j = 0; j <= 9; ++j) {
                int n = i*10+j;
                if (n != 0) {
                    if (current < totalDigits)
                        next.add(n);
                    results.add(i*10+j);
                }
            }
        }

        if (!next.isEmpty()) {
            helper(next, current + 1, totalDigits, results);
        }

    }

    public static void main(String[] args) {
        final PrintNumbersByRecursion s = new PrintNumbersByRecursion();

        Utils.print(s.numbersByRecursion(2));
        Utils.print(s.numbersByRecursion(3));
    }
}
