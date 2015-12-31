package org.morewax.support;

/**
 * Created by Bing on 11/27/2015.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode asList(int[] A) {
        ListNode head = null;
        ListNode tail = null;

        for (int i = 0; i < A.length; ++i) {
            ListNode n = new ListNode(A[i]);

            if (tail == null) {
                head = n;
            } else {
                tail.next = n;
            }

            tail = n;
        }

        return head;
    }

    public static void print(ListNode head) {
        if (head == null) {
            System.out.println("List: empty");
            return;
        }

        int[] counter = new int[1];

        System.out.print("List: ");
        while (head != null) {
            if (counter[0]++ != 0) {
                System.out.print("->");
            }
            System.out.print(head.val);
            head = head.next;
        }
        System.out.println("");
    }
}
