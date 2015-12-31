package org.morewax.lintcode;

import org.morewax.support.ListNode;

/**
 * Problem
 * Remove Duplicates from Sorted List II (http://www.lintcode.com/en/problem/remove-duplicates-from-sorted-list-ii/)
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * Example
 *  Given 1->2->3->3->4->4->5, return 1->2->5.
 *  Given 1->1->1->2->3, return 2->3.
 *
 * Created by byuan on 12/30/15.
 */
public class RemoveDuplicatesFromSortedListII {
    /**
     * @param head is the head of the linked list
     * @return: ListNode head of the linked list
     */
    public ListNode deleteDuplicates(ListNode head) {
        // write your code here
        if (head == null) return head;

        ListNode newHead = null;
        ListNode newTail = null;

        ListNode slow = head;
        ListNode fast = head.next;
        int counter = 1;

        while (fast != null) {
            if (slow.val == fast.val) {
                ++counter;
                fast = fast.next;
            } else {
                if (counter == 1) {
                    if (newTail == null) {
                        newHead = slow;
                    } else {
                        newTail.next = slow;
                    }
                    newTail = slow;
                } else {
                    counter = 1;
                }

                slow = fast;
                fast = fast.next;

            }
        }

        if (counter == 1) {
            if (newTail == null) {
                newHead = slow;
            } else {
                newTail.next = slow;
            }
            newTail = slow;
        }

        if (newTail != null) {
            newTail.next = null;
        }

        return newHead;
    }

    public static void main(String[] args) {
        final RemoveDuplicatesFromSortedListII s = new RemoveDuplicatesFromSortedListII();

        ListNode.print(s.deleteDuplicates(ListNode.asList(new int[]{1, 1, 1, 2, 3})));

        ListNode.print(s.deleteDuplicates(ListNode.asList(new int[]{1, 2, 3, 3, 4, 4, 5})));

        ListNode.print(s.deleteDuplicates(ListNode.asList(new int[]{1})));

        ListNode.print(s.deleteDuplicates(ListNode.asList(new int[]{1, 2})));

        ListNode.print(s.deleteDuplicates(ListNode.asList(new int[]{1, 2, 3, 4, 5})));

        ListNode.print(s.deleteDuplicates(ListNode.asList(new int[]{1, 1, 1})));

        ListNode.print(s.deleteDuplicates(ListNode.asList(new int[]{1, 1, 1, 8})));
    }
}
