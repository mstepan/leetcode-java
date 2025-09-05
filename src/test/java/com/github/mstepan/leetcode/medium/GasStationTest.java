package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.GasStation.canCompleteCircuit;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GasStationTest {

    @Test
    void case1() {
        assertEquals(3, canCompleteCircuit(new int[] {1, 2, 3, 4, 5}, new int[] {3, 4, 5, 1, 2}));
    }

    @Test
    void case2() {
        assertEquals(-1, canCompleteCircuit(new int[] {2, 3, 4}, new int[] {3, 4, 3}));
    }

    @Test
    void moreComplexCase() {
        assertEquals(
                2, canCompleteCircuit(new int[] {5, 4, 3, 5, 5, 3}, new int[] {2, 10, 1, 6, 1, 4}));
    }
}
