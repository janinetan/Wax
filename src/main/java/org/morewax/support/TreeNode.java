package org.morewax.support;

import java.util.*;

/**
 * Created by Bing on 11/28/2015.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

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
        if (root.left != null) {
            root.left.parent = root;
        }
        root.right = buildTreeHelper(A, mid+1, end);
        if (root.right != null) {
            root.right.parent = root;
        }

        return root;
    }

    public static TreeNode from(List<String> nodes) {
        if (nodes.isEmpty()) return null;

        if (nodes.get(0).equals("#")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(nodes.get(0)));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        for (int i = 1; i < nodes.size(); i += 2) {
            TreeNode node = q.poll();
            if (!nodes.get(i).equals("#")) {
                node.left = new TreeNode(Integer.parseInt(nodes.get(i)));
                q.offer(node.left);
            }

            if (!nodes.get(i+1).equals("#")) {
                node.right = new TreeNode(Integer.parseInt(nodes.get(i+1)));
                q.offer(node.right);
            }
        }

        return root;
    }

    public static List<String> to(TreeNode root) {
        List<String> result = new ArrayList<>();

        if (root == null) {
            result.add("#");
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode n = q.poll();

            if (n != null) {
                result.add(((Integer) n.val).toString());

                q.offer(n.left);
                q.offer(n.right);
            } else {
                result.add("#");
            }
        }

        return result;
    }
}
