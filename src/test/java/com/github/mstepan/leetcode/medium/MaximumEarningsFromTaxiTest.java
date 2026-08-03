package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MaximumEarningsFromTaxi.maxTaxiEarnings;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumEarningsFromTaxiTest {

    @Test
    void case1() {
        assertEquals(
                7L,
                maxTaxiEarnings(
                        5,
                        new int[][] {
                            {2, 5, 4},
                            {1, 5, 1}
                        }));
    }

    @Test
    void case2() {
        assertEquals(
                20L,
                maxTaxiEarnings(
                        20,
                        new int[][] {
                            {1, 6, 1},
                            {3, 10, 2},
                            {10, 12, 3},
                            {11, 12, 2},
                            {12, 15, 2},
                            {13, 18, 1}
                        }));
    }

    @Test
    void singleRide() {
        assertEquals(7L, maxTaxiEarnings(5, new int[][] {{1, 5, 3}}));
    }

    @Test
    void noRidesAtAll() {
        assertEquals(0L, maxTaxiEarnings(5, new int[][] {}));
    }
}
