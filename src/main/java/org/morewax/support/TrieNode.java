package org.morewax.support;

/**
 * Created by Bing on 11/30/2015.
 */
public class TrieNode<T> {
    public boolean isWord;
    public TrieNode<T>[] children;
    public T value;

    public TrieNode() {
        children = new TrieNode[26];
    }
}
