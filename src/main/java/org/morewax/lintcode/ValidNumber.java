package org.morewax.lintcode;

/**
 * Problem
 * Valid Number(http://www.lintcode.com/en/problem/valid-number/)
 * Validate if a given string is numeric.
 * Example
 * "0" => true

 " 0.1 " => true

 "abc" => false

 "1 a" => false

 "2e10" => true
 * Created by byuan on 2/12/16.
 */
public class ValidNumber {
    /**
     * @param s the string that represents a number
     * @return whether the string is a valid number
     */
    public boolean isNumber(String s) {
        // Write your code here
        boolean result = false;
        int i = 0;
        int n = s.length();

        // leading spaces
        while (i < n && s.charAt(i) == ' ') {
            ++i;
        }

        // handle symbol
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            ++i;
        }

        while (i < n && isDigit(s.charAt(i))) {
            result = true;
            ++i;
        }

        if (i < n && s.charAt(i) == '.') {
            // handle float
            ++i;

            while (i < n && isDigit(s.charAt(i))) {
                result = true;
                ++i;
            }
        }

        if (result && i < n && s.charAt(i) == 'e') {
            // handle scientific number;
            ++i;

            result = false;
            // handle symbol
            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                ++i;
            }

            while (i < n && isDigit(s.charAt(i))) {
                result = true;
                ++i;
            }
        }

        // handle trailing spaces
        while (i < n && s.charAt(i) == ' ') {
            ++i;
        }

        return result && i == n;
    }

    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }
}
