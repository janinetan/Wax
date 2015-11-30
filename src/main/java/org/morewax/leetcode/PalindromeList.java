package org.morewax.leetcode;

import org.morewax.support.ListNode;

/**
 * Created by Bing on 11/27/2015.
 */
public class PalindromeList {
    public boolean isPalindrome(ListNode head) {
        int n = getLength(head);

        if (n <= 1) return true;

        ListNode current = head;
        ListNode prev = null;
        ListNode secondHalf;
        int steps;

        if (n%2 == 0) { // even number of nodes
            steps = n/2;
        } else {
            steps = n/2 + 1;
        }

        for (int step = 1; step <= steps; ++step) {
            prev = current;
            current = current.next;
        }

        secondHalf = reverse(current);

        prev.next = secondHalf;
        current = head;

        while (current != null && secondHalf != null) {
            if (current.val != secondHalf.val) return false;
            current = current.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    private int getLength(ListNode head) {
        int count = 0;

        while (head != null) {
            ++count;
            head = head.next;
        }

        return count;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
