package com.github.mstepan.leetcode.hard;

import java.util.Objects;

/**
 * 132. Palindrome Partitioning II
 *
 * <p>https://leetcode.com/problems/palindrome-partitioning-ii/description/
 */
public class PalindromePartitioning2 {

    /**
     * Use bottom-up dynamic programming approach to calculate minimum number of cuts.
     *
     * <p>time: O(N^2), space: O(N^2)
     */
    public static int minCut(String str) {
        Objects.requireNonNull(str);

        if (str.length() < 2) {
            return 0;
        }

        char[] arr = str.toCharArray();

        boolean[][] palindromesTable = palindromesTable(arr);

        int[] cutsSol = new int[arr.length + 1];
        cutsSol[1] = 1;

        for (int to = 1; to < arr.length; to++) {
            int optCur = Integer.MAX_VALUE;

            for (int from = to; from >= 0; --from) {
                if (palindromesTable[from][to]) {
                    optCur = Math.min(optCur, 1 + cutsSol[from]);
                }
            }

            cutsSol[to + 1] = optCur;
        }

        return cutsSol[cutsSol.length - 1] - 1;
    }

    /**
     * Pre-calculate if any substring of a string is a palindrome.
     *
     * <p>time: O(N^2), space: O(N^2)
     */
    static boolean[][] palindromesTable(char[] arr) {
        assert arr != null;

        final int length = arr.length;

        boolean[][] sol = new boolean[length][length];

        for (int row = length - 1; row >= 0; row--) {
            for (int col = 0; col < length; col++) {
                if (row >= col) {
                    sol[row][col] = true;
                } else {
                    boolean curSol = arr[row] == arr[col] && sol[row + 1][col - 1];
                    sol[row][col] = curSol;
                }
            }
        }

        return sol;
    }
}
