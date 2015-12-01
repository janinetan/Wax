package org.morewax.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem
 * Word Search II
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 For example,
 Given words = ["oath","pea","eat","rain"] and board =

 [
 ['o','a','a','n'],
 ['e','t','a','e'],
 ['i','h','k','r'],
 ['i','f','l','v']
 ]
 Return ["eat","oath"].
 Note:
 You may assume that all inputs are consist of lowercase letters a-z.

 click to show hint.

 You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

 If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently?
 Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.

 * Created by Bing on 11/30/2015.
 */
public class WordSearch2 {
    private Trie trie;

    public List<String> findWords(char[][] board, String[] words) {
        trie = new Trie();

        for (int i = 0; i < words.length; ++i) {
            trie.insert(words[i]);
        }

        // It may appear to be odd to use Set here. The main reason is that
        // There might be more than one path that matches a word in the dictionary.
        // For example, if the board is like this:
        // ['a', 'a'] and dictionary is ["a"].
        // Then there are TWO paths that both match the word "a" in dictionary.
        // Use Set instead of List could de-dup such case.
        Set<String> results = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs(board, visited, i, j, "", trie, results);
            }
        }

        return new ArrayList<>(results);
    }

    private void dfs(char[][] board, boolean[][] visited, int x, int y, String result, Trie trie, Set<String> results) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) return; // out of boundary of board or has visited this position

        result += board[x][y];

        if (!trie.startsWith(result)) return; // the prefix is invalid;

        if (trie.search(result)) {
            results.add(result);
        }

        visited[x][y] = true;

        dfs(board, visited, x-1, y, result, trie, results);
        dfs(board, visited, x+1, y, result, trie, results);
        dfs(board, visited, x, y-1, result, trie, results);
        dfs(board, visited, x, y+1, result, trie, results);

        visited[x][y] = false;
    }

    public static void main(String[] args) {
        WordSearch2 wordSearch2 = new WordSearch2();
        List<String> results = null;

        char[][] board = new char[][] {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

        String[] words = new String[]{"oath","pea","eat","rain"};
        results = wordSearch2.findWords(board, words);
        final int[] counter = {0};
        System.out.print("[");
        results.forEach(word -> {
            if (counter[0]++ != 0) {
                System.out.print(", ");
            }
            System.out.print(word);
        });
        System.out.println("]");
    }
}
