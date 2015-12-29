package org.morewax.lintcode;

/**
 * Problem
 * Unique Binary Trees(http://www.lintcode.com/en/problem/unique-binary-search-trees/)
 * Given n, how many structurally unique BSTs (binary search trees) that store values 1...n?
 * Example
 * Given n = 3, there are a total of 5 unique BST's.
 *
 1           3    3       2      1
 \         /    /       / \      \
 3      2     1       1   3      2
 /      /       \                  \
 2     1          2                  3

 * Created by Bing on 12/28/2015.
 */
public class UniqueBinaryTrees {
    /**
     * @paramn n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        // write your code here
        if (n == 0) return 1;

        int[] dp = new int[n+1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                dp[i] += dp[j]*dp[i-j-1];
            }
        }

        return dp[n];
    }
}
