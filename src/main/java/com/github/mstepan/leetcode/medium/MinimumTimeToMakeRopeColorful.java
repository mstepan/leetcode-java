package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 1578. Minimum Time to Make Rope Colorful
 *
 * <p>https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
 */
public class MinimumTimeToMakeRopeColorful {

    /**
     * Time: O(N)
     *
     * <p>Space: O(1)
     *
     * <p>Greedy like approach.
     */
    public static int minCost(String colors, int[] costs) {
        Objects.requireNonNull(colors, "'colors' String must not be null");
        Objects.requireNonNull(costs, "'costs' array must not be null");
        assert colors.length() == costs.length;

        if (colors.length() < 2) {
            return 0;
        }

        int subCost = 0;

        char lastCh = colors.charAt(0);
        int lastCost = costs[0];

        for (int i = 1; i < colors.length(); i++) {
            char cur = colors.charAt(i);

            if (lastCh == cur) {
                subCost += Math.min(lastCost, costs[i]);
                lastCost = Math.max(lastCost, costs[i]);
            } else {
                lastCost = costs[i];
            }

            lastCh = cur;
        }

        return subCost;
    }
}
