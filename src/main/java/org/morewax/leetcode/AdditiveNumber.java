package org.morewax.leetcode;

/**
 * Problem
 * Additive Number (https://leetcode.com/problems/additive-number/)
 *
 * Additive number is a string whose digits can form additive sequence.

 A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

 For example:
 "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 1 + 99 = 100, 99 + 100 = 199
 Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

 Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

 Follow up:
 How would you handle overflow for very large input integers?

 * Created by Bing on 12/13/2015.
 */
public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        for (int i = 1; i < num.length(); ++i) {
            for (int j = i+1; j < num.length(); ++j) {
                String s1 = num.substring(0, i);
                String s2 = num.substring(i, j);

                if ((s1.length() > 1 && s1.charAt(0) == '0') || (s2.length() > 1 && s2.charAt(0) == '0')) {
                    continue;
                }

                long v1 = Long.valueOf(s1);
                long v2 = Long.valueOf(s2);
                long sum = v1 + v2;
                String current = s1 + s2 + String.valueOf(sum);

                while (current.length() < num.length()) {
                    v1 = v2;
                    v2 = sum;
                    sum = v1 + v2;
                    current += String.valueOf(sum);
                }

                if (current.equals(num)) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        final AdditiveNumber s = new AdditiveNumber();

        System.out.println(s.isAdditiveNumber("112358"));
    }
}
