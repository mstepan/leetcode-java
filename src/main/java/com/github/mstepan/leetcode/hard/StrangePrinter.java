package com.github.mstepan.leetcode.hard;

import java.util.Objects;

/**
 * 664. Strange Printer
 *
 * <p>https://leetcode.com/problems/strange-printer/
 */
public class StrangePrinter {

    /**
     * Use bottom-up dynamic programming technique.
     *
     * <p>N = str.length(), max value = 100
     *
     * <p>K = 'z' - 'a' + 1 = 26
     *
     * <p>time: O(N^2) = 100 * 100 = 100_000
     *
     * <p>space: O(N^2), can be reduced to O(N)
     */
    public static int strangePrinter(String str) {
        Objects.requireNonNull(str, "str is null");

        if (str.length() < 2) {
            return str.length();
        }

        char[] arr = str.toCharArray();

        int[][] opt = new int[arr.length][arr.length];

        for (int row = arr.length - 1; row >= 0; row--) {
            for (int col = row; col < arr.length; col++) {
                if (row == col) {
                    opt[row][col] = 1;
                } else {
                    if (arr[row] == arr[col]) {
                        opt[row][col] = opt[row][col - 1];
                    } else {
                        int bestSol = Integer.MAX_VALUE;
                        for (int k = row; k < col; ++k) {
                            // check all possible solutions and save minimal for ranges [i...k] +
                            // [k+1...col]
                            bestSol = Math.min(bestSol, opt[row][k] + opt[k + 1][col]);
                        }

                        opt[row][col] = bestSol;
                    }
                }
            }
        }

        return opt[0][opt.length - 1];
    }
}
