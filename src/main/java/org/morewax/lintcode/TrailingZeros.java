package org.morewax.lintcode;

/**
 * Problem
 * Trailing Zeros (http://www.lintcode.com/en/problem/trailing-zeros/)
 * Write an algorithm which computes the number of trailing zeros in n factorial.
 * Example
 * 11! = 39916800, so the out should be 2
 *
 * * Created by Bing on 12/28/2015.
 */
public class TrailingZeros {
    /*
     * param n: As desciption
     * return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
        // write your code here
        long count = 0;
        long divider = 5;

        while (n/divider != 0) {
            count += n/divider;
            divider *= 5;
        }

        return count;
    }

    public static void main(String[] args) {
        final TrailingZeros s = new TrailingZeros();

        System.out.println(s.trailingZeros(11));
    }
}
