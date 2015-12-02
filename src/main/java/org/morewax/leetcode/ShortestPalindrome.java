package org.morewax.leetcode;

/**
 * Problem
 * Shortest Palindrome (https://leetcode.com/problems/shortest-palindrome/)
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

 For example:

 Given "aacecaaa", return "aaacecaaa".

 Given "abcd", return "dcbabcd".
 * Created by byuan on 12/1/15.
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if (s.isEmpty()) return "";

        int len = s.length();

        String reversed = reverseString(s);
        String combined = s + "#" + reversed;

        int[] next = getNext(combined);

        return reversed.substring(0, len-next[2*len]) + s;
    }

    private String reverseString(String s) {
        StringBuffer sb = new StringBuffer(s);
        return sb.reverse().toString();
    }

    private int[] getNext(String s) {
        // unlike the traditional next array in KMP, here
        // next records the LENGTH of common prefix/suffix characters of s
        int[] next = new int[s.length()];

        for (int i = 1; i < s.length(); ++i) {
            int j = next[i - 1];

            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }

            j += (s.charAt(i) == s.charAt(j))? 1 : 0;
            next[i] = j;
        }

        return next;
    }

    public static void main(String[] args) {
        ShortestPalindrome shortestPalindrome = new ShortestPalindrome();

        String s;
        String result;

        s = "abcd";
        result = shortestPalindrome.shortestPalindrome(s);
        System.out.println("Shortest palindrome for " + s + " is " + result);

        s = "aacecaaa";
        result = shortestPalindrome.shortestPalindrome(s);
        System.out.println("Shortest palindrome for " + s + " is " + result);

        s = "aaaa";
        result = shortestPalindrome.shortestPalindrome(s);
        System.out.println("Shortest palindrome for " + s + " is " + result);
    }
}
