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
        return dfs(pages, 0, k);
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

    public static void main(String[] args) {
        final CopyBooks s = new CopyBooks();

        System.out.println(s.copyBooks(new int[]{3, 2, 4}, 2));
    }
}
