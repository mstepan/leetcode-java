package com.github.mstepan.leetcode.medium;

/**
 * 86. Partition List
 *
 * <p>https://leetcode.com/problems/partition-list
 */
public class PartitionList {

    /**
     * time: O(N)
     *
     * <p>space: O(1)
     */
    public static ListNode partition(ListNode head, int value) {

        ListNode lessHead = null;
        ListNode lessTail = null;

        ListNode cur = head;
        ListNode prev = null;

        while (cur != null) {
            ListNode nextCur = cur.next;

            if (cur.val < value) {
                if (prev == null) {
                    head = deleteHead(head);
                }

                if (lessHead == null) {
                    lessHead = cur;
                    lessTail = cur;
                } else {
                    lessTail.next = cur;
                    lessTail = cur;
                }

                lessTail.next = null;

                if (prev != null) {
                    prev.next = nextCur;
                }

                cur = nextCur;

            } else {
                prev = cur;
                cur = nextCur;
            }
        }

        return combine(lessHead, lessTail, head);
    }

    private static ListNode deleteHead(ListNode head) {
        ListNode newHead = head.next;
        head.next = null;

        return newHead;
    }

    private static ListNode combine(ListNode lessHead, ListNode lessTail, ListNode head) {

        if (lessHead == null) {
            return head;
        }

        if (head != null) {
            lessTail.next = head;
        }

        return lessHead;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}
