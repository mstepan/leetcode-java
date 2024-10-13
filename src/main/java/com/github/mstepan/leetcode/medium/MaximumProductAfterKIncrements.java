package com.github.mstepan.leetcode.medium;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 2233. Maximum Product After K Increments
 *
 * <p>https://leetcode.com/problems/maximum-product-after-k-increments/description/
 */
public class MaximumProductAfterKIncrements {

    private static final int MOD = 1_000_000_007;

    /**
     * N = nums.length, K = k
     *
     * <p>time: O(K*lgN)
     *
     * <p>space: O(N)
     */
    public static int maximumProduct(int[] nums, int k) {
        Objects.requireNonNull(nums, "null 'nums' detected");
        if (k < 0) {
            throw new IllegalArgumentException("'k' should be positive or zero: k = " + k);
        }

        if (nums.length == 0) {
            return 0;
        }

        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int val : nums) {
            if (val < 0) {
                throw new IllegalArgumentException("Negative value in 'nums' detected");
            }
            minHeap.add(val);
        }

        for (int i = 0; i < k; ++i) {
            assert !minHeap.isEmpty();

            int minValue = minHeap.poll();

            if (minValue == Integer.MAX_VALUE) {
                throw new IllegalStateException("Integer overflow occurred");
            }
            minHeap.add(minValue + 1);
        }

        long res = 1L;

        for (int val : minHeap) {
            res = (res * val) % MOD;
        }

        return (int) res;
    }
}
