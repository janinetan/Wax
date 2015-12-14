package org.morewax.study;

import org.morewax.support.TreeNode;

import java.util.Stack;

/**
 * Practices for tree traversal (iterative)
 *
 * Created by Bing on 12/11/2015.
 */
public class Tree {
    private final TreeNode root;
    private final int[] counter;

    public Tree(int[] nums) {
        this.root = TreeNode.buildTree(nums);
        this.counter = new int[1];
    }

    public void PreOrder() {
        counter[0] = 0;
        System.out.print("Preorder (recursive): \t\t");
        PreOrderHelper(root);
        System.out.println("");

        System.out.print("Preorder (iterative): \t\t");
        if (root != null) {
            counter[0] = 0;
            Stack<TreeNode> s = new Stack<>();
            s.push(root);

            while (!s.isEmpty()) {
                TreeNode n = s.pop();
                visitTreeNode(n);

                if (n.right != null) {
                    s.push(n.right);
                }

                if (n.left != null) {
                    s.push(n.left);
                }
            }

            System.out.println("");
        }
    }

    public void PreorderWithParent() {
        counter[0] = 0;
        System.out.print("Preorder (with parent): \t");
        TreeNode current = root;
        TreeNode previous = null;
        TreeNode next;

        while (current != null) {
            if (previous == null || previous.left == current || previous.right == current) { // going down the tree
                visitTreeNode(current);
                if (current.left != null) {
                    next = current.left;
                } else {
                    next = (current.right != null)? current.right : current.parent;
                }
            } else if (current.left == previous) { // going up tree from the left
                next = (current.right != null)? current.right : current.parent;
            } else { // going up tree from the right
                next = current.parent;
            }

            previous = current;
            current = next;
        }

        System.out.println("");
    }

    public void PreorderWithIterator() {
        counter[0] = 0;
        System.out.print("Preorder (with iterator): \t");
        PreorderIterator preorderIterator = this.getPreorderIterator();
        while (preorderIterator.hasNext()) {
            if (counter[0]++ != 0) {
                System.out.print(", ");
            }
            System.out.print(preorderIterator.next());
        }
        System.out.println();
    }

    public void InOrder() {
        counter[0] = 0;
        System.out.print("Inorder (recursive): \t\t");
        InOrderHelper(root);
        System.out.println("");

        counter[0] = 0;
        System.out.print("Inorder (iterative): \t\t");
        Stack<TreeNode> s = new Stack<>();
        TreeNode current = root;

        while (!s.isEmpty() || current != null) {
            if (current != null) {
                s.push(current);
                current = current.left;
            } else {
                TreeNode node = s.pop();
                visitTreeNode(node);
                current = node.right;
            }
        }
        System.out.println("");
    }

    public void InorderWithParent() {
        counter[0] = 0;
        System.out.print("Inorder (with parent): \t\t");
        TreeNode current = root;
        TreeNode previous = null;
        TreeNode next;

        while (current != null) {
            if (previous == null || previous.left == current || previous.right == current) { // going down the tree
                if (current.left != null) {
                    next = current.left;
                } else {
                    visitTreeNode(current);
                    next = (current.right != null)? current.right : current.parent;
                }
            } else if (current.left == previous)  { // going up from the left
                visitTreeNode(current);
                next = (current.right != null)? current.right : current.parent;
            } else { // going up from the right; i.e. current.right == previous
                next = current.parent;
            }
            previous = current;
            current = next;
        }

        System.out.println("");
    }

    public void InorderWithIterator() {
        counter[0] = 0;
        System.out.print("Inorder (with iterator): \t");
        InorderIterator inorderIterator = this.getInorderIterator();

        while (inorderIterator.hasNext()) {
            if (counter[0]++ != 0) {
                System.out.print(", ");
            }
            System.out.print(inorderIterator.next());
        }
        System.out.println("");
    }

    public void PostOrder() {
        counter[0] = 0;
        System.out.print("Postorder (recursive): \t\t");
        PostOrderHelper(root);
        System.out.println("");

        counter[0] = 0;
        System.out.print("Postorder (iterative): \t\t");
        Stack<TreeNode> s = new Stack<>();
        TreeNode current = root;
        TreeNode previous = null;

        while (current != null) {
            for (; current.left != null; current = current.left) {
                s.push(current);
            }

            while (current.right == null || current.right == previous) {
                visitTreeNode(current);
                previous = current;
                if (s.isEmpty()) {
                    current = null;
                    break;
                }
                current = s.pop();
            }

            if (current != null) {
                s.push(current);
                current = current.right;
            }
        }

        System.out.println("");
    }

    public void PostorderWithParent() {
        counter[0] = 0;
        System.out.print("Postorder (with parent): \t");
        TreeNode current = root;
        TreeNode previous = null;
        TreeNode next;

        while (current != null) {
            if (previous == null || previous.left == current || previous.right == current) { // going down the tree
                if (current.left != null) {
                    next = current.left;
                } else if (current.right != null) {
                    next = current.right;
                } else {
                    visitTreeNode(current);
                    next = current.parent;
                }
            } else if (current.left == previous) { // going up from the left
                if (current.right != null) {
                    next = current.right;
                } else {
                    visitTreeNode(current);
                    next = current.parent;
                }
            } else { // going up from the right
                visitTreeNode(current);
                next = current.parent;
            }

            previous = current;
            current = next;
        }
        System.out.println("");
    }

    public void PostorderWithIterator() {
        counter[0] = 0;
        System.out.print("Postorder (with iterator): \t");
        PostorderIterator postorderIterator = this.getPostorderIterator();

        while (postorderIterator.hasNext()) {
            if (counter[0]++ != 0) {
                System.out.print(", ");
            }
            System.out.print(postorderIterator.next());
        }
        System.out.println("");
    }

    private void PreOrderHelper(TreeNode current) {
        if (current == null) return;

        visitTreeNode(current);
        PreOrderHelper(current.left);
        PreOrderHelper(current.right);
    }

    private void InOrderHelper(TreeNode current) {
        if (current == null) return;

        InOrderHelper(current.left);
        visitTreeNode(current);
        InOrderHelper(current.right);
    }

    private void PostOrderHelper(TreeNode current) {
        if (current == null) return;

        PostOrderHelper(current.left);
        PostOrderHelper(current.right);
        visitTreeNode(current);
    }

    private void visitTreeNode(TreeNode node) {
        if (counter[0]++ != 0) {
            System.out.print(", ");
        }
        System.out.print(node.val);
    }

    public PreorderIterator getPreorderIterator() {
        return new PreorderIterator(this.root);
    }

    private static class PreorderIterator {
        private TreeNode current;

        public PreorderIterator(TreeNode root) {
            this.current = root;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public int next() {
            int ret = current.val;
            if (current.left != null) {
                current = current.left;
            } else if (current.right != null) {
                current = current.right;
            } else {
                TreeNode parent = current.parent;
                TreeNode child = current;
                while (parent != null && (parent.right == child || parent.right == null)) {
                    child = parent;
                    parent = parent.parent;
                }
                if (parent != null) {
                    current = parent.right;
                } else {
                    current = parent;
                }
            }
            return ret;
        }
    }

    public InorderIterator getInorderIterator() {
        return new InorderIterator(this.root);
    }

    private static class InorderIterator {
        private TreeNode current;

        public InorderIterator(TreeNode root) {
            this.current = getLeftMost(root);
        }

        public boolean hasNext() {
            return (this.current != null);
        }

        public int next() {
            int ret = current.val;

            current = getSuccessor(current);

            return ret;
        }

        private TreeNode getSuccessor(TreeNode current) {
            if (current.right != null) {
                return getLeftMost(current.right);
            } else {
                TreeNode parent = current.parent;

                while (parent != null && parent.right == current) {
                    current = parent;
                    parent = parent.parent;
                }

                return parent;
            }
        }

        private TreeNode getLeftMost(TreeNode current) {
            while (current != null && current.left != null) {
                current = current.left;
            }

            return current;
        }
    }

    public PostorderIterator getPostorderIterator() {
        return new PostorderIterator(this.root);
    }

    private static class PostorderIterator {
        private TreeNode current;

        public PostorderIterator(TreeNode root) {
            this.current = getFirst(root);
        }

        public boolean hasNext() {
            return (this.current != null);
        }

        public int next() {
            int ret = current.val;

            current = getSuccessor(current);

            return ret;
        }

        private TreeNode getSuccessor(TreeNode current) {
            TreeNode parent = current.parent;

            if (parent != null) {
                if (parent.right == current || parent.right == null) {
                    return parent;
                } else {
                    return getFirst(parent.right);
                }
            } else{
                return null;
            }
        }

        private TreeNode getFirst(TreeNode current) {
            while (current != null && (current.left != null || current.right != null)) {
                if (current.left != null) {
                    current = current.left;
                }  else {
                    current = current.right;
                }
            }

            return current;
            /*
            while (current != null && current.left != null) {
                current = current.left;
            }

            if (current.right != null) {
                return getFirst(current.right);
            } else {
                return current;
            }*/
        }
    }

    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 4, 5, 6, 7, 9, 10};
        Tree tree = new Tree(A);

        tree.PreOrder();
        tree.PreorderWithParent();
        tree.PreorderWithIterator();
        System.out.println("");

        tree.InOrder();
        tree.InorderWithParent();
        tree.InorderWithIterator();
        System.out.println("");

        tree.PostOrder();
        tree.PostorderWithParent();
        tree.PostorderWithIterator();
    }
}
