package org.morewax.lintcode;

import org.morewax.support.ListNode;

/**
 * Problem
 * Reverse Linked List II ()
 * Reverse a linked list from position m to n.
 * Example
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL.
 * Note
 *  Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
 *
 * The following implementation is a one-pass solution. It's NOT recommended to try this solution first during real
 * waxing, because you would need a clear mind to get it right.
 * Created by byuan on 12/30/15.
 */
public class ReverseLinkedListII {
    /**
     * @param head is the head of the linked list
     * @oaram m and n
     * @return: The head of the reversed linked list
     */
    public ListNode reverseBetween(ListNode head, int m , int n) {
        // write your code
        ListNode current = head;
        ListNode prev = null;

        for (int i = 0; i < m-1; ++i) {
            prev = current;
            current = current.next;
        }

        ListNode newHead = reverseList(current, current.next, n-m-1);

        if (prev != null) {
            prev.next = newHead;
            return head;
        } else {
            return newHead;
        }
    }

    private ListNode reverseList(ListNode prev,  ListNode head, int count) {
        if (count < 0) {
            return prev;
        }

        ListNode tail = prev;
        ListNode current = head;
        ListNode next;

        do {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            --count;
        } while (count >= 0);

        tail.next = next;

        return prev;
    }

    public static void main(String[] args) {
        final ReverseLinkedListII s = new ReverseLinkedListII();

        ListNode.print(s.reverseBetween(ListNode.asList(new int[]{1, 2, 3, 4, 5}), 2, 4));

        ListNode.print(s.reverseBetween(ListNode.asList(new int[]{1, 2, 3, 4, 5}), 2, 2));

        ListNode.print(s.reverseBetween(ListNode.asList(new int[]{1, 2, 3, 4, 5}), 1, 5));

        ListNode.print(s.reverseBetween(ListNode.asList(new int[]{1}), 1, 1));
    }
}
