package org.morewax.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem
 * N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 * Example image (http://www.leetcode.com/wp-content/uploads/2012/03/8-queens.png)

 * Given an integer n, return all distinct solutions to the n-queens puzzle.

 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:

 [
 [".Q..",  // Solution 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // Solution 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 * Created by byuan on 12/18/15.
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();

        if (n > 0) {
            int[] board = new int[n];

            dfs(board, 0, n, results);
        }

        return results;
    }

    private void dfs(int[] board, int row, int boardSize, List<List<String>> results) {
        if (row == boardSize) {
            results.add(getResult(board, boardSize));
            return;
        }

        for (int i = 0; i < boardSize; ++i) {
            board[row] = i;
            if (isSafe(board, row)) {
                dfs(board, row+1, boardSize, results);
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

    private List<String> getResult(int[] board, int boardSize) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < boardSize; ++i) {
            String row = "";
            for (int j = 0; j < boardSize; ++j) {
                if (board[i] == j) {
                    row += "Q";
                } else {
                    row += ".";
                }
            }
            result.add(row);
        }

        return result;
    }

    public void print(List<List<String>> results) {
        results.forEach(result -> {
            System.out.println("");
            result.forEach(System.out::println);
            System.out.println("");
        });
    }

    public static void main(String[] args) {
        final NQueens solution = new NQueens();

        solution.print(solution.solveNQueens(4));
    }
}
