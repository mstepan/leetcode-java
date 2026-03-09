package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 2226. Maximum Candies Allocated to K Children
 *
 * <p>https://leetcode.com/problems/maximum-candies-allocated-to-k-children/description/
 */
public class MaximumCandiesAllocatedToKChildren {

    /**
     * Binary search the answer.
     *
     * <p>time: O(N*lgK), N = 10^5, K = 10^7 => N*lgK ~ 2.5M
     *
     * <p>space: O(1)
     */
    public static int maximumCandies(int[] candies, long k) {
        Objects.requireNonNull(candies);
        if (k < 0) {
            throw new IllegalArgumentException("k should be greater than 0");
        }

        if (k == 0L) {
            return 0;
        }

        if (candies.length == 0) {
            return 0;
        }

        int max = findMax(candies);

        int maxCandies = 0;

        int lo = 1;
        int hi = max;

        assert hi >= 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (hasSolution(candies, k, mid)) {
                maxCandies = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return maxCandies;
    }

    private static boolean hasSolution(int[] candies, long k, int expectedCandies) {

        long leftCount = k;

        for (int i = 0; i < candies.length && leftCount > 0L; i++) {
            leftCount -= (candies[i] / expectedCandies);
        }

        return leftCount <= 0L;
    }

    private static int findMax(int[] candies) {
        assert candies != null;
        assert candies.length > 0;

        int max = candies[0];

        for (int val : candies) {
            max = Math.max(max, val);
        }

        return max;
    }

    record MinMax(int min, int max) {}
}
