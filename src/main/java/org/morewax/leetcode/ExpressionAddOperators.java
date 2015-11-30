package org.morewax.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem
 *
 * Expression Add Operators (https://leetcode.com/problems/expression-add-operators/)
 *
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

 * Examples:
 *  "123", 6 -> ["1+2+3", "1*2*3"]
 *  "232", 8 -> ["2*3+2", "2+3*2"]
 *  "105", 5 -> ["1*0+5","10-5"]
 *  "00", 0 -> ["0+0", "0-0", "0*0"]
 *  "3456237490", 9191 -> []
 *
 *  Created by Bing on 11/27/2015.
 */
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> list = new ArrayList<String>();
        dfs(list, num, "", 0, 0, 0, target);
        return list;
    }

    private void dfs(List<String> list, String num, String path, int pos, long sum, long lastNum, int target) {
        int len = num.length();

        if (pos == len && sum == target) {
            list.add(path);
            return;
        }

        if (pos >= len) {
            return;
        }

        for (int i = pos; i < len; i++) {
            if (i != pos && num.charAt(pos) == '0') {
                break;
            }

            long curNum = Long.parseLong(num.substring(pos, i + 1));

            if (pos == 0) {
                dfs(list, num, path + "" + curNum, i + 1, curNum, curNum, target);
            } else {
                dfs(list, num, path + "+" + curNum, i + 1,
                        sum + curNum, curNum, target);
                dfs(list, num, path + "-" + curNum, i + 1,
                        sum - curNum, -curNum, target);
                dfs(list, num, path + "*" + curNum, i + 1,
                        sum - lastNum + lastNum * curNum, lastNum * curNum, target);
            }
        }
    }
}
