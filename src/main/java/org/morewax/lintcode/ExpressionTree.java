package org.morewax.lintcode;

import org.morewax.support.ExpressionTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Problem
 * Expression Tree Build (http://www.lintcode.com/en/problem/expression-tree-build/)
 * The structure of Expression Tree is a binary tree to evaluate certain expressions.
 * All leaves of the Expression Tree have an number string value. All non-leaves of the Expression Tree have an operator
 * string value.
 * Now, given an expression array, build the expression tree of this expression, return the root of this expression tree.
 *
 * Example
 * For the expression (2*6-(23+7)/(1+2)) (which can be represented by ["2" "*" "6" "-" "(" "23" "+" "7" ")" "/" "(" "1" "+" "2" ")"]).
 * The expression tree will be like
 *                 [ - ]
 *              /          \
 *      [ * ]              [ / ]
 *    /     \           /         \
 *  [ 2 ]  [ 6 ]      [ + ]        [ + ]
 *                    /    \       /      \
 *                  [ 23 ][ 7 ] [ 1 ]   [ 2 ] .
 * After building the tree, you just need to return root node [-].
 * Created by Bing on 2/12/2016.
 */
public class ExpressionTree {
    /**
     * @param expression: A string array
     * @return: The root of expression tree
     */
    public ExpressionTreeNode build(String[] expression) {
        // write your code here
        if (expression == null || expression.length == 0) return null;

        List<String> posixExpression = toPosixExpression(expression);
        ExpressionTreeNode root = buildExpressionTree(posixExpression);

        return root;
    }

    private List<String> toPosixExpression(String[] expression) {
        List<String> posixExpression = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        for (String item : expression) {
            if (isOperator(item)) {
                while (!operators.isEmpty()) {
                    if (hasPrecedence(operators.peek(), item)) {
                        posixExpression.add(operators.pop());
                    } else {
                        break;
                    }
                }

                if (!item.equals(")")) {
                    operators.push(item);
                } else {
                    do {
                        String operator = operators.pop();
                        if (operator.equals("(")) {
                            break;
                        } else {
                            posixExpression.add(operator);
                        }
                    } while(true);
                }
            } else {
                posixExpression.add(item);
            }
        }

        while (!operators.isEmpty()) {
            posixExpression.add(operators.pop());
        }

        return posixExpression;
    }

    private boolean hasPrecedence(String topOperator, String currentOperator) {
        if (topOperator.equals("+") || topOperator.equals("-")) {
            if (currentOperator.equals("+") || currentOperator.equals("-") || currentOperator.equals(")")) {
                return true;
            } else {
                return false;
            }
        } else if (topOperator.equals("*") || topOperator.equals("/")) {
            if (currentOperator.equals("(")) {
                return false;
            } else {
                return true;
            }
        } else if (topOperator.equals("(")) {
            return false;
        } else {
           return false;
        }
    }

    private ExpressionTreeNode buildExpressionTree(List<String> posixExpression) {
        Stack<ExpressionTreeNode> s = new Stack<>();

        for (String item : posixExpression) {
            if (isOperator(item)) {
                ExpressionTreeNode root = new ExpressionTreeNode(item);
                root.right = s.pop();
                root.left = s.pop();
                s.push(root);
            } else {
                s.push(new ExpressionTreeNode(item));
            }
        }

        return s.empty()? null : s.pop();
    }

    private boolean isOperator(String item) {
        if (item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/") || item.equals("(") || item.equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        final ExpressionTree s = new ExpressionTree();

        final ExpressionTreeNode r1 = s.build(new String[]{"2", "*", "6", "-", "(", "23", "+", "7", ")", "/", "(", "1", "+", "2", ")"});
        System.out.println((r1==null)? "empty tree": r1.symbol);

        final ExpressionTreeNode r2 = s.build(new String[]{"(","(","(","(","(",")",")",")",")",")"});
        System.out.println((r2==null)? "empty tree": r2.symbol);
    }
}
