package org.morewax.baozi;

import java.util.Stack;

/**
 * Problem
 * Regular Expression Matching (http://baozitraining.org/blog/regular-expression-matching-problems/)
 *
 * Created by Bing on 11/28/2015.
 */
public class RegularExpression {
    public void returnAllUsingStack(String input) {
        if(input == null || input.isEmpty()){
            return;
        }

        Stack<Integer> positionToReVisit = new Stack<>();
        positionToReVisit.push(-1);

        StringBuilder currentString = new StringBuilder();
        int currStartPosition = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '?') {
                positionToReVisit.push(i);
                currentString.append(0);
                break;
            }
            currentString.append(input.charAt(i));
            currStartPosition++;
        }

        if (currentString.length() == input.length()) {
            System.out.println(currentString);
            return;
        }

        while (!positionToReVisit.isEmpty()) {
            for (int i = currStartPosition + 1; i < input.length(); i++) {
                if (input.charAt(i)== '?') {
                    currentString.append('0');
                    positionToReVisit.push(i);
                } else {
                    currentString.append(input.charAt(i));
                }
            }

            if (currentString.length() == input.length()) {
                System.out.println(currentString);
            }

            currStartPosition = positionToReVisit.pop();

            if (currStartPosition == -1) break;

            currentString = new StringBuilder(currentString.substring(0, currStartPosition));
            currentString.append('1');
        }

        return;
    }

    public static void main(String[] args) {
        RegularExpression regularExpression = new RegularExpression();
        regularExpression.returnAllUsingStack("1??");
    }
}
