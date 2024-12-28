package com.github.mstepan.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 983. Minimum Cost For Tickets
 *
 * <p>https://leetcode.com/problems/minimum-cost-for-tickets/description/
 */
public class MinimumCostForTickets {

    /**
     * Uses top-down dynamic programming approach.
     *
     * <p>time: O(365 * 3) ~ O(1)
     *
     * <p>space: O(365) ~ O(1)
     */
    public static int mincostTickets(int[] days, int[] costs) {
        checkValidDaysOfYear(days);
        checkValidCosts(costs);

        if (days.length == 0) {
            return 0;
        }

        final Set<Integer> daysSet = toSet(days);
        final int lastDay = days[days.length - 1];

        int[] opt = new int[lastDay + 1];

        for (int day = 1; day < opt.length; day++) {
            if (daysSet.contains(day)) {
                int curBest =
                        minOfThree(
                                costs[0] + opt[Math.max(day - 1, 0)],
                                costs[1] + opt[Math.max(day - 7, 0)],
                                costs[2] + opt[Math.max(day - 30, 0)]);

                opt[day] = curBest;
            } else {
                opt[day] = opt[day - 1];
            }
        }

        return opt[lastDay];
    }

    private static void checkValidDaysOfYear(int[] days) {
        if (days == null) {
            throw new IllegalArgumentException("'days' is null");
        }

        for (int singleDay : days) {
            if (singleDay <= 0 || singleDay > 365) {
                throw new IllegalArgumentException(
                        "incorrect day of year: " + singleDay + ", should be in range [1...365]");
            }
        }
    }

    private static void checkValidCosts(int[] costs) {
        if (costs == null) {
            throw new IllegalArgumentException("'costs' cannot be null");
        }

        if (costs.length != 3) {
            throw new IllegalArgumentException("'costs' must have 3 elements");
        }

        for (int singleCost : costs) {
            if (singleCost <= 0) {
                throw new IllegalArgumentException("Negative cost detected");
            }
        }
    }

    private static Set<Integer> toSet(int[] days) {
        assert days != null;

        Set<Integer> daysSet = new HashSet<>();
        for (int singleDay : days) {
            daysSet.add(singleDay);
        }

        return daysSet;
    }

    private static int minOfThree(int first, int second, int third) {
        return Math.min(Math.min(first, second), third);
    }
}
