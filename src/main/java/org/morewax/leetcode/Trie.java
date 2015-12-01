package org.morewax.leetcode;

import org.morewax.support.TrieNode;

import java.util.Map;

/**
 * Created by Bing on 11/30/2015.
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            if (node.children[c-'a'] == null) {
                node.children[c-'a'] = new TrieNode();
            }
            node = node.children[c-'a'];
        }

        node.item = word;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            if (node.children[c-'a'] == null) {
                return false;
            }

            node = node.children[c-'a'];
        }

        return node.item.equals(word);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (char c : prefix.toCharArray()) {
            if (node.children[c-'a'] == null) {
                return false;
            }
            node = node.children[c-'a'];
        }

        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        boolean result;

        trie.insert("a");
        result = trie.search("a");
        System.out.println(result);
        result = trie.startsWith("a");
        System.out.println(result);
    }
}