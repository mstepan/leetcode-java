package com.github.mstepan.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MakeArrayStrictlyIncreasingTest {

    @Test
    void case1() {
        assertEquals(
                1,
                MakeArrayStrictlyIncreasing.makeArrayIncreasing(
                        new int[] {1, 5, 3, 6, 7}, new int[] {1, 3, 2, 4}));
    }

    @Test
    void case2() {
        assertEquals(
                2,
                MakeArrayStrictlyIncreasing.makeArrayIncreasing(
                        new int[] {1, 5, 3, 6, 7}, new int[] {4, 3, 1}));
    }

    @Test
    void case3() {
        assertEquals(
                -1,
                MakeArrayStrictlyIncreasing.makeArrayIncreasing(
                        new int[] {1, 5, 3, 6, 7}, new int[] {1, 6, 3, 3}));
    }
}
