package com.github.mstepan.leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 514. Freedom Trail
 *
 * <p>https://leetcode.com/problems/freedom-trail/description
 */
public class FreedomTrail {

    /**
     * Use bottom-up dynamic programming to find the minimum number of steps to spell out the key
     *
     * <p>N = right.length(), K = key.length()
     *
     * <p>time :O(N*K)
     *
     * <p>space: O(N*K)
     */
    public static int findRotateSteps(String ring, String key) {
        Objects.requireNonNull(ring, "null 'ring' argument");
        Objects.requireNonNull(key, "null 'key' argument");

        final int rows = key.length() + 1;
        final int cols = ring.length();
        int[][] sol = new int[rows][cols];

        for (int row = rows - 2; row >= 0; row--) {

            char keyCh = key.charAt(row);

            for (int col = 0; col < cols; col++) {
                int curBest = Integer.MAX_VALUE;

                List<Integer> foundLocations = findLocations(keyCh, ring);
                assert foundLocations.size() > 0;

                for (int locationIdx : foundLocations) {
                    curBest =
                            Math.min(
                                    curBest,
                                    sol[row + 1][locationIdx]
                                            + distance(col, locationIdx, ring.length()));
                }

                sol[row][col] = curBest;
            }
        }

        return sol[0][0];
    }

    private static List<Integer> findLocations(char searchCh, String ring) {
        List<Integer> locations = new ArrayList<>();

        for (int i = 0; i < ring.length(); i++) {
            if (ring.charAt(i) == searchCh) {
                locations.add(i);
            }
        }

        return locations;
    }

    private static int distance(int from1, int to1, int ringLength) {
        final int from = Math.min(from1, to1);
        final int to = Math.max(from1, to1);

        return Math.min(to - from, from + ringLength - to) + 1;
    }
}
