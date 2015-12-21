package org.morewax.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem
 * Remove Duplicate Letters (https://leetcode.com/problems/remove-duplicate-letters/)
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.

 Example:
 Given "bcabc"
 Return "abc"

 Given "cbacdcbc"
 Return "acdb"

 * Analysis:
 *
 * The basic idea is to find out the smallest result letter by letter (one letter at a time). Here is the thinking process for input "cbacdcbc":
 * Steps
 *  1. find out the last appeared position for each letter; c - 7 b - 6 a - 2 d - 4
 *  2. find out the smallest index from the map in step 1 (a - 2);
 *  3. the first letter in the final result must be the smallest letter from index 0 to index 2;
 *  4. repeat step 2 to 3 to find out remaining letters.
 *
 *  For example:
 *   the smallest letter from index 0 to index 2: a
 *   the smallest letter from index 3 to index 4: c
 *   the smallest letter from index 4 to index 4: d
 *   the smallest letter from index 5 to index 6: b
 *   so the result is "acdb"

 * Notes:
 *  1. after one letter is determined in step 3, it need to be removed from the "last appeared position map", and the same letter should be ignored in the following steps
 *  2. in step 3, the beginning index of the search range should be the index of previous determined letter plus one

 * This blog explains the idea better: http://www.hrwhisper.me/leetcode-remove-duplicate-letters/
 * Created by Bing on 12/20/2015.
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) return s;

        Map<Character, Integer> lastPosMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastPosMap.put(s.charAt(i), i); // step 1
        }

        char[] result = new char[lastPosMap.size()];
        int begin = 0, end = findMinLastPos(lastPosMap); // step 2

        for (int i = 0; i < result.length; i++) {
            char minChar = 'z' + 1;
            for (int k = begin; k <= end; k++) { // step 3
                if (lastPosMap.containsKey(s.charAt(k)) && s.charAt(k) < minChar) {
                    minChar = s.charAt(k);
                    begin = k+1; // See Point 2 in above Notes
                }
            }

            result[i] = minChar;
            if (i == result.length-1) break;

            lastPosMap.remove(minChar); // see Point 1 in above Notes
            //if (s.charAt(end) == minChar) end = findMinLastPos(lastPosMap);
            end = findMinLastPos(lastPosMap);
        }

        return new String(result);
    }

    private int findMinLastPos(Map<Character, Integer> lastPosMap) {
        if (lastPosMap == null || lastPosMap.isEmpty()) return -1;
        int minLastPos = Integer.MAX_VALUE;
        for (int lastPos : lastPosMap.values()) {
            minLastPos = Math.min(minLastPos, lastPos);
        }
        return minLastPos;
    }

    public static void main(String[] args) {
        final RemoveDuplicateLetters s = new RemoveDuplicateLetters();

        System.out.println(s.removeDuplicateLetters("bcabc"));
        System.out.println(s.removeDuplicateLetters("cbacdcbc"));
    }
}
