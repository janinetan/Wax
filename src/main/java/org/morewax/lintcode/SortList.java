package org.morewax.lintcode;

import org.morewax.support.ListNode;

/**
 * Problem
 * Sort Singly Linked List(http://www.lintcode.com/en/problem/sort-list/)
 * Sort a linked list in O(n log n) time using constant space complexity.
 * Example
 * Given 1-3->2->null, sort it to 1->2->3->null.
 *
 * Created by byuan on 12/29/15.
 */
public class SortList {
    /**
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list, using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        // write your code here
        int length = getLength(head);
        if (length <= 1) return head;

        int steps = length/2;
        ListNode fast = head;
        ListNode prev = null;
        for (int i = 0; i < steps; ++i) {
            prev = fast;
            fast = fast.next;
        }

        prev.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(fast);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode head = null;
        ListNode tail = null;

        if (left == null) return right;
        if (right == null) return left;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                if (tail == null) {
                    head = left;
                } else {
                    tail.next = left;
                }
                tail = left;
                left = left.next;
                tail.next = null;
            } else {
                if (tail == null) {
                    head = right;
                } else {
                    tail.next = right;
                }
                tail = right;
                right = right.next;
                tail.next = null;
            }
        }

        if (left != null) {
            tail.next = left;
        }

        if (right != null) {
            tail.next = right;
        }

        return head;
    }

    private int getLength(ListNode head) {
        int length = 0;

        while (head != null) {
            ++length;
            head = head.next;
        }

        return length;
    }

    public static void main(String[] args) {
        final SortList s = new SortList();

        int[] a1 = new int[]{1, 3, 2, 4};
        ListNode.print(s.sortList(ListNode.asList(a1)));

        int[] a2 = new int[]{2, 1};
        ListNode.print(s.sortList(ListNode.asList(a2)));

        int[] a3 = new int[]{1, 3, 2, 7, 5, 6, 10, 4, 20};
        ListNode.print(s.sortList(ListNode.asList(a3)));
    }
}
