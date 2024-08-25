package com.github.mstepan.leetcode.medium;

import java.util.Arrays;
import java.util.Objects;

/**
 * 2771. Longest Non-decreasing Subarray From Two Arrays
 *
 * <p>https://leetcode.com/problems/longest-non-decreasing-subarray-from-two-arrays/description/
 */
public class LongestNonDecreasingSubarrayFromTwoArrays {

    /**
     * Uses Kadane's like algorithm but for 2 arrays at the sam time.
     *
     * <p>time: O(N)
     *
     * <p>space: O(N)
     */
    public static int maxNonDecreasingLength(int[] arr1, int[] arr2) {
        Objects.requireNonNull(arr1);
        Objects.requireNonNull(arr2);
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("arr1.length != arr2.length");
        }

        int maxSoFar = 1;

        int[] res1 = new int[arr1.length];
        Arrays.fill(res1, 1);

        int[] res2 = new int[arr2.length];
        Arrays.fill(res2, 1);

        for (int i = 1; i < arr1.length; ++i) {

            // handle arr1
            if (arr1[i] >= arr1[i - 1]) {
                res1[i] = 1 + res1[i - 1];
            }
            if (arr1[i] >= arr2[i - 1]) {
                res1[i] = Math.max(res1[i], 1 + res2[i - 1]);
            }

            // handle arr2
            if (arr2[i] >= arr2[i - 1]) {
                res2[i] = 1 + res2[i - 1];
            }
            if (arr2[i] >= arr1[i - 1]) {
                res2[i] = Math.max(res2[i], 1 + res1[i - 1]);
            }

            maxSoFar = Math.max(maxSoFar, res1[i]);
            maxSoFar = Math.max(maxSoFar, res2[i]);
        }

        return maxSoFar;
    }
}
