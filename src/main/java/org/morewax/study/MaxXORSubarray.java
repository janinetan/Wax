package org.morewax.study;

import org.morewax.support.TrieNode;

/**
 * Problem
 * Find the maximum XOR subarray (https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8&category=345&page=show_problem&problem=2683)
 * Given an array of integers. find the maximum XOR subarray value in given array.
 *
 * Example 1
 * Input: arr[] = {1, 2, 3, 4}
 * Output: 7
 * The subarray {3, 4} has maximum XOR value
 *
 * Example 2
 * Input: arr[] = {8, 1, 2, 12, 7, 6}
 * Output: 15
 * The subarray {1, 2, 12} has maximum XOR value
 *
 * Example 3
 * Input: arr[] = {4, 6}
 * Output: 6
 * The subarray {6} has maximum XOR value
 *
 * Analysis:
 * This article explains the basic idea beautifully (https://threads-iiith.quora.com/Tutorial-on-Trie-and-example-problems)
 * Created by byuan on 2/1/16.
 */
public class MaxXORSubarray {
    private static int NUM_BIT = 32;

    public int solve(int[] nums) {
        TrieNode<Integer> root = new TrieNode<>();
        insert(root, 0);
        int prefix = 0;
        int result = 0;

        for (int num : nums) {
            prefix = (prefix^num);
            insert(root, prefix);
            int current = query(root, prefix);
            result = Math.max(result, current);
        }

        return result;
    }

    private void insert(TrieNode root, int num) {
        TrieNode<Integer> node = root;

        for (int i = NUM_BIT-1; i >= 0; --i) {
            int index = ((num&(1<<i)) != 0)? 1 : 0;
            if (node.children[index] == null) {
                node.children[index] = new TrieNode<>();
            }
            node = node.children[index];
        }

        node.value = num;
    }

    private int query(TrieNode root, int num) {
        TrieNode<Integer> node = root;

        for (int i = NUM_BIT-1; i >= 0; --i) {
            int index = ((num&(1<<i)) != 0)? 1 : 0;
            if (node.children[1-index] != null) {
                node = node.children[1-index];
            } else if (node.children[index] != null) {
                node = node.children[index];
            }
        }

        return num^node.value;
    }

    public static void main(String[] args) {
        final MaxXORSubarray s = new MaxXORSubarray();

        System.out.println(s.solve(new int[]{1, 2, 3, 4}));
        System.out.println(s.solve(new int[]{8, 1, 2, 12, 7, 6}));
        System.out.println(s.solve(new int[]{4, 6}));
    }
}
