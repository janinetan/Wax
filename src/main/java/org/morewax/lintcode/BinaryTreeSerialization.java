package org.morewax.lintcode;

import org.morewax.support.TreeNode;

/**
 * Problem
 * Serialize and Deserialize Binary Tree (http://www.lintcode.com/en/problem/binary-tree-serialization/)
 * Design an algorithm and write code to serialize and deserialize a binary tree. Writing the tree to a file is
 * called 'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.
 * There is no limit of how you deserialize or serialize a binary tree, you only need to make sure you can serialize a
 * binary tree to a string and deserialize this string to the original structure.
 * Example
 *  An example of testdata: Binary tree {3,9,20,#,#,15,7}, denote the following structure:
 *
  3
 / \
 9  20
 /  \
 15   7
 Our data serialization use bfs traversal. This is just for when you got wrong answer and want to debug the input.

 You can use other method to do serializaiton and deserialization.
 * Created by Bing on 1/4/2016.
 */
public class BinaryTreeSerialization {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        StringBuffer sb = new StringBuffer();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuffer sb) {
        if (sb.length() != 0) {
            sb.append(",");
        }

        if (root == null) {
            sb.append("#");
            return;
        }

        sb.append(Integer.toString(root.val));
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if (data == null || data.isEmpty()) return null;

        String[] values = data.split(",");
        int[] index = new int[1];
        return deserializeHelper(values, index);
    }

    private TreeNode deserializeHelper(String[] values, int[] index) {
        if (index[0] >= values.length || values[index[0]].equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(values[index[0]]));
        ++index[0];
        root.left = deserializeHelper(values, index);
        ++index[0];
        root.right = deserializeHelper(values, index);
        return root;
    }
}
