package org.morewax.leetcode;

import java.util.Arrays;

/**
 * Problem
 * Coin Change (https://leetcode.com/problems/coin-change/)
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *  Example 1:
 *  coins = [1, 2, 5], amount = 11
 *  return 3 (11 = 5 + 5 + 1)
 *
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 *
 * Note:
 * You may assume that you have an infinite number of each kind of coin.


 * Created by byuan on 12/28/15.
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount+1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 0; i < coins.length; ++i) {
            int currentSum = 0;
            while (currentSum+coins[i] <= amount) {
                if (dp[currentSum] != Integer.MAX_VALUE) {
                    dp[currentSum+coins[i]] = Math.min(dp[currentSum+coins[i]], dp[currentSum]+1);
                }
                ++currentSum;
            }
        }
        /*
        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j < coins.length && coins[j] <= i; ++j) {
                if (dp[i-coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }*/

        return (dp[amount]==Integer.MAX_VALUE)? -1 : dp[amount];
    }

    public static void main(String[] args) {
        final CoinChange s = new CoinChange();

        int[] coins1 = new int[]{1, 2};
        System.out.println(s.coinChange(coins1, 3));

        int[] coins2 = new int[]{2};
        System.out.println(s.coinChange(coins2, 3));
    }
}
