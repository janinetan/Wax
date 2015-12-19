package org.morewax.leetcode;

/**
 * Problem
 * N-Queens II (https://leetcode.com/problems/n-queens-ii/)
 * Follow up for N-Queens problem.
 * Example image: http://www.leetcode.com/wp-content/uploads/2012/03/8-queens.png
 * Now, instead outputting board configurations, return the total number of distinct solutions.

 * Created by byuan on 12/18/15.
 */
public class NQueens2 {
    public int totalNQueens(int n) {
        int[] result = new int[1];

        if (n > 0) {
            int[] board = new int[n];
            dfs(board, 0, n, result);
        }

        return result[0];
    }

    private void dfs(int[] board, int currentRow, int boardSize, int[] result) {
        if (currentRow == boardSize) {
            ++result[0];
            return;
        }

        for (int col = 0; col < boardSize; ++col) {
            board[currentRow] = col;
            if (isSafe(board, currentRow)) {
                dfs(board, currentRow+1, boardSize, result);
            }
        }
    }

    private boolean isSafe(int[] board, int currentRow) {
        boolean result = true;

        for (int row = 0; row < currentRow; ++row) {
            if (board[row] == board[currentRow]) { // same column
                result = false;
                break;
            } else {
                if (currentRow-row == Math.abs(board[currentRow]-board[row])) { // diagonal
                    result = false;
                    break;
                }
            }
        }

        return result;
    }
}
