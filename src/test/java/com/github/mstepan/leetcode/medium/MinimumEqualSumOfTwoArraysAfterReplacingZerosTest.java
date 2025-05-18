package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MinimumEqualSumOfTwoArraysAfterReplacingZeros.minSum;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumEqualSumOfTwoArraysAfterReplacingZerosTest {

    @Test
    void case1() {
        assertEquals(12, minSum(new int[] {3, 2, 0, 1, 0}, new int[] {6, 5, 0}));
    }

    @Test
    void case2() {
        assertEquals(-1, minSum(new int[] {2, 0, 2, 0}, new int[] {1, 4}));
    }
}
