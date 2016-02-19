package org.morewax.study;

import org.morewax.support.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * Problem
 * Given a binary tree, find the size of largest BST subtree of it.
 * Created by byuan on 2/17/16.
 */
public class LargestBSTSubtree {
    private static class Result {
        public boolean isBST;
        public int numberOfNodes;
        public int minValue;
        public int maxValue;

        public Result(boolean isBST, int numberOfNodes, int minValue, int maxValue) {
            this.isBST = isBST;
            this.numberOfNodes = numberOfNodes;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    public int sizeOfLargestBSTSubtree(List<String> serializedTree) {
        final TreeNode root = TreeNode.from(serializedTree);

        final Result result = helper(root);

        return result.numberOfNodes;
    }

    private Result helper(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new Result(true, 1, root.val, root.val);
        }

        Result leftResult = null;
        Result rightResult = null;

        if (root.left != null) {
            leftResult = helper(root.left);
        }

        if (root.right != null) {
            rightResult = helper(root.right);
        }

        if (leftResult != null && rightResult != null) {
            if (leftResult.isBST && rightResult.isBST) {
                if (leftResult.maxValue <= root.val && root.val <= rightResult.minValue) {
                    return new Result(true, leftResult.numberOfNodes+rightResult.numberOfNodes+1, leftResult.minValue, rightResult.maxValue);
                } else {
                    return new Result(false, Math.max(leftResult.numberOfNodes, rightResult.numberOfNodes), 0, 0);
                }
            } else if (leftResult.isBST) {
                if (leftResult.maxValue <= root.val) {
                    return new Result(true, Math.max(leftResult.numberOfNodes+1, rightResult.numberOfNodes), leftResult.minValue, root.val);
                } else {
                    return new Result(false, Math.max(leftResult.numberOfNodes, rightResult.numberOfNodes), 0, 0);
                }
            } else if (rightResult.isBST) {
                if (rightResult.minValue >= root.val) {
                    return new Result(true, Math.max(rightResult.numberOfNodes+1, rightResult.numberOfNodes), root.val, rightResult.maxValue);
                } else {
                    return new Result(false, Math.max(leftResult.numberOfNodes, rightResult.numberOfNodes), 0, 0);
                }
            } else {
                return new Result(false, Math.max(leftResult.numberOfNodes, rightResult.numberOfNodes), 0, 0);
            }
        } else if (leftResult != null) {
            if (leftResult.isBST) {
                if (leftResult.maxValue <= root.val) {
                    return new Result(true, leftResult.numberOfNodes+1, leftResult.minValue, root.val);
                } else {
                    return new Result(false, leftResult.numberOfNodes, 0, 0);
                }
            } else {
                return new Result(false, leftResult.numberOfNodes, 0, 0);
            }
        } else {
            if (rightResult.isBST) {
                if (rightResult.minValue >= root.val) {
                    return new Result(true, rightResult.numberOfNodes+1, root.val, rightResult.maxValue);
                } else {
                    return new Result(false, rightResult.numberOfNodes, 0, 0);
                }
            } else {
                return new Result(false, rightResult.numberOfNodes, 0, 0);
            }
        }
    }

    public static void main(String[] args) {
        final LargestBSTSubtree s = new LargestBSTSubtree();

        List<String> t = Arrays.asList(
                "25"
                , "18", "50"
                , "19", "20", "35", "60"
                , "#", "15", "18", "25", "20", "40", "55", "70"
                , "#", "#", "#", "#", "#", "#", "#", "25", "#", "#", "#", "#", "#", "#"
                , "#", "#"
        );
        System.out.println("The size of largest BST subtree: " + s.sizeOfLargestBSTSubtree(t));
    }
}
