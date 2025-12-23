package com.github.mstepan.leetcode.hard;

import java.util.Objects;

/**
 * 546. Remove Boxes
 *
 * <p>https://leetcode.com/problems/remove-boxes/description/
 *
 * <p>Bottom-up DP: dp[l][r][k] = max score for boxes[l..r] if there are k boxes equal to boxes[r]
 * already attached to the right (merged later).
 *
 * <p>Transitions (without recursive compression): - Remove the (r)th box and its k attachments now:
 * dp[l][r-1][0] + (k+1)^2 - Try merging boxes[r] with some boxes[i] (l <= i < r) of the same color
 * by clearing the middle first: if boxes[i] == boxes[r]: dp[l][i][k+1] + dp[i+1][r-1][0]
 *
 * <p>Base: - For any l and any k: dp[l][l][k] = (k+1)^2
 *
 * <p>We iterate by increasing segment length (r - l + 1), and for each (l, r), compute k in
 * descending order so that states with (k+1) are already available.
 */
class RemoveBoxes {

    public static int removeBoxes(int[] boxes) {
        Objects.requireNonNull(boxes);

        int n = boxes.length;
        if (n == 0) {
            return 0;
        }

        int[][][] dp = new int[n][n][n];

        // Base case: single element segment
        for (int l = 0; l < n; l++) {
            for (int k = 0; k < n; k++) {
                dp[l][l][k] = (k + 1) * (k + 1);
            }
        }

        // Build solutions for longer segments
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;

                // Compute k in descending order so dp[...][k+1] is available
                for (int k = n - 1; k >= 0; k--) {
                    // Option 1: remove the tail (r) with its k attachments now
                    int best = dp[l][r - 1][0] + (k + 1) * (k + 1);

                    // Option 2: connect boxes[r] with boxes[i] by clearing (i+1..r-1)
                    for (int i = l; i < r; i++) {
                        if (boxes[i] == boxes[r]) {
                            if (k + 1 < n) {
                                best = Math.max(best, dp[l][i][k + 1] + dp[i + 1][r - 1][0]);
                            }
                        }
                    }

                    dp[l][r][k] = best;
                }
            }
        }

        return dp[0][n - 1][0];
    }
}
