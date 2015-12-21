package org.morewax.leetcode;

import java.util.Arrays;

/**
 * Problem
 * Scramble String (https://leetcode.com/problems/scramble-string/)
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

 Below is one possible representation of s1 = "great":

 great
 /    \
 gr    eat
 / \    /  \
 g   r  e   at
 / \
 a   t
 To scramble the string, we may choose any non-leaf node and swap its two children.

 For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

 rgeat
 /    \
 rg    eat
 / \    /  \
 r   g  e   at
 / \
 a   t
 We say that "rgeat" is a scrambled string of "great".

 Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

 rgtae
 /    \
 rg    tae
 / \    /  \
 r   g  ta  e
 / \
 t   a
 We say that "rgtae" is a scrambled string of "great".

 Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 * Created by Bing on 12/20/2015.
 */
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        if (s1.length() == 1) {
            return s1.charAt(0) == s2.charAt(0);
        }

        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        if (!(new String(a1)).equals(new String(a2))) {
            return false;
        }

        for (int i = 1; i < s1.length(); ++i) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i);
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i);

            if (isScramble(s11, s21) && isScramble(s12, s22)) {
                return true;
            }

            s21 = s2.substring(0, s2.length()-i);
            s22 = s2.substring(s2.length()-i);

            if (isScramble(s11, s22) && isScramble(s12, s21)) {
                return true;
            }
        }

        return false;
    }

    // The above implementation is a recursive solution that involves that repeated calculations that could be avoided by
    // using memoization. or DP. Here is the DP solution
    public boolean isScrambleDP(String s1, String s2) {
        int len = s1.length();
        if(len != s2.length()){
            return false;
        }
        if(len == 0){
            return true;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        // canTransform 第一维为子串的长度delta，第二维为s1的起始索引，第三维为s2的起始索引
        // canTransform[k][i][j]表示s1[i...i+k]是否可以由s2[j...j+k]变化得来。
        boolean[][][] canT = new boolean[len][len][len];
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){    // 如果字符串总长度为1，则取决于唯一的字符是否想到
                canT[0][i][j] = c1[i] == c2[j];
            }
        }

        for(int k=2; k<=len; k++){       // 子串的长度
            for(int i=len-k; i>=0; i--){         // s1[i...i+k]
                for(int j=len-k; j>=0; j--){ // s2[j...j+k]
                    boolean canTransform = false;
                    for(int m=1; m<k; m++){  // 尝试以m为长度分割子串
                        // canT[k][i][j]
                        canTransform = (canT[m-1][i][j] && canT[k-m-1][i+m][j+m]) ||    // 前前后后匹配
                                (canT[m-1][i][j+k-m] && canT[k-m-1][i+m][j]); // 前后后前匹配
                        if(canTransform){
                            break;
                        }
                    }
                    canT[k-1][i][j] = canTransform;
                }
            }
        }

        return canT[len-1][0][0];
    }

    public static void main(String[] args) {
        final ScrambleString s = new ScrambleString();

        System.out.println(s.isScramble("great", "rgtae"));
        System.out.println(s.isScrambleDP("great", "rgtae"));
    }
}
