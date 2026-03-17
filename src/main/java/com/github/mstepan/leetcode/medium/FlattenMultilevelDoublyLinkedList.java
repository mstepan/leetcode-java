package com.github.mstepan.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 430. Flatten a Multilevel Doubly Linked List
 *
 * <p>https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 */
public class FlattenMultilevelDoublyLinkedList {

    /**
     * time: O(N)
     *
     * <p>space: O(1)
     */
    public static Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        final Node sentinelHead = new Node();
        Node tail = sentinelHead;

        Node cur = head;
        Deque<Node> stack = new ArrayDeque<>();

        while (cur != null) {
            tail = insertAfter(cur, tail);

            if (cur.child != null) {
                if (cur.next != null) {
                    stack.push(cur.next);
                }

                cur = cur.child;
            } else {
                cur = cur.next;

                if (cur == null && !stack.isEmpty()) {
                    cur = stack.pop();
                }
            }
        }

        // null all child references
        for (Node node = sentinelHead.next; node != null; node = node.next) {
            node.child = null;
        }

        final Node flatListResult = sentinelHead.next;
        if (flatListResult != null) {
            flatListResult.prev = null;
        }
        return flatListResult;
    }

    private static Node insertAfter(Node cur, Node tail) {
        assert cur != null;
        assert tail != null;

        tail.next = cur;
        cur.prev = tail;

        return cur;
    }

    // Definition for a Node.
    public static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public void addAfter(Node other) {
            this.next = other;
            other.prev = this;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}
