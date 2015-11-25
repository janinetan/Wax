package org.morewax.leetcode;

import java.util.Stack;

/**
 * Problem:
 * Basic Calculator (https://leetcode.com/problems/basic-calculator/)
 *
 * Implement a basic calculator to evaluate a simple expression string.

 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

 * You may assume that the given expression is always valid.

 * Some examples:
 *      "1 + 1" = 2
 *      "2-1 + 2 " = 3
 *      "(1+(4+5+2)-3)+(6+8)" = 23
 *
 * Created by Bing on 11/24/2015.
 */
public class BasicCalculator {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        /**
         * the stack is used to track the impact of '(' and ')' on the operator '+' and '-'
         * the first two pushes are necessary because all the operators here are binary operators
         */
        stack.push(1);
        stack.push(1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                int j = i + 1;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    num = 10 * num + (s.charAt(j) - '0');
                    j++;
                }
                res += stack.pop() * num;
                i = j - 1;
            } else if (c == '+' || c == '(') {
                stack.push(stack.peek());
            } else if (c == '-') {
                stack.push(-1 * stack.peek());
            } else if (c == ')') {
                stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        BasicCalculator basicCalculator = new BasicCalculator();
        int result;

        String s1 = "1 + 1";
        result = basicCalculator.calculate(s1);
        System.out.println(s1 + " = " + result);

        String s2 = "2 - 1  + 2";
        result = basicCalculator.calculate(s2);
        System.out.println(s2 + " = " + result);

        String s3 = "(1+(4+5+2)-3)+(6+8)";
        result = basicCalculator.calculate(s3);
        System.out.println(s3 + " = " + result);
    }
}
