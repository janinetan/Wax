package org.morewax.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem
 * Summary Ranges (https://leetcode.com/problems/summary-ranges/)
 * Given a sorted integer array without duplicates, return the summary of its ranges.

 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 *
 * Created by byuan on 11/26/15.
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        if (nums.length > 0) {
            int s = nums[0];
            int e = nums[0];

            for (int i = 1; i < nums.length; ++i) {
                if (nums[i] - e == 1) {
                    e = nums[i];
                } else {
                    String range = getRange(s, e);
                    result.add(range);
                    s = nums[i];
                    e = nums[i];
                }
            }

            result.add(getRange(s, e));
        }

        return result;
    }

    private String getRange(int start, int end) {
        String range = "";

        range += Integer.toString(start);
        if (start != end) {
            range += "->";
            range += Integer.toString(end);
        }

        return range;
    }
}
