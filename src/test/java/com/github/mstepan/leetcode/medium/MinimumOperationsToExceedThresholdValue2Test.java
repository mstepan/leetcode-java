package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MinimumOperationsToExceedThresholdValue2.minOperations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumOperationsToExceedThresholdValue2Test {

    @Test
    void minOperationsCase1() {
        assertEquals(2, minOperations(new int[] {2, 11, 10, 1, 3}, 10));
    }

    @Test
    void minOperationsCase2() {
        assertEquals(4, minOperations(new int[] {1, 1, 2, 4, 9}, 20));
    }
}
