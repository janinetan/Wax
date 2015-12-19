package org.morewax.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem
 * Maximum Product of Word Length (https://leetcode.com/problems/maximum-product-of-word-lengths/)
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
 * You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

 * Example 1:
 * Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 * Return 16
 * The two words can be "abcw", "xtfn".

 * Example 2:
 * Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 * Return 4
 * The two words can be "ab", "cd".

 * Example 3:
 * Given ["a", "aa", "aaa", "aaaa"]
 * Return 0
 * No such pair of words.

 * Created by byuan on 12/18/15.
 */
public class MaxProductOfWordLength {
    public int maxProduct(String[] words) {
        int maxLength = 0;

        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            int temp = 0;
            for (char c : words[i].toCharArray()) {
                temp |= (1<<(c-'a'));
            }
            m.put(words[i], temp);
        }

        for (int i = 0; i < words.length; ++i) {
            for (int j = 0; j < words.length; ++j) {
                if (i != j && (m.get(words[i]) & m.get(words[j])) == 0) {
                    maxLength = Math.max(words[i].length()*words[j].length(), maxLength);
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        final MaxProductOfWordLength s = new MaxProductOfWordLength();

        String[] a1 = new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.out.println("Max Product of Word Length: " + s.maxProduct(a1));

        String[] a2 = new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        System.out.println("Max Product of Word Length: " + s.maxProduct(a2));

        String[] a3 = new String[]{"a", "aa", "aaa", "aaaa"};
        System.out.println("Max Product of Word Length: " + s.maxProduct(a3));
    }
}
