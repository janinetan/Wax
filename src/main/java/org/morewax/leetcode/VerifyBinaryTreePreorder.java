package org.morewax.leetcode;

import java.util.Stack;

/**
 * Problem
 * Verify Preorder Serialization of Binary Tree (https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/)
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the
 * node's value. If it is a null node, we record using a sentinel value such as #.

  _9_
 /   \
 3     2
 / \   / \
 4   1  #  6
 / \ / \   / \
 # # # #   # #
 For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

 Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree.
 Find an algorithm without reconstructing the tree.

 Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

 You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

 Example 1:
 "9,3,4,#,#,1,#,#,2,#,6,#,#"
 Return true

 Example 2:
 "1,#"
 Return false

 Example 3:
 "9,#,#,1"
 Return false
 * Created by byuan on 2/3/16.
 */
public class VerifyBinaryTreePreorder {
    public boolean isValidSerialization(String preorder) {
        boolean result = true;
        Stack<Integer> s = new Stack<>();

        String[] values = preorder.split(",");
        for (int i = 0; i < values.length; ++i) {
            if (values[i] == null || values[i].length() == 0) {
                result = false;
                break;
            }

            if (values[i].equals("#")) {
                if (s.empty()) {
                    if (i != 0) {
                        result = false;
                        break;
                    }
                } else {
                    s.pop();
                }
            } else {
                try {
                    Integer.parseInt(values[i]);
                    if (s.empty()) {
                        if (i != 0) {
                            result = false;
                            break;
                        }
                    } else {
                        s.pop();
                    }

                    s.push(1);
                    s.push(0);
                } catch (NumberFormatException e) {
                    result = false;
                    break;
                }
            }
        }

        result = (result)? s.empty() : result;

        return result;
    }

    public boolean isValidSerialization2(String preorder) {
        boolean result = true;
        int count = 0;

        String[] values = preorder.split(",");
        if (values.length == 0) return false;

        if (isNumber(values[0])) {
            count += 2;
        }

        for (int i = 1; i < values.length; ++i) {
            if (isNull(values[i])) {
                if (count == 0) {
                    result = false;
                    break;
                } else {
                    --count;
                }
            } else if (isNumber(values[i])) {
                if (count == 0) {
                    result = false;
                    break;
                } else {
                    --count;
                }
                count += 2;
            } else {
                result = false;
                break;
            }
        }

        result = (result)? (count == 0) : result;

        return result;
    }

    private boolean isNumber(final String str) {
        boolean result = true;

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            result = false;
        }

        return result;
    }

    private boolean isNull(final String str) {
        return "#".equals(str);
    }

    public static void main(String[] args) {
        final VerifyBinaryTreePreorder s = new VerifyBinaryTreePreorder();

        System.out.println(s.isValidSerialization("1,#"));
        System.out.println(s.isValidSerialization2("1,#"));
        System.out.println(s.isValidSerialization("9,#,#,1"));
        System.out.println(s.isValidSerialization2("9,#,#,1"));
        System.out.println(s.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println(s.isValidSerialization2("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println(s.isValidSerialization("9,3,4,#,#,1,#,#,#,2,#,6,#,#"));
        System.out.println(s.isValidSerialization2("9,3,4,#,#,1,#,#,#,2,#,6,#,#"));
    }
}
