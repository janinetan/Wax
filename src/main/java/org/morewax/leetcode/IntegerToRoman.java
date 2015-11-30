package org.morewax.leetcode;

/**
 * Given an integer, convert it to a roman numeral.

 Input is guaranteed to be within the range from 1 to 3999.
 * Created by byuan on 11/30/15.
 */
public class IntegerToRoman {
    public String intToRoman(int num) {
        String[] romanCharacters = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] romanValues = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuffer sb = new StringBuffer();

        for (int i = 0; num != 0; ++i) {
            while (num >= romanValues[i]) {
                num -= romanValues[i];
                sb.append(romanCharacters[i]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();

        System.out.println(integerToRoman.intToRoman(1954));

        System.out.println(integerToRoman.intToRoman(1990));

        System.out.println(integerToRoman.intToRoman(2014));
    }
}
