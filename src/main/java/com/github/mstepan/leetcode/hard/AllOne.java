package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 432. All O`one Data Structure
 *
 * <p>https://leetcode.com/problems/all-oone-data-structure/
 *
 * <p>Runtime: 74 ms, Beats: 95.71%
 */
class AllOne {

    private final Map<String, Bucket> keyToBucket = new HashMap<>();
    private Bucket head;
    private Bucket tail;

    public AllOne() {}

    /** time: O(1) */
    public void inc(String key) {
        Objects.requireNonNull(key);

        Bucket cur = keyToBucket.get(key);

        // element not found at all
        if (cur == null) {
            if (head == null) {
                head = new Bucket(1);
                tail = head;
            } else if (head.frequency != 1) {
                Bucket newBucket = new Bucket(1);
                newBucket.next = head;
                head.prev = newBucket;

                head = newBucket;
            }

            head.add(key);
        }

        // element already found
        else {

            cur.remove(key);

            if (cur.next == null) {
                Bucket newBucket = new Bucket(cur.frequency + 1);

                cur.next = newBucket;
                newBucket.prev = cur;

                tail = newBucket;

                newBucket.add(key);
            } else {
                if (cur.next.frequency == cur.frequency + 1) {
                    cur.next.add(key);
                } else {
                    Bucket newBucket = new Bucket(cur.frequency + 1);
                    newBucket.add(key);

                    Bucket nextBucket = cur.next;

                    newBucket.next = nextBucket;
                    newBucket.prev = cur;
                    cur.next = newBucket;

                    if (nextBucket != null) {
                        nextBucket.prev = newBucket;
                    }
                }
            }

            if (cur.isEmpty()) {
                deleteBucket(cur);
            }
        }
    }

    private void deleteBucket(Bucket cur) {

        // cur is HEAD
        if (cur == head) {
            if (head.next == null) {
                head = null;
                tail = null;
            } else {
                Bucket newHead = head.next;

                head.next = null;
                newHead.prev = null;
                head = newHead;
            }
        }

        // cur is TAIL
        else if (cur == tail) {
            if (tail.prev == null) {
                tail = null;
                head = null;
            } else {
                Bucket newTail = tail.prev;

                newTail.next = null;
                tail.prev = null;

                tail = newTail;
            }

        }
        // cur is MIDDLE node
        else {
            Bucket left = cur.prev;
            Bucket right = cur.next;

            cur.next = null;
            cur.prev = null;

            left.next = right;
            right.prev = left;
        }
    }

    /** time: O(1) */
    public void dec(String key) {
        Objects.requireNonNull(key);

        Bucket cur = keyToBucket.get(key);

        if (cur == null) {
            return;
        }

        cur.remove(key);

        if (cur.frequency == 1) {
            if (cur.isEmpty()) {
                deleteBucket(cur);
            }
            return;
        }

        if (cur == head) {
            Bucket newHead = new Bucket(cur.frequency - 1);

            newHead.next = head;
            head.prev = newHead;

            head = newHead;

            newHead.add(key);
        } else {
            // 'cur' NOT 'head' => cur.prev != null
            assert cur.prev != null;

            if (cur.prev.frequency == cur.frequency - 1) {
                cur.prev.add(key);
            } else {
                Bucket newNode = new Bucket(cur.frequency - 1);
                newNode.add(key);

                newNode.next = cur;
                newNode.prev = cur.prev;

                Bucket prevNode = cur.prev;

                cur.prev = newNode;
                prevNode.next = newNode;
            }
        }

        if (cur.isEmpty()) {
            deleteBucket(cur);
        }
    }

    /** time: O(1) */
    public String getMaxKey() {
        return tail == null ? "" : tail.randomKey();
    }

    /** time: O(1) */
    public String getMinKey() {
        return head == null ? "" : head.randomKey();
    }

    class Bucket {
        final int frequency;

        final Set<String> keys;

        Bucket next;
        Bucket prev;

        Bucket(int frequency) {
            this.frequency = frequency;
            this.keys = new HashSet<>();
        }

        void add(String key) {
            keys.add(key);
            AllOne.this.keyToBucket.put(key, this);
        }

        void remove(String key) {
            keys.remove(key);
            AllOne.this.keyToBucket.remove(key);
        }

        String randomKey() {
            return keys.isEmpty() ? "" : keys.iterator().next();
        }

        boolean isEmpty() {
            return keys.isEmpty();
        }
    }
}
