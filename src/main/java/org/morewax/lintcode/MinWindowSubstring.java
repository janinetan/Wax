package org.morewax.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem
 * Minimum Window Substring (http://www.lintcode.com/en/problem/minimum-window-substring/)
 * Given a string source and a string target, find the minimum window in source which will contain all the characters in
 * target.
 * Example
 * For source = "ADOBECODEBANC", target = "ABC", the minimum window is "BANC"
 * Note
 * If there is no such window in source that covers all characters in target, return the emtpy string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in source.
 * Created by byuan on 2/22/16.
 */
public class MinWindowSubstring {
    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
     */
    public String minWindow(String source, String target) {
        // write your code
        if (source == null || source.length() == 0 || target == null || target.length() == 0) return "";

        Map<Character, Integer[]> m = new HashMap<>();
        int targetLength = target.length();
        int currentLength = 0;
        String result = "";
        int start = 0;

        for (int i = 0; i < target.length(); ++i) {
            if (m.containsKey(target.charAt(i))) {
                m.get(target.charAt(i))[0] += 1;
            } else {
                Integer[] values = new Integer[2];
                values[0] = 1;
                values[1]= 0;
                m.put(target.charAt(i), values);
            }
        }

        for (int i = 0; i < source.length(); ++i) {
            char c = source.charAt(i);
            if (!m.containsKey(c)) {
                continue;
            }

            Integer[] values = m.get(c);
            values[1] += 1;

            if (values[1] <= values[0]) {
                ++currentLength;
            }

            if (currentLength == targetLength) {
                // Now [start, i] covers the full content of target,
                // it's time to shrink the range to make it minimal.
                int j = start;
                for (; j <= i; ++j) {
                    char t = source.charAt(j);
                    if (!m.containsKey(t)) {
                        continue;
                    }
                    Integer[] v1 = m.get(t);
                    if (v1[1] > v1[0]) {
                        v1[1] -= 1;
                    } else {
                        break;
                    }
                }

                start = j;
                if (result.isEmpty() || result.length() > (i-start+1)) {
                    result = source.substring(start, i+1);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        final MinWindowSubstring s = new MinWindowSubstring();

        System.out.println("Min Window Substring: " + s.minWindow("ADOBECODEBANC", "ABC"));
    }
}
