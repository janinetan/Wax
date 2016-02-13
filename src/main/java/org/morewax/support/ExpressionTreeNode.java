package org.morewax.support;

/**
 * Created by Bing on 2/12/2016.
 */
public class ExpressionTreeNode {
    public String symbol;
    public ExpressionTreeNode left;
    public ExpressionTreeNode right;

    public ExpressionTreeNode(String symbol) {
        this.symbol = symbol;
        this.left = this.right = null;
    }
}
