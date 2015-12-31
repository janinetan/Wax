package org.morewax.lintcode;

import org.morewax.support.ListNode;

/**
 * Problem
 * Rotate List (http://www.lintcode.com/en/problem/rotate-list/)
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * Example
 * Given 1->2->3->4->5 and k = 2, return 4->5->1->2->3.
 *
 */
public class RotateList {
    /**
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        // write your code here
        int length = getLength(head);

        if (length == 0) return head;

        k = k%length;
        if (k == 0) return head;

        ListNode slow = head;
        ListNode fast = head;

        for (int i = 0; i < k; ++i) {
            fast = fast.next;
        }

        if (fast == null) {
            return head;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode newHead = (k == 1)? fast : slow.next;
        slow.next = null;
        fast.next = head;

        return newHead;
    }

    private int getLength(ListNode head) {
        int length = 0;

        while (head != null) {
            ++length;
            head = head.next;
        }

        return length;
    }
}