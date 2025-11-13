package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.PartitionList.partition;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PartitionListTest {

    @Test
    public void case1() {
        PartitionList.ListNode list = fromArr(new int[] {1, 4, 3, 2, 5, 2});

        PartitionList.ListNode partitionedList = partition(list, 3);

        assertListEquals(partitionedList, new int[] {1, 2, 2, 4, 3, 5});
    }

    @Test
    public void case2() {
        PartitionList.ListNode list = fromArr(new int[] {2, 1});

        PartitionList.ListNode partitionedList = partition(list, 2);

        assertListEquals(partitionedList, new int[] {1, 2});
    }

    private PartitionList.ListNode fromArr(int[] arr) {

        assertTrue(arr.length > 0);
        PartitionList.ListNode head = new PartitionList.ListNode(arr[0]);
        PartitionList.ListNode last = head;

        for (int i = 1; i < arr.length; i++) {
            last.next = new PartitionList.ListNode(arr[i]);
            last = last.next;
        }

        return head;
    }

    private void assertListEquals(PartitionList.ListNode list, int[] expectedArr) {

        PartitionList.ListNode cur = list;
        for (int expectedValue : expectedArr) {
            assertNotNull(cur);
            assertEquals(expectedValue, cur.val);
            cur = cur.next;
        }

        assertNull(cur);
    }
}
