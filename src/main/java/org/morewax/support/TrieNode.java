package org.morewax.support;

/**
 * Created by Bing on 11/30/2015.
 */
public class TrieNode {
    public String item;
    public TrieNode[] children;

    public TrieNode() {
        children = new TrieNode[26];
        item = "";
    }
}
