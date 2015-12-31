package org.morewax.lintcode;

import org.morewax.support.ListNode;

/**
 * Problem
 * Linked List Cycle II ()
 * Given a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 * Example
 * Given -21->10->4->5, tail connects to node index 1，return 10
 *
 * Created by byuan on 12/30/15.
 */
public class LinkedListCycleII {
    /**
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins.
     *           if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {
        // write your code here
        if (head == null) return head;

        ListNode slow = head;
        ListNode fast = head;

        fast = fast.next;
        if (fast != null) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }

            if (slow == fast) {
                int cycleLength = 0;

                do {
                    ++cycleLength;
                    fast = fast.next;
                } while (fast != slow);

                slow = head;
                fast = head;
                while (cycleLength-- != 0) {
                    fast = fast.next;
                }

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }

        return null;
    }

    public ListNode getNodeAtIndex(ListNode head, int index) {
        ListNode current = head;

        for (int i = 0; ((index == -1)? (current.next != null) : (i < index-1)); ++i) {
            current = current.next;
        }

        return current;
    }

    public static void main(String[] args) {
        final LinkedListCycleII s = new LinkedListCycleII();
        ListNode n = null;

        /*
        ListNode l1 = ListNode.asList(new int[]{1});
        n = s.detectCycle(l1);
        if (n == null) {
            System.out.println("No cycle");
        } else {
            System.out.println("Starting node value: " + n.val);
        }*/

        ListNode l2 = ListNode.asList(new int[]{-21, 10, 17, 8, 4, 26, 5,
                35, 33, -7, -16, 27, -12, 6, 29,
                -12, 5, 9, 20, 14, 14, 2,
                13, -24, 21, 23, -21, 105});
        ListNode tail = s.getNodeAtIndex(l2, -1);
        System.out.println("tail: " + tail.val);
        ListNode node = s.getNodeAtIndex(l2, 24);
        System.out.println("node: " + node.val);
        tail.next = node;
        n = s.detectCycle(l2);
        if (n == null) {
            System.out.println("No cycle");
        } else {
            System.out.println("Starting node value: " + n.val);
        }
    }
}
