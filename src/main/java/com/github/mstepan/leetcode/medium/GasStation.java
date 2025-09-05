package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 134. Gas Station
 *
 * <p>https://leetcode.com/problems/gas-station/description/
 */
public class GasStation {

    /**
     * Use modified Kadane's algorithm.
     *
     * <p>time: O(N)
     *
     * <p>space: O(1)
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        Objects.requireNonNull(gas, "gas");
        Objects.requireNonNull(cost, "cost");

        assert gas.length == cost.length;

        int leftGas = 0;
        int pathLength = 0;

        for (int i = 0; i < gas.length * 2 - 1; i++) {

            int idx = i % gas.length;

            leftGas = leftGas + gas[idx] - cost[idx];

            if (leftGas >= 0) {
                ++pathLength;

                if (pathLength == gas.length) {
                    return i - (gas.length - 1);
                }
            } else {
                leftGas = 0;
                pathLength = 0;
            }
        }

        return -1;
    }
}
