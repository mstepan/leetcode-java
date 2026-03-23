package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 845. Longest Mountain in Array
 *
 * <p>https://leetcode.com/problems/longest-mountain-in-array/description
 */
public class LongestMountainInArray {

    /**
     * Time: O (N)
     *
     * <p>Space: O(1)
     *
     * <p>Just use invariants and carefully process every array element once.
     */
    public static int longestMountain(int[] arr) {
        Objects.requireNonNull(arr);

        int incLength = 0;
        int decLength = 0;
        int maxSoFar = 0;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] == arr[i - 1]) {
                incLength = 0;
                decLength = 0;
            } else if (arr[i - 1] > arr[i]) {
                ++decLength;

                if (incLength != 0) {
                    maxSoFar = Math.max(maxSoFar, incLength + decLength + 1);
                }
            } else {
                if (decLength != 0) {
                    incLength = 0;
                    decLength = 0;
                }

                ++incLength;
            }
        }

        return maxSoFar;
    }
}
