package com.github.mstepan.leetcode.medium;

import java.util.Arrays;
import java.util.Objects;

public class LinkedListComponents {

    /*
     * time: O(N*lgN)
     *
     * space: O(1)
     */
    public static int numComponents(ListNode head, int[] nums) {
        Objects.requireNonNull(head);
        Objects.requireNonNull(nums);

        int connectedComponentsCnt = 0;

        Arrays.sort(nums);

        ConnectedComponentResult prev = ConnectedComponentResult.NOT_PART;
        ListNode cur = head;

        while (cur != null) {

            if (Arrays.binarySearch(nums, cur.val) >= 0) {
                if (prev == ConnectedComponentResult.NOT_PART) {
                    ++connectedComponentsCnt;
                    prev = ConnectedComponentResult.PART_OF_CONNECTED_COMPONENT;
                }
            } else {
                prev = ConnectedComponentResult.NOT_PART;
            }

            cur = cur.next;
        }

        return connectedComponentsCnt;
    }

    enum ConnectedComponentResult {
        PART_OF_CONNECTED_COMPONENT,
        NOT_PART;
    }

    // == don't copy below code ===

    public static class ListNode {
        final int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
