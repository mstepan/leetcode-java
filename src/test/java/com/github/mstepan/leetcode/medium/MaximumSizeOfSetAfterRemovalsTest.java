package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumSizeOfSetAfterRemovalsTest {

    @Test
    void maximumSetSizeCase1() {
        assertEquals(
                2,
                MaximumSizeOfSetAfterRemovals.maximumSetSize(
                        new int[] {1, 2, 1, 2}, new int[] {1, 1, 1, 1}));
    }

    @Test
    void maximumSetSizeCase2() {
        assertEquals(
                5,
                MaximumSizeOfSetAfterRemovals.maximumSetSize(
                        new int[] {1, 2, 3, 4, 5, 6}, new int[] {2, 3, 2, 3, 2, 3}));
    }

    @Test
    void maximumSetSizeCase3() {
        assertEquals(
                6,
                MaximumSizeOfSetAfterRemovals.maximumSetSize(
                        new int[] {1, 1, 2, 2, 3, 3}, new int[] {4, 4, 5, 5, 6, 6}));
    }
}
