package org.morewax.study;

/**
 * Problem
 * Count the number of subarrays that XOR value of the array is less than K (http://www.spoj.com/problems/SUBXOR/)
 *
 * Given an array of positive integers you have to print the number of subarrays whose XOR is less than K.
 * Example
 * Input: {4, 1, 3, 2, 7}, K = 2
 * Output: 3, that is:
 * {1}, {1, 3, 2} and {3, 2} are less than K (=2)
 *
 * Analysis:
 * This article explains the idea beautifully. (https://threads-iiith.quora.com/Tutorial-on-Trie-and-example-problems)
 * Created by byuan on 2/1/16.
 */
public class SubXOR {
    private static int NUM_BITS = 32;

    private static class MyTrieNode {
        public int leftLeafNodes;
        public int rightLeafNodes;

        public MyTrieNode[] children;

        public MyTrieNode() {
            children = new MyTrieNode[2];
        }
    }

    public int solve(int[] nums, int k) {
        MyTrieNode root = new MyTrieNode();
        insert(root, 0);
        int prefix = 0;
        int result = 0;

        for (int i = 0; i < nums.length; ++i) {
            prefix = (prefix^nums[i]);
            result += query(root, prefix, k);
            insert(root, prefix);
        }

        return result;
    }

    private void insert(MyTrieNode root, int num) {
        MyTrieNode node = root;

        for (int i = NUM_BITS-1; i >= 0; --i) {
            int index = ((num & (1<<i)) != 0)? 1 : 0;
            if (index == 0) {
                node.leftLeafNodes += 1;
            } else {
                node.rightLeafNodes += 1;
            }

            if (node.children[index] == null) {
                node.children[index] = new MyTrieNode();
            }

            node = node.children[index];
        }
    }

    public int query(MyTrieNode root, int num, int k) {
        MyTrieNode node = root;
        int result = 0;

        for (int i = NUM_BITS-1; i >= 0; --i) {
            int nBit = ((num & (1<<i)) != 0)? 1 : 0;
            int kBit = ((k & (1<<i)) != 0)? 1 : 0;

            if (nBit == 1 && kBit == 1) {
                result += node.rightLeafNodes;
                if (node.children[1-nBit] != null) {
                    node = node.children[1-nBit];
                } else {
                    break;
                }
            } else if (nBit == 0 && kBit == 1) {
                result += node.leftLeafNodes;
                if (node.children[1-nBit] != null) {
                    node = node.children[1-nBit];
                } else {
                    break;
                }
            } else if (nBit == 1 && kBit == 0) {
                if (node.children[nBit] != null) {
                    node = node.children[nBit];
                } else {
                    break;
                }
            } else { // nBit == 0 && kBit == 0
                if (node.children[nBit] != null) {
                    node = node.children[nBit];
                } else {
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        final SubXOR s = new SubXOR();

        System.out.println(s.solve(new int[]{4, 1, 3, 2, 7}, 2));
    }
}
