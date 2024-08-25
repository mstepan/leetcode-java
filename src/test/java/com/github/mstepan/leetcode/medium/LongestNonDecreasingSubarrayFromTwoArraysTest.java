package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestNonDecreasingSubarrayFromTwoArraysTest {

    @Test
    void maxNonDecreasingLength1() {
        assertEquals(
                4,
                LongestNonDecreasingSubarrayFromTwoArrays.maxNonDecreasingLength(
                        new int[] {1, 3, 2, 1}, new int[] {2, 2, 3, 4}));

        assertEquals(
                2,
                LongestNonDecreasingSubarrayFromTwoArrays.maxNonDecreasingLength(
                        new int[] {1, 1}, new int[] {2, 2}));

        assertEquals(
                2,
                LongestNonDecreasingSubarrayFromTwoArrays.maxNonDecreasingLength(
                        new int[] {4, 2}, new int[] {10, 4}));
    }

    @Test
    void maxNonDecreasingLength2() {
        assertEquals(
                2,
                LongestNonDecreasingSubarrayFromTwoArrays.maxNonDecreasingLength(
                        new int[] {3, 19, 13, 19}, new int[] {20, 18, 7, 14}));
    }
}
