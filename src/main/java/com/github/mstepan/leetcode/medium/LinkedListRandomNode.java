package com.github.mstepan.leetcode.medium;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 382. Linked List Random Node
 *
 * <p>https://leetcode.com/problems/linked-list-random-node/description/
 */
public class LinkedListRandomNode {

    private final ListNode head;

    public LinkedListRandomNode(ListNode head) {
        this.head = Objects.requireNonNull(head);
    }

    /**
     * Use reservoir-sampling (https://en.wikipedia.org/wiki/Reservoir_sampling) approach to select
     * random value from a linked-list (linked-list can be viewed as a stream of unknown length).
     *
     * <p>time: O(N) space: O(1)
     */
    public int getRandom() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        ListNode randNode = head;

        int idx = 2;
        for (ListNode cur = head.next; cur != null; cur = cur.next) {

            if (rand.nextInt(idx) == 0) {
                // probability of event = 1/idx, (1/2, 1/3, ....)
                randNode = cur;
            }

            ++idx;
        }

        return randNode.val;
    }

    // ================== DON'T COPY BELOW CODE ==================

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
    }

    public static void main(String[] args) {

        // 1 -> 2 -> 3 -> 4 -> 5
        ListNode five = new ListNode(5);

        ListNode four = new ListNode(4);
        four.next = five;

        ListNode three = new ListNode(3);
        three.next = four;

        ListNode two = new ListNode(2);
        two.next = three;

        ListNode one = new ListNode(1);
        one.next = two;

        LinkedListRandomNode obj = new LinkedListRandomNode(one);
        int randValue = obj.getRandom();
        System.out.printf("randValue: %d%n", randValue);
    }
}
