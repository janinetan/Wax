package org.morewax.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem
 * Combination Sum III (https://leetcode.com/problems/combination-sum-iii/)
 *
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

 * Ensure that numbers within the set are sorted in ascending order.

 * Example 1:

 * Input: k = 3, n = 7

 * Output:

 * [[1,2,4]]

 * Example 2:

 * Input: k = 3, n = 9

 * Output:

 * [[1,2,6], [1,3,5], [2,3,4]]
 * Created by Bing on 11/28/2015.
 */
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> oneResult = new ArrayList<>();

        helper(0, k, n, oneResult, results);

        return results;
    }

    public String printResults(List<List<Integer>> results) {
        final StringBuffer sb = new StringBuffer();

        sb.append("[");

        results.forEach(oneResult -> {
            sb.append("\n");
            sb.append("[");
            final int[] counter = {0};
            oneResult.forEach(i -> {
                if (counter[0]++ != 0) {
                    sb.append(", ");
                }

                sb.append(i);
            });
            sb.append("]");
        });
        sb.append("\n]");
        return sb.toString();
    }

    private void helper(int current, int end, int target, List<Integer> oneResult, List<List<Integer>> results) {
        if (oneResult.size() == end && target == 0) {
            results.add(new ArrayList<>(oneResult));
            return;
        }

        for (int i = current+1; i <= 9 && i <= target; ++i) {
            oneResult.add(i);
            helper(i, end, target-i, oneResult, results);
            oneResult.remove(oneResult.size()-1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> results;
        CombinationSum3 combinationSum3 = new CombinationSum3();

        results = combinationSum3.combinationSum3(3, 7);
        System.out.println(combinationSum3.printResults(results));

        results = combinationSum3.combinationSum3(3, 9);
        System.out.println(combinationSum3.printResults(results));
    }
}
