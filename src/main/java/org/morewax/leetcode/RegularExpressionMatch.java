package org.morewax.leetcode;

/**
 * Problem
 * Regular Expression Match (https://leetcode.com/problems/regular-expression-matching/)
 * Implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "a*") → true
 isMatch("aa", ".*") → true
 isMatch("ab", ".*") → true
 isMatch("aab", "c*a*b") → true
 * Created by Bing on 12/1/2015.
 */
public class RegularExpressionMatch {
    public boolean isMatch(String s, String p) {
        if (p.equals("*")) return false; // '*' cannot be the first in the regex.
        if (p.isEmpty()) return s.isEmpty();

        if (p.length() >=2 && p.charAt(1) == '*') {
            for (int i = 0; i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.'); ++i) { // this loop tries to match 1 or more leading characters before '*' in p.
                if (isMatch(s.substring(i + 1), p.substring(2))) {
                    return true;
                }
            }

            return isMatch(s, p.substring(2)); // tries to match the rest of P after '*'. This means 0 leading character (before '*')
        }

        return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
    }

    // Analysis of state transition (http://bangbingsyb.blogspot.com/2014/11/leetcode-regular-expression-matching.html)
    public boolean isMatchDP(String s, String p) {
        // the state dp[i][j] stands for the matching result of s[0:i-1] (length i) and p[0:j-1] (length j)
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;

        for (int i = 0; i <= s.length(); ++i) {
            for (int j = 1; j <= p.length(); ++j) {
                if (p.charAt(j-1) != '.' && p.charAt(j-1) != '*') {
                   if (i > 0 && s.charAt(i-1) == p.charAt(j-1) && dp[i-1][j-1]) {
                       dp[i][j] = true;
                   }
                } else if (p.charAt(j-1) == '.') {
                    if (i > 0 && dp[i-1][j-1]) {
                        dp[i][j] = true;
                    }
                } else {
                    if (j > 1) { // '*' cannot be the first element
                        if (dp[i][j-2] /* '*' means 0 p[j-2] character */ || dp[i][j-1] /* '*' means 1 p[j-2] character */) {
                            dp[i][j] = true;
                        } else if (i > 0 && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') && dp[i-1][j]) { // '*' means more than 2 or more p[j-2] characters
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        RegularExpressionMatch regularExpressionMatch = new RegularExpressionMatch();
        String s;
        String p;
        boolean result;

        s = "aa";
        p = "a";
        result = regularExpressionMatch.isMatch(s, p);
        System.out.println(result);
        result = regularExpressionMatch.isMatchDP(s, p);
        System.out.println("DP: " + result);
        System.out.println("");

        s = "aa";
        p = "aa";
        result = regularExpressionMatch.isMatch(s, p);
        System.out.println(result);
        result = regularExpressionMatch.isMatchDP(s, p);
        System.out.println("DP: " + result);
        System.out.println("");

        s = "aaa";
        p = "aa";
        result = regularExpressionMatch.isMatch(s, p);
        System.out.println(result);
        result = regularExpressionMatch.isMatchDP(s, p);
        System.out.println("DP: " + result);
        System.out.println("");

        s = "aa";
        p = "a*";
        result = regularExpressionMatch.isMatch(s, p);
        System.out.println(result);
        result = regularExpressionMatch.isMatchDP(s, p);
        System.out.println("DP: " + result);
        System.out.println("");

        s = "aa";
        p = ".*";
        result = regularExpressionMatch.isMatch(s, p);
        System.out.println(result);
        result = regularExpressionMatch.isMatchDP(s, p);
        System.out.println("DP: " + result);
        System.out.println("");

        s = "ab";
        p = ".*";
        result = regularExpressionMatch.isMatch(s, p);
        System.out.println(result);
        result = regularExpressionMatch.isMatchDP(s, p);
        System.out.println("DP: " + result);
        System.out.println("");

        s = "aab";
        p = "c*a*b";
        result = regularExpressionMatch.isMatch(s, p);
        System.out.println(result);
        result = regularExpressionMatch.isMatchDP(s, p);
        System.out.println("DP: " + result);
    }
}
