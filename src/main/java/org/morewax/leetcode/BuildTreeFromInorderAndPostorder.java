package org.morewax.leetcode;

import org.morewax.support.TreeNode;

/**
 * Problem
 * Build Binary Tree From Inorder and Postorder (https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note:
 *  You may assume that duplicates do not exist in the tree.
 * Created by Bing on 12/22/2015.
 */
public class BuildTreeFromInorderAndPostorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) return null;

        return helper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    private TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (postStart > postEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(postorder[postEnd]);
        int rootIndex = inStart;

        for(; rootIndex <= inEnd; ++rootIndex) {
            if (inorder[rootIndex] == postorder[postEnd]) {
                break;
            }
        }

        root.left = helper(inorder, inStart, rootIndex-1, postorder, postStart, postStart+rootIndex-inStart-1);
        root.right = helper(inorder, rootIndex+1, inEnd, postorder, postStart+rootIndex-inStart, postEnd-1);

        return root;
    }
}
