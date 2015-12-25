package org.morewax.lintcode;

/**
 * Problem
 * Longest Common Prefix ()
 * Given k strings, find the longest common prefix (LCP).
 * Example
 * For strings "ABCD", "ABEF" and "ACEF", the LCP is "A"
 *
 * For strings "ABCDEFG", "ABCEFG" and "ABCEFA", the LCP is "ABC"
 *
 * Created by Bing on 12/24/2015.
 */
public class LongestCommonPrefix {
    /**
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        // write your code here
        if (strs == null || strs.length == 0) return "";

        return LCP(strs, 0, strs.length-1);
    }

    private String LCP(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else if (start+1 == end) {
            return helper(strs[start], strs[end]);
        } else {
            int mid = start + (end-start)/2;
            return helper(LCP(strs, start, mid), LCP(strs, mid+1, end));
        }
    }

    private String helper(String s1, String s2) {
        int i = 0;
        for (; i < Math.min(s1.length(), s2.length()); ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }
        }

        return s1.substring(0, i);
    }
}
