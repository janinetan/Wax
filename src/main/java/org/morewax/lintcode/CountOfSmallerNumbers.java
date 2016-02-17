package org.morewax.lintcode;

import org.morewax.support.Utils;

import java.util.ArrayList;

/**
 * Problem
 * Count of Smaller Numbers (http://www.lintcode.com/en/problem/count-of-smaller-number/)
 * Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) and an
 * query list. For each query, give you an integer, return the number of element in the array that are smaller than the
 * given integer.
 * Example
 *  For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]
 * Created by Bing on 2/16/2016.
 */
public class CountOfSmallerNumbers {
    /**
     * @param A: An integer array
     * @return: The number of element in the array that
     *          are smaller that the given integer
     */
    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        final SegmentTreeNode root = buildSegmentTree(0, 10000);
        for (int i : A) {
            updateTree(root, i);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int query : queries) {
            result.add(queryTree(root, 0, query - 1));
        }

        return result;
    }

    private SegmentTreeNode buildSegmentTree(int start, int end) {
        SegmentTreeNode root = new SegmentTreeNode(start, end);

        if (end > start) {
            int mid = start + (end-start)/2;
            root.left = buildSegmentTree(start, mid);
            root.right = buildSegmentTree(mid+1, end);
        }

        return root;
    }

    private void updateTree(SegmentTreeNode root, int value) {
        if (root.start == root.end && root.start == value) {
            root.count += 1;
            return;
        }

        if (value < root.start || root.end < value) return;

        if (root.start <= value && value <= root.end) {
            updateTree(root.left, value);
            updateTree(root.right, value);
            root.count = root.left.count + root.right.count;
        }
    }

    private int queryTree(SegmentTreeNode root, int start, int end) {
        if (root == null) return 0;

        if (root.end < start || end < root.start) return 0;

        if (start <= root.start && root.end <= end) return root.count;

        return queryTree(root.left, start, end) + queryTree(root.right, start, end);
    }

    private static class SegmentTreeNode {
        public int count;
        public int start;
        public int end;
        public SegmentTreeNode left;
        public SegmentTreeNode right;

        public SegmentTreeNode(int start, int end) {
            this.count = 0;
            this.start = start;
            this.end = end;
            this.left = this.right = null;
        }
    }

    public static void main(String[] args) {
        final CountOfSmallerNumbers s = new CountOfSmallerNumbers();

        //[1,2,7,8,5], and queries [1,8,5], return [0,4,2]
        Utils.print(s.countOfSmallerNumber(new int[]{1, 2, 7, 8, 5}, new int[]{1, 8, 5}));
    }
}
