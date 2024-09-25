package com.github.mstepan.leetcode.medium;

import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 3066. Minimum Operations to Exceed Threshold Value II
 *
 * <p>https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/
 */
public class MinimumOperationsToExceedThresholdValue2 {

    /**
     * time: O(lgK * N * lgN)
     *
     * <p>space: O(N)
     */
    public static int minOperations(int[] arr, int threshold) {

        Objects.requireNonNull(arr);
        if (threshold < 1) {
            throw new IllegalArgumentException(
                    "threshold should be greater or equal to 1, threshold: " + threshold);
        }

        if (arr.length < 2) {
            return -1;
        }

        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        for (int val : arr) {
            assert val >= 1;
            minHeap.add((long) val);
        }

        int opsCnt = 0;

        while (minHeap.size() >= 2 && minHeap.peek() < threshold) {
            long minValue = minHeap.poll();
            long secondMin = minHeap.poll();

            minHeap.add(minValue * 2 + secondMin);
            ++opsCnt;
        }

        if (minHeap.peek() < threshold) {
            return -1;
        }

        return opsCnt;
    }
}
