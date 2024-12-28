package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MinimumCostForTickets.mincostTickets;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumCostForTicketsTest {

    @Test
    void case1() {
        assertEquals(11, mincostTickets(new int[] {1, 4, 6, 7, 8, 20}, new int[] {2, 7, 15}));
    }

    @Test
    void case2() {
        assertEquals(
                17,
                mincostTickets(
                        new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[] {2, 7, 15}));
    }
}
