package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MaximalScoreAfterApplyingKOperations.maxKelements;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximalScoreAfterApplyingKOperationsTest {

    @Test
    void case1() {
        assertEquals(50, maxKelements(new int[] {10, 10, 10, 10, 10}, 5));
    }

    @Test
    void case2() {
        assertEquals(17, maxKelements(new int[] {1, 10, 3, 3, 3}, 3));
    }

    @Test
    void emptyArrayShouldBeOk() {
        assertEquals(0, maxKelements(new int[] {}, 3));
    }

    @Test
    void zeroStepsShouldBeOk() {
        assertEquals(0, maxKelements(new int[] {1, 2, 3, 4, 5}, 0));
    }
}
