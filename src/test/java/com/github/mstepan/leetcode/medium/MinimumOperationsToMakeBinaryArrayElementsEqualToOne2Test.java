package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumOperationsToMakeBinaryArrayElementsEqualToOne2Test {

    @Test
    void minOpsSimpleCase1() {
        assertEquals(
                4,
                new MinimumOperationsToMakeBinaryArrayElementsEqualToOne2()
                        .minOperations(new int[] {0, 1, 1, 0, 1}));
    }

    @Test
    void minOpsSimpleCase2() {
        assertEquals(
                1,
                new MinimumOperationsToMakeBinaryArrayElementsEqualToOne2()
                        .minOperations(new int[] {1, 0, 0, 0}));
    }

    @Test
    void minOpsAllOnes() {
        assertEquals(
                0,
                new MinimumOperationsToMakeBinaryArrayElementsEqualToOne2()
                        .minOperations(new int[] {1, 1, 1, 1, 1}));
    }

    @Test
    void minOpsAllZeros() {
        assertEquals(
                1,
                new MinimumOperationsToMakeBinaryArrayElementsEqualToOne2()
                        .minOperations(new int[] {0, 0, 0, 0}));
    }

    @Test
    void minOpsOneElement() {
        assertEquals(
                0,
                new MinimumOperationsToMakeBinaryArrayElementsEqualToOne2()
                        .minOperations(new int[] {1}));

        assertEquals(
                1,
                new MinimumOperationsToMakeBinaryArrayElementsEqualToOne2()
                        .minOperations(new int[] {0}));
    }

    @Test
    void minOpsEmptyArray() {
        assertEquals(
                0,
                new MinimumOperationsToMakeBinaryArrayElementsEqualToOne2()
                        .minOperations(new int[] {}));
    }
}
