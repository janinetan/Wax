package org.morewax.leetcode;

/**
 * Problem
  * Implement strStr(). (https://leetcode.com/problems/implement-strstr/)

 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * Created by byuan on 12/18/15.
 */
public class StrStr {
    public int strStr(String haystack, String needle) {
        int result = -1;

        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i <= n-m; ++i) {
            if (compare(haystack, i, needle)) {
                result = i;
                break;
            }
        }

        return result;
    }

    private boolean compare(String haystack, int start, String needle) {
        boolean result = true;

        for (int i = 0; i < needle.length(); ++i) {
            if (haystack.charAt(i+start) != needle.charAt(i)) {
                result = false;
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        final StrStr s = new StrStr();

        System.out.println(s.strStr("mississippi", "a"));
    }
}
