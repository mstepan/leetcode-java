package com.github.mstepan.leetcode.hard;

import java.util.Arrays;
import java.util.Objects;

/**
 * 135. Candy
 *
 * <p>https://leetcode.com/problems/candy/
 */
public class Candy {

    /**
     * Uses linear scan through 'ratings' array to assign appropriate candies. If DESC order
     * sequence found adjust candies values right-to-left from local minimum.
     *
     * <p>time: O(N)
     *
     * <p>space: O(N)
     */
    public static int candy(int[] ratings) {
        Objects.requireNonNull(ratings, "null 'ratings' array detected");
        if (ratings.length < 2) {
            return ratings.length;
        }

        int[] candies = new int[ratings.length];
        candies[0] = 1;

        for (int i = 1; i < ratings.length; ) {
            int prev = ratings[i - 1];
            int cur = ratings[i];

            if (cur > prev) {
                candies[i] = candies[i - 1] + 1;
                ++i;
            } else if (cur == prev) {
                candies[i] = 1;
                ++i;
            } else {
                int minIdx = findLocalMin(ratings, i);
                fixToRight(ratings, candies, minIdx, i - 1);
                i = minIdx + 1;
            }
        }

        return Arrays.stream(candies).sum();
    }

    private static int findLocalMin(int[] ratings, int from) {
        assert ratings != null;
        assert from > 0 && from < ratings.length;

        for (int i = from; i < ratings.length; ++i) {
            int prev = ratings[i - 1];
            int cur = ratings[i];
            int next = (i + 1 < ratings.length) ? ratings[i + 1] : Integer.MAX_VALUE;

            if (cur <= prev && cur < next) {
                return i;
            }
        }

        throw new IllegalStateException(
                "Can't find local minimum for array: %s and from = %d"
                        .formatted(Arrays.toString(ratings), from));
    }

    private static void fixToRight(int[] ratings, int[] candies, int from, int to) {
        candies[from] = 1;

        for (int i = from - 1; i >= to; --i) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], 1 + candies[i + 1]);
            } else if (ratings[i] == ratings[i + 1]) {
                candies[i] = Math.max(1, candies[i]);
            } else {
                throw new IllegalStateException(
                        "Incorrect state detected for 'fixToRight' for range [%d...%d]"
                                .formatted(to, from));
            }
        }
    }
}
