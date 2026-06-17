package com.github.mstepan.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 447. Number of Boomerangs
 *
 * <p>https://leetcode.com/problems/number-of-boomerangs/description/
 */
public class NumberOBoomerangs {

    /**
     * time: O(N^2)
     *
     * <p>space: O(1)
     */
    public static int numberOfBoomerangs(int[][] points) {
        Objects.requireNonNull(points);

        if (points.length < 3) {
            return 0;
        }

        int boomerangsCount = 0;

        for (int i = 0; i < points.length; ++i) {

            final int[] cur = points[i];

            final Map<Integer, Integer> distancesForCur = new HashMap<>();

            for (int j = 0; j < points.length; ++j) {
                if (i != j) {
                    int distance = calculateDistance(cur, points[j]);
                    distancesForCur.compute(
                            distance, (keyNotUsed, count) -> count == null ? 1 : count + 1);
                }
            }

            for (Integer distanceCount : distancesForCur.values()) {
                if (distanceCount > 1) {
                    boomerangsCount = boomerangsCount + (distanceCount * (distanceCount - 1));
                }
            }
        }

        return boomerangsCount;
    }

    private static int calculateDistance(int[] cur, int[] other) {
        assert cur != null;
        assert other != null;

        int dx = cur[0] - other[0];
        int dy = cur[1] - other[1];

        return dx * dx + dy * dy;
    }
}
