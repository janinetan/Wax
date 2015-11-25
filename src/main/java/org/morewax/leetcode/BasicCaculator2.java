package org.morewax.leetcode;

import java.util.Stack;

/**
 * Problem
 * Basic Calculator II (https://leetcode.com/problems/basic-calculator-ii/)
 *
 * Implement a basic calculator to evaluate a simple expression string.

 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

 * You may assume that the given expression is always valid.

 * Some examples:
 *  "3+2*2" = 7
 *  " 3/2 " = 1
 *  " 3+5 / 2 " = 5

 * Created by Bing on 11/24/2015.
 */
public class BasicCaculator2 {
    public int calculate(String s) {
        Stack<Character> operators = new Stack<>();
        Stack<Integer> operands = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            if (Character.isDigit(s.charAt(i))) {
                int res = 0;
                int j = i;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    res *= 10;
                    res += (s.charAt(j) - '0');
                    ++j;
                }
                operands.push(res);
                i = j - 1;
            } else {
                char c = s.charAt(i);
                if (isOperator(c)) {
                    if (operators.isEmpty()) {
                        operators.push(c);
                    } else {
                        while (!operators.isEmpty() && shouldCaculate(operators.peek(), c)) {
                            operands.push(compute(operands.pop(), operands.pop(), operators.pop()));
                        }
                        operators.push(c);
                    }
                }
            }
        }

        while (!operators.isEmpty()) {
            operands.push(compute(operands.pop(), operands.pop(), operators.pop()));
        }

        return operands.peek();
    }

    private boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    private boolean shouldCaculate(char op1, char op2) {
        return op1 == '*' || op1 == '/' || op2 == '+' || op2 == '-';
    }

    private int compute(int operand2, int operand1, char op) {
        if (op == '*')
            return operand1 * operand2;
        else if (op == '/')
            return operand1/operand2;
        else if (op == '+')
            return operand1 + operand2;
        else
            return operand1 - operand2;
    }

    public static void main(String[] args) {
        BasicCaculator2 basicCaculator2 = new BasicCaculator2();
        int result;

        String s1 = "3+2*2";
        result = basicCaculator2.calculate(s1);
        System.out.println(s1 + " = " + result);

        String s2 = "3/2";
        result = basicCaculator2.calculate(s2);
        System.out.println(s2 + " = " + result);

        String s3 = "3+5 / 2 ";
        result = basicCaculator2.calculate(s3);
        System.out.println(s3 + " = " + result);

        String s4 = "1+3*5 / 2 ";
        result = basicCaculator2.calculate(s4);
        System.out.println(s4 + " = " + result);

        String s5 = "1*2-3/4+5*6-7*8+9/10";
        result = basicCaculator2.calculate(s5);
        System.out.println(s5 + " = " + result);
    }
}
