package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 2211. Count Collisions on a Road
 *
 * <p>https://leetcode.com/problems/count-collisions-on-a-road/description/
 */
public class CountCollisionsOnRoad {

    /**
     * Do ordinary string traversal and just carefully analyse all possible invariants.
     *
     * <p>time: O(N)
     *
     * <p>space: O(1)
     */
    public static int countCollisions(String directions) {
        Objects.requireNonNull(directions);

        if (directions.length() < 2) {
            return 0;
        }

        int collisionsCount = 0;

        int movingRightCount = (directions.charAt(0) == 'R') ? 1 : 0;
        char prev = directions.charAt(0);

        for (int i = 1; i < directions.length(); ++i) {
            char cur = directions.charAt(i);

            if (cur == 'R') {
                ++movingRightCount;
                prev = cur;
            } else if (cur == 'S') {
                collisionsCount += movingRightCount;
                movingRightCount = 0;
                prev = cur;
            } else if (cur == 'L') {
                if (movingRightCount > 0) {
                    collisionsCount += (movingRightCount + 1);
                    movingRightCount = 0;
                    prev = 'S';
                } else if (prev == 'S') {
                    ++collisionsCount;
                } else {
                    prev = cur;
                }
            } else {
                throw new IllegalStateException("Incorrect 'directions' string");
            }
        }

        return collisionsCount;
    }
}
