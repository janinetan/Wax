package org.morewax.leetcode;

/**
 * Problem
 * Word Search
 * Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

 For example,
 Given board =

 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]
 word = "ABCCED", -> returns true,
 word = "SEE", -> returns true,
 word = "ABCB", -> returns false.
 *
 * Created by Bing on 11/30/2015.
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (dfs(board, i, j, visited, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int x, int y, boolean[][] visited, String word) {
        if (x < 0) return word.isEmpty();
        if (x >= board.length) return word.isEmpty();
        if (y < 0) return word.isEmpty();
        if (y >= board[0].length) return word.isEmpty();

        if (word.isEmpty()) return true;

        if (board[x][y] != word.charAt(0) || visited[x][y]) return false;

        visited[x][y] = true;
        if (dfs(board, x-1, y, visited, word.substring(1))) return true;
        if (dfs(board, x+1, y, visited, word.substring(1))) return true;
        if (dfs(board, x, y-1, visited, word.substring(1))) return true;
        if (dfs(board, x, y+1, visited, word.substring(1))) return true;
        visited[x][y] = false;

        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };

        WordSearch wordSearch = new WordSearch();
        boolean result;

        result = wordSearch.exist(board, "ABCCED");
        System.out.println(result);

        result = wordSearch.exist(board, "SEE");
        System.out.println(result);

        result = wordSearch.exist(board, "ABCB");
        System.out.println(result);
    }
}
