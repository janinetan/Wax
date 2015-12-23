package org.morewax.leetcode;

/**
 * Problem
 * Integer to English (https://leetcode.com/problems/integer-to-english-words/)
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

 For example,
 123 -> "One Hundred Twenty Three"
 12345 -> "Twelve Thousand Three Hundred Forty Five"
 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 Hint:

 Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
 Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
 There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)

 * Created by byuan on 12/22/15.
 */
public class IntegerToEnglish {
    private static String[] digits = new String[]{
            "zero",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"
    };

    private static String[] decimals = new String[] {
            "",
            "",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
    };

    private static String[] units = new String[] {
            "",
            "Thousand",
            "Million",
            "Billion"
    };

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String result = "";
        int unit = 0;

        while (num > 0) {
            if ((num%1000) != 0) {
                result = convertHelper(num % 1000) + ((unit != 0)? " " : "") + units[unit] + ((result.length() > 0)? " " : "") + result;
            }
            ++unit;
            num /= 1000;
        }

        return result;
    }

    private String convertHelper(int num) {
        StringBuffer sb = new StringBuffer();

        int result = num/100;
        if (result != 0) {
            sb.append(digits[result]).append(" Hundred");
        }

        num %= 100;
        if (num != 0) {
            result = num/10;

            if (result == 1) {
                if (sb.length() != 0) {
                    sb.append(" ");
                }
                sb.append(digits[num]);
            } else {
                if (result != 0 && sb.length() != 0) {
                    sb.append(" ");
                }
                sb.append(decimals[num / 10]);

                num %= 10;
                if (num != 0) {
                    if (sb.length() != 0) {
                        sb.append(" ");
                    }
                    sb.append(digits[num]);
                }
            }
        }



        return sb.toString();
    }

    public static void main(String[] args) {
        final IntegerToEnglish s = new IntegerToEnglish();

        System.out.println(s.numberToWords(0));
        System.out.println(s.numberToWords(10));
        System.out.println(s.numberToWords(11));
        System.out.println(s.numberToWords(123));
        System.out.println(s.numberToWords(101));
        System.out.println(s.numberToWords(100));
        System.out.println(s.numberToWords(50));
        System.out.println(s.numberToWords(88));
        System.out.println(s.numberToWords(12345));
    }
}
