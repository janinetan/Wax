package org.morewax.meetqun;

import org.apache.commons.lang3.tuple.Pair;
import org.morewax.support.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem
 * 2 sum (diff by K)
 *  Given an array of positive, unique, increasingly sorted numbers A, e.g. A = [1, 2, 3, 5, 6, 8, 9, 11, 12, 13].
 *  Given a positive value K, e.g. K = 3. Output all pairs in A that differ exactly by K.
 *  e.g.
 *  2, 5
 *  3, 6
 *  5, 8
 *  6, 9
 *  8, 11
 *  9, 12
 *  What's the time complexity?
 *
 * Created by Bing on 12/6/2015.
 */
public class TwoSumByK {
    public List<Pair<Integer, Integer>> findAllPairs(int[] A, int K) {
        List<Pair<Integer, Integer>> results = new ArrayList<>();

        if (A.length >= 2) {
            int start = 0;
            int end = 1;
            int n = A.length;

            while (end < n) {
                int diff = A[end] - A[start];
                if (diff == K) {
                    results.add(Pair.of(A[start], A[end]));
                    ++start;
                    ++end;
                } else if (diff < K) {
                    ++end;
                } else {
                    ++start;
                }
            }

        }

        return results;
    }

    public static void main(String[] args) {
        TwoSumByK s = new TwoSumByK();

        int[] A1 = new int[]{1, 2, 3, 5, 6, 8, 9, 11, 12, 13};
        Utils.print(s.findAllPairs(A1, 3));
    }
}
