package org.morewax.cc;

import org.morewax.support.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem
 * Find if a given binary tree has duplicate sub trees or not.
 * (Two leaf nodes of a parent do not count as subtree)
 * Created by Bing on 2/18/2016.
 */
public class DuplicateSubtree {
    private static class Result {
        public boolean isLeafNode;
        public String serializedSubtree;
        public boolean hasDuplicate;

        public Result() {
            this.isLeafNode = false;
            this.hasDuplicate = false;
            this.serializedSubtree = "";
        }
    }

    public boolean hasDuplicateSubtree(TreeNode root) {
        Map<String, TreeNode> m = new HashMap<>();

        if (root == null) return false;

        Result result = new Result();

        helper(root, result, m);

        return result.hasDuplicate;
    }

    private void helper(TreeNode root, Result result, Map<String, TreeNode> m) {
        if (root.left == null && root.right == null) {
            result.hasDuplicate = false;
            result.isLeafNode = true;
            result.serializedSubtree = ((Integer)root.val).toString() + "#" + "#";
        } else if (root.left != null && root.right != null) {
            Result left = new Result();
            Result right = new Result();

            helper(root.left, left, m);
            if (left.hasDuplicate) {
                result.hasDuplicate = true;
                return;
            }

            helper(root.right, right, m);
            if (right.hasDuplicate) {
                result.hasDuplicate = true;
                return;
            }

            if (left.isLeafNode && right.isLeafNode) {
                result.serializedSubtree = ((Integer)root.val).toString() + left.serializedSubtree + right.serializedSubtree;
                if (m.containsKey(result.serializedSubtree)) {
                    result.hasDuplicate = true;
                } else {
                    m.put(result.serializedSubtree, root);
                }
            }
        } else if (root.left != null) {
            Result left = new Result();
            helper(root.left, left, m);

            if (left.hasDuplicate) {
                result.hasDuplicate = true;
                return;
            }

            if (left.isLeafNode) {
                result.serializedSubtree = ((Integer)root.val).toString() + left.serializedSubtree + "#";
                if (m.containsKey(result.serializedSubtree)) {
                    result.hasDuplicate = true;
                } else {
                    m.put(result.serializedSubtree, root);
                }
            }
        } else {
            Result right = new Result();
            helper(root.right, right, m);

            if (right.hasDuplicate) {
                result.hasDuplicate = true;
                return;
            }

            if (right.isLeafNode) {
                result.serializedSubtree = ((Integer)root.val).toString() + "#" + right.serializedSubtree;
                if (m.containsKey(result.serializedSubtree)) {
                    result.hasDuplicate = true;
                } else {
                    m.put(result.serializedSubtree, root);
                }
            }
        }
    }

    public String postOrder(TreeNode root) {
        if (root == null) return "#";

        String left = postOrder(root.left);
        String right = postOrder(root.right);

        return ((Integer)root.val).toString()+ left + right;
    }

    public String preOrder(TreeNode root) {
        if (root == null) return "#";

        return ((Integer)root.val).toString()+ preOrder(root.left) + preOrder(root.right);
    }

    public static void main(String[] args) {
        final DuplicateSubtree s = new DuplicateSubtree();

        TreeNode r1 = TreeNode.from(Arrays.asList(
                          "1",
                   "2",         "3",
                "#", "#",    "4",  "#",
                          "#", "#"
        ));
        System.out.println("PostOrder:" + s.postOrder(r1));
        System.out.println("PreOrder:" + s.preOrder(r1));

        TreeNode r2 = TreeNode.from(Arrays.asList(
                          "1",
                   "2",        "3",
                "#", "3",    "#", "4",
                   "#", "4",    "#", "#",
                      "#", "#"
        ));

        System.out.println("Has duplicate subtree? " + s.hasDuplicateSubtree(r2));

        TreeNode r3 = TreeNode.from(Arrays.asList(
                            "1",
                    "2",            "3",
                "#", "3",       "4",    "#",
                   "#", "4",  "#", "#",
                      "#", "#"
        ));

        System.out.println("Has duplicate subtree? " + s.hasDuplicateSubtree(r3));

        TreeNode r4 = TreeNode.from(Arrays.asList(
                            "1",
                    "2",            "3",
                "#",   "3",       "#",    "4",
                    "#",  "4",          "1", "#",
                        "#", "#",     "#", "#"
        ));

        System.out.println("Has duplicate subtree? " + s.hasDuplicateSubtree(r4));
    }
}
