package org.morewax.lintcode;

/**
 * Problem
 * Coins in a line II (http://www.lintcode.com/en/problem/coins-in-a-line-ii/)
 * There are n coins with different value in a line. Two players take turns to take one or two coins from left side
 * until there are no more coins left. The player who take the coins with the most value wins.
 * Could you please decide the first player will win or lose?
 * Example
 * Given values array A = [1,2,2], return true.
 * Given A = [1,2,4], return false.
 * Created by Bing on 2/6/2016.
 */
public class CoinsInALineII {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        if (values == null || values.length <= 2) {
            return true;
        }
        int second = values[values.length - 1];
        int first = values[values.length - 2] + second;
        int sum = first;
        for(int i = values.length - 3; i >= 0; i--){
            sum += values[i];
            int cur = sum - Math.min(second, first);
            second = first;
            first = cur;
        }

        return first > sum - first;
    }

    public static void main(String[] args) {
        final CoinsInALineII s = new CoinsInALineII();

        System.out.println("Will first player win: " + s.firstWillWin(new int[]{1, 2, 2}));
        System.out.println("Will first player win: " + s.firstWillWin(new int[]{1, 2, 4}));
    }
}
