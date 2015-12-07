package org.morewax.meetqun;

import org.apache.commons.lang3.tuple.Pair;
import org.morewax.support.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Problem
 * Minimal Coverage
 *
 * Given a set of ranges:
 * (e.g. S = {(1, 4), (30, 40), (20, 91) ,(8, 10), (6, 7), (3, 9), (9, 12), (11, 14)}.
 * And given a target range R (e.g. R = (3, 13) - meaning the range going from 3 to 13).
 * Write an algorithm to find the smallest set of ranges that covers your target range.
 * All of the ranges in the set must overlap in order to be considered as spanning the entire target range.
 * (In this example, the answer would be {(3, 9), (9, 12), (11, 14)}.
 *
 * Note:
 *  the basic idea is explained here (http://stackoverflow.com/questions/293168/finding-the-minimal-coverage-of-an-interval-with-subintervals)
 *  and this problem is a very rare one that is related to greedy algorithm.
 * Created by Bing on 12/6/2015.
 */
public class MinimalCoverage {
    private static class RangeComparator implements Comparator<Pair<Integer, Integer>> {

        @Override
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            if (o1.getLeft() == o2.getRight()) return o1.getRight() - o2.getRight();
            else return o1.getLeft() - o2.getLeft();
        }
    }

    public List<Pair<Integer, Integer>> solve(List<Pair<Integer, Integer>> ranges, Pair<Integer, Integer> targetRange) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        int target, i, maxTarget, maxIndex;
        boolean match;

        if (targetRange.getLeft() == targetRange.getRight()) {
            ranges.forEach(range -> {
               if (range.getLeft() <= targetRange.getLeft() &&
                       range.getRight() >= targetRange.getRight() && result.isEmpty()) {
                   result.add(range);
               }
            });

            return result;
        }

        Collections.sort(ranges, new RangeComparator());

        target = targetRange.getLeft();
        maxIndex = 0;
        while (target < targetRange.getRight()) {
            match = false;
            maxTarget = target;
            for (i = maxIndex; i < ranges.size(); i++) {
                final Pair<Integer, Integer> currentRange = ranges.get(i);
                if (currentRange.getLeft() <= target && currentRange.getRight() >= maxTarget) {
                    match = true;
                    maxTarget = currentRange.getRight();
                    maxIndex = i;
                } else if (currentRange.getLeft() > target) {
                    break;
                }
            }

            if (!match) {
                result.clear();
                break;
            }

            result.add(Pair.of(ranges.get(maxIndex).getLeft(), ranges.get(maxIndex).getRight()));
            target = maxTarget;
            maxIndex++;
        }

        return result;
    }

    public static void main(String[] args) {
        MinimalCoverage mc = new MinimalCoverage();

        List<Pair<Integer, Integer>> s1 = new ArrayList();
        s1.add(Pair.of(1, 4));
        s1.add(Pair.of(30, 40));
        s1.add(Pair.of(20, 91));
        s1.add(Pair.of(8, 10));
        s1.add(Pair.of(6, 7));
        s1.add(Pair.of(3, 9));
        s1.add(Pair.of(9, 12));
        s1.add(Pair.of(11, 14));

        Pair<Integer, Integer> tr1 = Pair.of(3, 13);

        Utils.print(mc.solve(s1, tr1));
    }
}
