package org.morewax.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem
 * Text Justification
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

 Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Return the formatted lines as:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 Note: Each word is guaranteed not to exceed L in length.
 * Created by byuan on 11/30/15.
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> results = new ArrayList<>();

        if (words.length == 0) return results;

        List<String> oneLine = new ArrayList<>();
        int totalLength = 0; // the total length of words in @{oneLine}

        int i = 0;

        while (i < words.length) {
            if (words[i].length() > maxWidth) return results;

            int newLength = totalLength + (oneLine.isEmpty()? 0 : oneLine.size()) + words[i].length();
            if (newLength <= maxWidth) { // can still fit into current line
                oneLine.add(words[i]);
                totalLength += words[i].length();
                ++i;
            } else { // current word cannot fit into current line
                String result = getLine(oneLine, totalLength, maxWidth, false);
                results.add(result);
                oneLine.clear();
                totalLength = 0;
            }
        }

        results.add(getLine(oneLine, totalLength, maxWidth, true));

        return results;
    }

    private String getLine(List<String> oneLine, int totalLength, int maxWidth, boolean isLastLine) {
        int totalNumberOfSpaces = maxWidth - totalLength;
        if (oneLine.size() == 1) {
            return oneLine.get(0) + padStringWithSpace(totalNumberOfSpaces);
        } else if (!isLastLine){
            int numberOfSpaces = oneLine.size()-1; // total number of places where we need to insert spaces
            int averageNumberOfSpaces = totalNumberOfSpaces/numberOfSpaces;
            int additionalSpaces = totalNumberOfSpaces-averageNumberOfSpaces*numberOfSpaces; // total number of additional spaces that need to be inserted.

            String result = oneLine.get(0);

            for (int i = 1; i < oneLine.size(); ++i) {
                if (additionalSpaces-- > 0) {
                    result += padStringWithSpace(averageNumberOfSpaces+1);
                } else {
                    result += padStringWithSpace(averageNumberOfSpaces);
                }
                result += oneLine.get(i);
            }

            return result;
        } else {
            String result = oneLine.get(0);

            for (int i = 1; i < oneLine.size(); ++i) {
                result += " ";
                result += oneLine.get(i);
                --totalNumberOfSpaces;
            }

            result += padStringWithSpace(totalNumberOfSpaces);

            return result;
        }
    }

    private String padStringWithSpace(int numberOfSpaces) {
        if (numberOfSpaces == 0) {
            return "";
        } else {
            char[] spaces = new char[numberOfSpaces];
            Arrays.fill(spaces, ' ');
            return new String(spaces);
        }
    }
    public static void main(String[] args) {
        TextJustification tj = new TextJustification();
        int L;


        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        L = 16;
        List<String> result = tj.fullJustify(words, L);
        result.forEach(oneLine -> {
            System.out.print("\"");
            System.out.print(oneLine);
            System.out.println("\"");
        });

        String[] words2 = new String[]{""};
        L = 0;
        List<String> result2 = tj.fullJustify(words2, L);
        result2.forEach(oneLine -> {
            System.out.print("\"");
            System.out.print(oneLine);
            System.out.println("\"");
        });

        String[] words3 = new String[]{"a"};
        L = 1;
        List<String> result3 = tj.fullJustify(words3, L);
        result3.forEach(oneLine -> {
            System.out.print("\"");
            System.out.print(oneLine);
            System.out.println("\"");
        });

        String[] words4 = new String[]{"What", "must", "be", "shall", "be."};
        L = 12;
        List<String> result4 = tj.fullJustify(words4, L);
        result4.forEach(oneLine -> {
            System.out.print("\"");
            System.out.print(oneLine);
            System.out.println("\"");
        });

        String[] words5 = new String[]{"Imagination","is","more","important","than","knowledge."};
        L = 14;
        List<String> result5 = tj.fullJustify(words5, L);
        result5.forEach(oneLine -> {
            System.out.print("\"");
            System.out.print(oneLine);
            System.out.println("\"");
        });

        String[] words6= new String[]{"Don't","go","around","saying","the","world","owes","you","a","living;","the","world","owes","you","nothing;","it","was","here","first."};
        L = 30;
        List<String> result6 = tj.fullJustify(words6, L);
        result6.forEach(oneLine -> {
            System.out.print("\"");
            System.out.print(oneLine);
            System.out.println("\"");
        });
    }

}
