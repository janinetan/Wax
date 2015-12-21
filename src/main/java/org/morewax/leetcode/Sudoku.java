package org.morewax.leetcode;

/**
 * Problem
 * Sudoku Solver (https://leetcode.com/problems/sudoku-solver/)
 * Write a program to solve a Sudoku puzzle by filling the empty cells.

 Empty cells are indicated by the character '.'.

 You may assume that there will be only one unique solution.

 Sample image before calling the solver: http://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png

 A sudoku puzzle...

 Sample image after calling the solver:
 http://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png
 ...and its solution numbers marked in red.
 * Created by byuan on 12/21/15.
 */
public class Sudoku {
    public void solveSudoku(char[][] board) {
        helper(board);
    }

    private boolean helper(char[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == '.') {
                    for (int k = 1; k <= 9; ++k) {
                        board[i][j] = (char)('0' + k);

                        if (isValidSudoku(board) && helper(board)) return true;
                    }

                    board[i][j] = '.';

                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (!isValid(board, 3*i, 3*j, 3*(i+1), 3*(j+1))) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 9; ++i) {
            if (!isValid(board, i, 0, i+1, 9)) {
                return false;
            }
        }

        for (int i = 0; i < 9; ++i) {
            if (!isValid(board, 0, i, 9, i+1)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValid(char[][] board, int xStart, int yStart, int xEnd, int yEnd) {
        int[] used = new int[9];

        for (int x = xStart; x < xEnd; ++x) {
            for (int y = yStart; y < yEnd; ++y) {
                if (board[x][y] != '.') {
                    if (used[board[x][y] - '1'] == 0) {
                        used[board[x][y] - '1'] = 1;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
