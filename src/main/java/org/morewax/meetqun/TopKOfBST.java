package org.morewax.meetqun;

import org.morewax.support.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/** Problem
 * 给了一个BST，要求输出最大的K个值
 *
 * Both implementations (recursive and iterative) assume K is <= total number of nodes of BST
 * Created by Bing on 12/5/2015.
 */
public class TopKOfBST {
    public void topK(TreeNode root, int k) {
        List<Integer> result = new ArrayList<>();

        if (root != null && k > 0) {
            helper(root, k, result);
        }

       printResult(result);
    }

    public void topKIterative(TreeNode root, int k) {
        List<Integer> result = new ArrayList<>();
        if (root != null && k > 0) {
            Stack<TreeNode> s = new Stack<>();
            TreeNode current = root;

            while (!s.empty() || current != null) {
                if (current != null) {
                    s.push(current);
                    current = current.right;
                } else {
                    TreeNode node = s.pop();
                    result.add(node.val);
                    if (result.size() == k) {
                        break;
                    }
                    current = node.left;
                }
            }
        }

        printResult(result);
    }

    private void helper(TreeNode node, int k, List<Integer> result) {
        if (node == null || result.size() == k) return;

        helper(node.right, k, result);
        if (result.size() < k) {
            result.add(node.val);
        }
        helper(node.left, k, result);
    }

    private void printResult(List<Integer> result) {
        int[] counter = new int[1];

        System.out.print("[");
        result.forEach(val -> {
            if (counter[0]++ != 0) {
                System.out.print(", ");
            }
            System.out.print(val);
        });
        System.out.println("]");
    }

    public static void main(String[] args) {
        TopKOfBST s = new TopKOfBST();

        int[] A1 = new int[]{3, 7, 9, 20, 40, 77, 89};
        TreeNode r1 = TreeNode.buildTree(A1);
        s.topK(r1, 1);
        s.topKIterative(r1, 1);
        s.topK(r1, 5);
        s.topKIterative(r1, 5);
        s.topK(r1, 7);
        s.topKIterative(r1, 7);
    }
}
