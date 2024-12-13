package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.SplitLinkedListInParts.ListNode;
import static com.github.mstepan.leetcode.medium.SplitLinkedListInParts.splitListToParts;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SplitLinkedListInPartsTest {

    @Test
    void case1() {
        ListNode head = createList(1, 2, 3);

        int k = 5;

        ListNode[] chunks = splitListToParts(head, k);

        assertNotNull(chunks);
        assertEquals(k, chunks.length);

        // [[1],[2],[3],[],[]]
        assertChunk(List.of(1), chunks[0]);
        assertChunk(List.of(2), chunks[1]);
        assertChunk(List.of(3), chunks[2]);
        assertChunk(null, chunks[3]);
        assertChunk(null, chunks[4]);
    }

    @Test
    void case2() {
        ListNode head = createList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int k = 3;

        ListNode[] chunks = splitListToParts(head, k);

        assertNotNull(chunks);
        assertEquals(k, chunks.length);

        // [[1,2,3,4],[5,6,7],[8,9,10]]
        assertChunk(List.of(1, 2, 3, 4), chunks[0]);
        assertChunk(List.of(5, 6, 7), chunks[1]);
        assertChunk(List.of(8, 9, 10), chunks[2]);
    }

    private ListNode createList(int firstValue, int... values) {

        ListNode head = new ListNode(firstValue);
        ListNode tail = head;

        for (int singleValue : values) {
            tail.next = new ListNode(singleValue);
            tail = tail.next;
        }

        return head;
    }

    private void assertChunk(List<Integer> expectedValues, ListNode chunk) {
        if (expectedValues == null) {
            assertNull(chunk);
        } else {

            ListNode cur = chunk;

            for (Integer expectedSingleValue : expectedValues) {
                assertNotNull(cur);
                assertEquals(expectedSingleValue, cur.val);
                cur = cur.next;
            }
        }
    }
}
