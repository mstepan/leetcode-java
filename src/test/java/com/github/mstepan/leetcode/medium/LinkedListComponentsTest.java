package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LinkedListComponentsTest {

    @Test
    void case1() {
        assertEquals(
                2, LinkedListComponents.numComponents(linkedList(0, 1, 2, 3), new int[] {0, 1, 3}));
    }

    @Test
    void case2() {
        assertEquals(
                2,
                LinkedListComponents.numComponents(
                        linkedList(0, 1, 2, 3, 4), new int[] {0, 3, 1, 4}));
    }

    @Test
    void caseWithOneElementMatchingConnectedComponent() {
        assertEquals(1, LinkedListComponents.numComponents(linkedList(10), new int[] {10}));
    }

    @Test
    void caseWithOneElementNotMatchingConnectedComponent() {
        assertEquals(0, LinkedListComponents.numComponents(linkedList(10), new int[] {20}));
    }

    @Test
    void caseWithSingleConnectedComponent() {
        assertEquals(
                1,
                LinkedListComponents.numComponents(
                        linkedList(1, 2, 0, 4, 3), new int[] {3, 4, 0, 2, 1}));
    }

    private static LinkedListComponents.ListNode linkedList(int firstValue, int... values) {

        LinkedListComponents.ListNode head = new LinkedListComponents.ListNode(firstValue);

        LinkedListComponents.ListNode cur = head;

        for (int i = 1; i < values.length; ++i) {
            cur.next = new LinkedListComponents.ListNode(values[i]);
            cur = cur.next;
        }

        return head;
    }
}
