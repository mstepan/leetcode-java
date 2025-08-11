package com.github.mstepan.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 128. Longest Consecutive Sequence
 *
 * <p>https://leetcode.com/problems/longest-consecutive-sequence/description
 */
public class LongestConsecutiveSequence {

    /**
     * Uses union-find or disjoint set DS to track consecutive ranges.
     *
     * <p>time: O(N)
     *
     * <p>space: O(N)
     */
    public static int longestConsecutive(int[] nums) {

        Range largest = Range.EMPTY;

        Map<Integer, Range> ranges = new HashMap<>();

        for (int value : nums) {
            if (ranges.containsKey(value)) {
                // already merged into range, just skip value
                continue;
            }

            final Range left = ranges.get(value - 1);
            final Range right = ranges.get(value + 1);

            if (left == null && right == null) {
                // BOTH nulls
                Range newRange = new Range(value, value);
                ranges.put(value, new Range(value, value));

                if (newRange.size() > largest.size()) {
                    largest = newRange;
                }

            } else if (left != null && right != null) {
                // LEFT and RIGHT
                // merge left with right

                Range mergedRange;

                if (left.size() >= right.size()) {
                    mergedRange = left;
                    mergedRange.to = right.to;

                    for (int rightValue = right.from; rightValue <= right.to; ++rightValue) {
                        ranges.put(rightValue, mergedRange);
                    }

                } else {
                    mergedRange = right;
                    mergedRange.from = left.from;

                    for (int leftValue = left.from; leftValue <= left.to; ++leftValue) {
                        ranges.put(leftValue, mergedRange);
                    }
                }

                largest = max(largest, mergedRange);

                ranges.put(value, mergedRange);
            } else if (left != null) {
                // LEFT only
                left.to = value;
                ranges.put(value, left);

                largest = max(largest, left);

            } else {
                // RIGHT only
                right.from = value;
                ranges.put(value, right);
                largest = max(largest, right);
            }
        }

        return largest.size();
    }

    private static Range max(Range left, Range right) {
        return left.size() >= right.size() ? left : right;
    }

    private static final class Range {

        private static final Range EMPTY = new Range(0, -1);

        int from;
        int to;

        Range(int from, int to) {
            this.from = from;
            this.to = to;
        }

        int size() {
            return to - from + 1;
        }
    }
}
