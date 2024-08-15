package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumStrengthOfGroupTest {

    @Test
    void maxStrengthOnlyPositiveValues() {
        assertEquals(
                9L * 3 * 4 * 6 * 2,
                new MaximumStrengthOfGroup().maxStrength(new int[] {9, 3, 4, 6, 1, 2}));

        assertEquals(9L, new MaximumStrengthOfGroup().maxStrength(new int[] {9, 1, 1}));

        assertEquals(9L * 2, new MaximumStrengthOfGroup().maxStrength(new int[] {9, 2}));
    }

    @Test
    void maxStrengthOnlyNegativeValues() {
        assertEquals(
                -9L * -3 * -4 * -6,
                new MaximumStrengthOfGroup().maxStrength(new int[] {-9, -3, -4, -6, -2}));
    }

    @Test
    void maxStrengthMixedValues() {
        assertEquals(
                9L * 6 * -4 * -3,
                new MaximumStrengthOfGroup().maxStrength(new int[] {9, -3, -4, 6, -2}));
    }

    @Test
    void maxStrengthWithZeros() {
        assertEquals(0L, new MaximumStrengthOfGroup().maxStrength(new int[] {0, -1}));

        assertEquals(
                265420800L,
                new MaximumStrengthOfGroup()
                        .maxStrength(new int[] {8, 6, 0, 5, -4, -8, -4, 9, -1, 6, -4, 8, -5}));

        assertEquals(
                2L * 7 * 9 * 4,
                new MaximumStrengthOfGroup().maxStrength(new int[] {2, 7, 0, -4, 9, 4}));
    }

    @Test
    void maxStrengthWithAllZeros() {
        assertEquals(0L, new MaximumStrengthOfGroup().maxStrength(new int[] {0, 0, 0, 0, 0}));
    }

    @Test
    void maxStrengthEmptyArray() {
        assertEquals(0L, new MaximumStrengthOfGroup().maxStrength(new int[] {}));
    }

    @Test
    void maxStrengthSingleElementArray() {
        assertEquals(7L, new MaximumStrengthOfGroup().maxStrength(new int[] {7}));

        assertEquals(-5L, new MaximumStrengthOfGroup().maxStrength(new int[] {-5}));
    }
}
