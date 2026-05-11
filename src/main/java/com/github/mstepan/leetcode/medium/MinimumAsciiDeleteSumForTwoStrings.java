package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 712. Minimum ASCII Delete Sum for Two Strings
 *
 * <p>https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/
 */
public class MinimumAsciiDeleteSumForTwoStrings {

    /**
     * Solved using top-down dynamic programming approach.
     * time: O(N*K), space: O(N*K) ~ O(min(N, K))
     *
     * N = s1.length()
     * K = s2.length()
     *
     */
    public static int minimumDeleteSum(String s1, String s2) {
        Objects.requireNonNull(s1);
        Objects.requireNonNull(s2);
        final int rows = s1.length() + 1;
        final int cols = s2.length() + 1;
        int[][] opt = new int[rows][cols];

        for (int row = 1; row < rows; ++row) {
            opt[row][0] = opt[row - 1][0] + s1.charAt(row - 1);
        }

        for (int col = 1; col < cols; ++col) {
            opt[0][col] = opt[0][col - 1] + s2.charAt(col - 1);
        }

        for (int row = 1; row < rows; ++row) {
            for (int col = 1; col < cols; ++col) {

                char c1 = s1.charAt(row - 1);
                char c2 = s2.charAt(col - 1);

                if (c1 == c2) {
                    opt[row][col] = opt[row - 1][col - 1];
                } else {
                    opt[row][col] =
                            Math.min(
                                    opt[row - 1][col] + s1.charAt(row - 1),
                                    opt[row][col - 1] + s2.charAt(col - 1));
                }
            }
        }

        return opt[rows - 1][cols - 1];
    }
}
