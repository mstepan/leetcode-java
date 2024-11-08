package com.github.mstepan.leetcode.medium;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 2530. Maximal Score After Applying K Operations
 *
 * <p>https://leetcode.com/problems/maximal-score-after-applying-k-operations/description/
 */
public class MaximalScoreAfterApplyingKOperations {

    /**
     * time: O(K*lgN), actually O(N*lgN) b/c we need to add all elements to max-heap.
     *
     * <p>space: O(N)
     */
    public static long maxKelements(int[] arr, int stepsCnt) {
        checkArgument(arr != null, "null 'arr' detected");
        checkArgument(stepsCnt >= 0, "Negative 'stepsCnt' detected");

        if (arr.length == 0 || stepsCnt == 0) {
            return 0L;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // below loop will have time: O(N*lgN)
        for (int val : arr) {
            maxHeap.add(val);
        }

        long res = 0L;

        for (int it = 0; it < stepsCnt; ++it) {
            assert !maxHeap.isEmpty();
            int maxVal = maxHeap.poll();
            res += maxVal;
            maxHeap.add(maxVal / 3 + (maxVal % 3 == 0 ? 0 : 1));
        }

        return res;
    }

    private static void checkArgument(boolean predicate, String errorMessage) {
        if (!predicate) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
