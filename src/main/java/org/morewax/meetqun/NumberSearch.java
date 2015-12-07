package org.morewax.meetqun;

import org.morewax.support.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem
 * Number Search
 * Design a data structure that supports kind of full text search but in
 numbers.

 We are given file with lot of 10-digits numbers, for example:

 1234 567 890
 4124 123 123
 3123 123 322

 On a given number X we should return all numbers that contain X.

 For example, if the number 123 was given, we should return all numbers (from
 the list above) because 123 is in all of them.

 If the number 41 was given we should return only the middle number - because
 the number 41 is only in it.
 * Created by Bing on 12/6/2015.
 */
public class NumberSearch {
    public List<Long> search(long[] A, int target) {
        List<Long> results = new ArrayList<>();
        long x = 1;
        int counter = 0;

        while (x <= target) {
            x *= 10;
            ++counter;
        }

        for (int i = 0; i < A.length; ++i) {
            long y = A[i];
            for (int j = 10-counter; j >= 0; --j) {
                if (y/(long)Math.pow(10, j)%x == target) {
                    results.add(A[i]);
                    break;
                }
            }
        }

        return results;
    }

    public static void main(String[] args) {
        NumberSearch s = new NumberSearch();

        long[] A1 = new long[]{1234567890L, 4124123123L, 3123123322L};
        Utils.print(s.search(A1, 123));
        Utils.print(s.search(A1, 41));
        Utils.print(s.search(A1, 31));
    }
}
