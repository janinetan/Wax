package org.morewax.mitbbs;

import org.morewax.support.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem
 * 找出两个不同字符的最长的连续字串。打印出所有的解。"yellow" => [ell, llo]
 * Source: MITBBS (http://www.mitbbs.com/article_t/JobHunting/33124223.html)
 * Created by byuan on 1/15/16.
 */
public class LongestSubstring {
    List<String> solve(final String str) {
        List<String> results = new ArrayList<>();
        Map<Character, Integer> count = new HashMap<>();
        int start = 0;

        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);

            if (count.size() < 2) {
                if (count.containsKey(c)) {
                    count.put(c, count.get(c)+1);
                } else {
                    count.put(c, 1);
                }
            } else {
                if (count.containsKey(c)) {
                    count.put(c, count.get(c)+1);
                } else {
                    count.put(c, 1);
                }

                while (count.size() > 2) {
                    char cc = str.charAt(start);
                    count.put(cc, count.get(cc)-1);
                    if (count.get(cc) == 0) {
                        count.remove(cc);
                    }
                    ++start;
                }

                final String temp = str.substring(start, i+1);
                addToResultsIfPossible(results, temp);
            }
        }

        return results;
    }

    private void addToResultsIfPossible(List<String> results, final String temp) {
        if (results.isEmpty()) {
            results.add(temp);
        } else {
            if (temp.length() > results.get(0).length()) {
                results.clear();
                results.add(temp);
            } else if (temp.length() == results.get(0).length()) {
                results.add(temp);
            }
        }
    }

    public static void main(String[] args) {
        final LongestSubstring s = new LongestSubstring();

        Utils.print(s.solve("yellow"));
    }
}
