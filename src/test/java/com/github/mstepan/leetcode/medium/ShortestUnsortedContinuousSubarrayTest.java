package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ShortestUnsortedContinuousSubarrayTest {

    @Test
    void normalCase() {
        assertEquals(
                5,
                ShortestUnsortedContinuousSubarray.findUnsortedSubarray(
                        new int[] {2, 6, 4, 8, 10, 9, 15}));
    }

    @Test
    void fullySortedArray() {
        assertEquals(
                0, ShortestUnsortedContinuousSubarray.findUnsortedSubarray(new int[] {1, 2, 3, 4}));
    }

    @Test
    void singleElement() {
        assertEquals(0, ShortestUnsortedContinuousSubarray.findUnsortedSubarray(new int[] {1}));
    }

    @Test
    void boundaryCases() {
        int[][] inputs = {
            {},
            {2, 2, 2},
            {3, 2, 1},
            {2, 1, 3},
            {1, 3, 2},
            {1, 3, 2, 2, 2},
            {2, 2, 2, 1, 3},
            {Integer.MAX_VALUE, 0, Integer.MIN_VALUE}
        };
        int[] expected = {0, 0, 3, 2, 2, 4, 4, 3};
        for (int i = 0; i < inputs.length; ++i) {
            assertEquals(
                    expected[i],
                    ShortestUnsortedContinuousSubarray.findUnsortedSubarray(inputs[i]));
        }
    }

    @Test
    void nullArrayThrowsException() {
        assertThrows(
                NullPointerException.class,
                () -> {
                    ShortestUnsortedContinuousSubarray.findUnsortedSubarray(null);
                });
    }
}
