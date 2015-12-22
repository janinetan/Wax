package org.morewax.leetcode;

import org.morewax.support.TreeNode;

/** Problem
 * Build binary tree from preorder and inorder traversal results (https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Created by Bing on 12/21/2015.
 */
public class BuildTreeFromPreorderAndInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) return null;

        return helper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode helper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart >= preEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = inStart;

        for (; rootIndex < inEnd; ++rootIndex) {
            if (inorder[rootIndex] == preorder[preStart]) {
                break;
            }
        }

        root.left = helper(preorder, preStart+1, preStart+1+rootIndex-inStart, inorder, inStart, rootIndex);
        root.right = helper(preorder, preStart+1+rootIndex-inStart, preEnd, inorder, rootIndex+1, inEnd);

        return root;
    }
}
