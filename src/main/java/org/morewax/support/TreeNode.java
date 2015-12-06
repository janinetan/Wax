package org.morewax.support;

/**
 * Created by Bing on 11/28/2015.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        this.val = x;
    }

    public static TreeNode buildTree(int[] A) {
        TreeNode root = null;

        if (A.length > 0) {
            root = buildTreeHelper(A, 0, A.length-1);
        }

        return root;
    }

    private static TreeNode buildTreeHelper(int[] A, int start, int end) {
        if (start > end) {
            return null;
        }

        if (start == end) {
            return new TreeNode(A[start]);
        }

        int mid = start + (end-start)/2;
        TreeNode root = new TreeNode(A[mid]);
        root.left = buildTreeHelper(A, start, mid-1);
        root.right = buildTreeHelper(A, mid+1, end);

        return root;
    }
}
