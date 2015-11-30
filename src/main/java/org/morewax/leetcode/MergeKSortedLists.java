package org.morewax.leetcode;

import org.morewax.support.ListNode;

/**
 * Created by Bing on 11/27/2015.
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        return helper(lists, 0, lists.length-1);
    }

    private ListNode helper(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        }

        if (start == end) {
            return lists[start];
        }

        int mid = start + (end-start)/2;

        ListNode left = helper(lists, start, mid);
        ListNode right = helper(lists, mid+1, end);

        return mergeTwoLists(left, right);
    }

    private ListNode mergeTwoLists(ListNode left, ListNode right) {
        if (left != null && right == null) return left;
        if (right != null && left == null) return right;

        ListNode[] newList = new ListNode[2];

        while (left != null && right != null) {
            if (left.val <= right.val) {
                ListNode next = left.next;
                append(newList, left);
                left = next;
            } else {
                ListNode next = right.next;
                append(newList, right);
                right = next;
            }
        }

        if (left != null) {
            append(newList, left);
        }

        if (right != null) {
            append(newList, right);
        }

        return newList[0];
    }

    private void append(ListNode[] newList, ListNode node) {
        if (newList[1] == null) {
            newList[0] = node;
        } else {
            newList[1].next = node;
        }

        newList[1] = node;
    }
}
