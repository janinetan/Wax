package org.morewax.lintcode;

/**
 * Problem
 * String to Integer II (http://www.lintcode.com/en/problem/string-to-integer-ii/)
 * Implement function atoi to convert a string to an integer.
 * If no valid conversion could be performed, a zero value is returned.
 * If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 *  Example
 *  "10" => 10
 *  "-1" => -1
 *  "123123123123123" => 2147483647
 *  "1.0" => 1
 *
 * Created by Bing on 12/24/2015.
 */
public class StringToInteger2 {
    /**
     * @param str: A string
     * @return An integer
     */
    public int atoi(String str) {
        // write your code here
        if (str == null) return 0;

        str = str.trim();
        if (str.length() == 0) return 0;

        int sign = 1;
        int index = 0;

        if (str.charAt(0) == '-') {
            sign = -1;
            ++index;
        } else if (str.charAt(0) == '+') {
            ++index;
        }

        long result = 0;
        while (index < str.length()) {
            char c = str.charAt(index);
            if (c > '9' || c < '0') break;

            result = result*10 + c - '0';

            if (result > Integer.MAX_VALUE) {
                break;
            }

            ++index;
        }

        if (result*sign >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if (result*sign <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int)result*sign;
    }
}
