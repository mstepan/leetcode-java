package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MinimumTimeToMakeRopeColorful.minCost;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumTimeToMakeRopeColorfulTest {

    @Test
    void minCostNormalCases() {
        assertEquals(3, minCost("abaac", new int[] {1, 2, 3, 4, 5}));
        assertEquals(0, minCost("abc", new int[] {1, 2, 3}));
        assertEquals(2, minCost("aabaa", new int[] {1, 2, 3, 4, 1}));
    }
}
