package org.morewax.lintcode;


/**
 * Problem
 * Copy Books (http://www.lintcode.com/en/problem/copy-books/)
 *  Copy Books
 *  Given an array A of integer with size of n( means n books and number of pages of each book) and k people to copy
 *  the book. You must distribute the continuous id books to one people to copy. (You can give book A[1],A[2] to one
 *  people, but you cannot give book A[1], A[3] to one people, because book A[1] and A[3] is not continuous.)
 *  Each person have can copy one page per minute. Return the number of smallest minutes need to copy all the books.
 *  Example
 *   Given array A = [3,2,4], k = 2.
 *   Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes
 *   to copy book 3. )
 *  Challenge
 *      Could you do this in O(n*k) time ?
 *  The binary search version is inspired by this topcoder article: https://www.topcoder.com/community/data-science/data-science-tutorials/binary-search/
 * Created by byuan on 1/19/16.
 */
public class CopyBooks {
    /**
     * @param pages: an array of integers
     * @param k: an integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        // write your code here
        /**
         * for each book i, assign [0..i] to 1st available copier, assign the rest of books to the remaining copiers.
         * repeat the above strategy until:
         *  1. no more books;
         *  2. no more copier
         */
        //return dfs(pages, 0, k);
        //return dpHelper(pages, k);
        return binarySearch(pages, k);
    }

    private int dfs(int[] pages, int start, int numOfCopiers) {
        if (start == pages.length) return 0;
        if (numOfCopiers == 0) return Integer.MAX_VALUE;

        /*
        if (numOfCopiers == 1) {
            int sum = 0;
            for (int i = start; i < pages.length; ++i) {
                sum += pages[i];
            }

            return sum;
        }*/

        int result = Integer.MAX_VALUE;
        int prefixSum = 0;
        for (int i = start; i < pages.length; ++i) {
            prefixSum += pages[i];
            result = Math.min(result, Math.max(prefixSum, dfs(pages, i + 1, numOfCopiers - 1)));
        }

        return result;
    }

    private int dpHelper(int[] pages, int k) {
        int n = pages.length;
        k = Math.min(k, n);
        int[][] dp = new int[k+1][n+1];

        for (int i = 0; i <=n; ++i) {
            dp[0][i] = (i == 0)? 0 : Integer.MAX_VALUE;
        }

        for (int i = 1; i <= k; ++i) {
            for (int j = 1; j <= n; ++j) {
                int sum = 0;
                dp[i][j] = Integer.MAX_VALUE;
                for (int l = 0; l < j; ++l) {
                    sum += pages[j-1-l];
                    dp[i][j] = Math.min(dp[i][j], Math.max(sum, dp[i-1][j-l-1]));
                }
            }
        }

        return dp[k][n];
    }

    private int binarySearch(int[] pages, int k) {
        long maxPages = 0;
        long totalPages = 0;

        for (int i = 0; i < pages.length; ++i) {
            maxPages = Math.max(maxPages, (long)pages[i]);
            totalPages += (long)pages[i];
        }

        long l = maxPages;
        long h = totalPages;

        while (l < h) {
            long m = l + (h-l)/2;

            long sum = 0;
            int requiredWorkers = 1;

            for (int i = 0; i < pages.length; ++i) {
                if ((sum+pages[i]) <= m) {
                    sum += pages[i];
                } else {
                    ++requiredWorkers;
                    sum = pages[i];
                }
            }

            if (requiredWorkers <= k) {
                h = m;
            } else {
                l = m+1;
            }
        }

        return (int)l;
    }

    public static void main(String[] args) {
        final CopyBooks s = new CopyBooks();

        System.out.println(s.copyBooks(new int[]{3, 2, 4}, 2));
    }
}
