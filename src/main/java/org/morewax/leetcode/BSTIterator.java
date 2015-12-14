package org.morewax.leetcode;

/** Problem
 *  Binary Search Tree Iterator (https://leetcode.com/problems/binary-search-tree-iterator/)
 *  Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 * Calling next() will return the next smallest number in the BST.

 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * Created by Bing on 12/12/2015.
 */
import org.morewax.support.TreeNode;

import java.util.Stack;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    Stack<TreeNode> s;
    TreeNode current;

    public BSTIterator(TreeNode root) {
        s = new Stack<>();
        current = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !s.empty() || current != null;
    }

    /** @return the next smallest number */
    public int next() {
        while (current != null) {
            s.push(current);
            current = current.left;
        }

        TreeNode n = s.pop();
        current = n.right;
        return n.val;
    }
}