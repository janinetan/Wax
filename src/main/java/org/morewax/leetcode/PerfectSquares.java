package org.morewax.leetcode;

import java.util.Arrays;

/**
 * Problem
 * Perfect Squares (https://leetcode.com/problems/perfect-squares/)
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 *
 * Created by Bing on 12/14/2015.
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int res = n;
        int num = 2;

        while (num * num <= n) {
            int a = n / (num * num);
            int b = n % (num * num);
            res = Math.min(res, a + numSquares(b));
            ++num;
        }

        return res;
    }

    // This solution is equivalent to the above recursive one.
    // Since it's using DP, thus it could avoid unnecessary computations.
    // In fact the above solution could be optimized by using memoization.
    public int numSquaresDP(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i <= n; ++i) {
            for (int j = 1; i + j*j <=n; ++j) {
                dp[i+j*j] = Math.min(dp[i+j*j], dp[i]+1);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        final PerfectSquares s = new PerfectSquares();

        System.out.println(s.numSquares(12));
        System.out.println(s.numSquaresDP(12));
        System.out.println(s.numSquares(13));
        System.out.println(s.numSquaresDP(13));
    }
}
