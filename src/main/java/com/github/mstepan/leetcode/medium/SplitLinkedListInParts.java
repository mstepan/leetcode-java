package com.github.mstepan.leetcode.medium;

/**
 * 725. Split Linked List in Parts
 *
 * <p>https://leetcode.com/problems/split-linked-list-in-parts/
 */
public class SplitLinkedListInParts {

    /**
     * time: O(N)
     *
     * <p>space: O(N)
     */
    public static ListNode[] splitListToParts(ListNode head, int k) {
        checkArgument(head != null, "null 'head'");
        checkArgument(k > 0, "incorrect 'k' value, expected > 0 but found " + k);

        ListNode[] chunks = new ListNode[k];

        int nodesCnt = calculateLength(head);
        ListNode cur = head;

        for (int i = 0; i < chunks.length; ++i) {
            int leftChunksCnt = k - i;

            int chunkSize = nodesCnt / leftChunksCnt + (nodesCnt % leftChunksCnt == 0 ? 0 : 1);

            ExtractedPart part = extractPart(cur, chunkSize);
            chunks[i] = part.chunkHead;
            cur = part.newHead;

            nodesCnt -= chunkSize;
        }

        return chunks;
    }

    private static void checkArgument(boolean predicate, String errorMessage) {
        if (!predicate) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static ExtractedPart extractPart(ListNode head, int size) {

        if (size == 0) {
            return new ExtractedPart(null, null);
        }

        ListNode cur = head;

        for (int i = 0; i < size - 1; ++i) {
            cur = cur.next;
        }

        ListNode nextHead = cur.next;
        cur.next = null;

        return new ExtractedPart(head, nextHead);
    }

    private static int calculateLength(ListNode head) {
        int size = 0;

        for (ListNode cur = head; cur != null; cur = cur.next) {
            ++size;
        }

        return size;
    }

    private static class ExtractedPart {
        ListNode chunkHead;
        ListNode newHead;

        ExtractedPart(ListNode chunkHead, ListNode newHead) {
            this.chunkHead = chunkHead;
            this.newHead = newHead;
        }
    }

    // =============================================================
    // Do not copy below lines
    // =============================================================

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

            StringBuilder buf = new StringBuilder();
            buf.append("[");

            for (ListNode cur = this; cur != null; cur = cur.next) {
                if (buf.length() != 1) {
                    buf.append(", ");
                }
                buf.append(cur.val);
            }
            buf.append("]");

            return buf.toString();
        }
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));

        int k = 5;

        ListNode[] chunks = splitListToParts(head, k);

        for (ListNode singleChunk : chunks) {
            System.out.println(singleChunk == null ? "[]" : String.valueOf(singleChunk));
        }
    }
}
