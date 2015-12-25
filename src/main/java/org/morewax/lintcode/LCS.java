package org.morewax.lintcode;

/**
 * Created by Bing on 12/24/2015.
 */
public class LCS {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common subsequence.
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        if (A == null || A.length() == 0 || B == null || B.length()== 0) return 0;

        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (A.charAt(i-1) == B.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];
    }

    // more memory efficient version.
    // observe that in the state transition, only two rows are involved.
    public int longestCommonSubsequence2(String A, String B) {
        if (A == null || A.length() == 0 || B == null || B.length()== 0) return 0;

        int m = A.length();
        int n = B.length();

        int[][] dp = new int[2][n+1];

        int previousRow = 0;
        int currentRow = 0;

        for (int i = 1; i <= m; ++i) {
            previousRow = currentRow;
            currentRow = (currentRow+1)%2;

            //System.out.println("PrevRow: " + previousRow + "; CurrRow: " + currentRow);
            for (int j = 1; j <= n; ++j) {
                if (A.charAt(i-1) == B.charAt(j-1)) {
                    dp[currentRow][j] = dp[previousRow][j-1] + 1;
                } else {
                    dp[currentRow][j] = Math.max(dp[previousRow][j], dp[currentRow][j-1]);
                }
            }
        }

        return dp[currentRow][n];
    }

    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        if (A == null || A.length() == 0 || B == null || B.length() == 0) return 0;

        int m = A.length();
        int n = B.length();

        int maxLength = 0;
        int[][] dp = new int[2][n+1];

        int previousRow = 0;
        int currentRow = 0;

        for (int i = 1; i <= m; ++i) {
            previousRow = currentRow;
            currentRow = (currentRow+1)%2;

            //System.out.println("PrevRow: " + previousRow + "; CurrRow: " + currentRow);
            for (int j = 1; j <= n; ++j) {
                if (A.charAt(i-1) == B.charAt(j-1)) {
                    dp[currentRow][j] = dp[previousRow][j-1] + 1;
                    maxLength = Math.max(maxLength, dp[currentRow][j]);
                } else {
                    dp[currentRow][j] = 0;
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        final LCS s = new LCS();

        String s1 = "www.lintcode.com code";
        String s2 = "www.ninechapter.com code";

        System.out.println("Longest Common Subsequence: " + s.longestCommonSubsequence(s1, s2));
        System.out.println("Longest Common Subsequence: " + s.longestCommonSubsequence2(s1, s2));
        System.out.println("Longest Common Substring: " + s.longestCommonSubstring(s1, s2));
    }
}
