package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.LargestTimeForGivenDigits.largestTimeFromDigits;
import static com.github.mstepan.leetcode.medium.LargestTimeForGivenDigits.nextPermutation;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LargestTimeForGivenDigitsTest {

    @Test
    void largestTimeFromDigitsCase1() {
        assertEquals("23:41", largestTimeFromDigits(new int[] {1, 2, 3, 4}));
    }

    @Test
    void largestTimeFromDigitsCase2() {
        assertEquals("", largestTimeFromDigits(new int[] {5, 5, 5, 5}));
    }

    @Test
    void nextPermutationNormalCase() {
        assertArrayEquals(new int[] {1, 2, 4, 3}, nextPermutation(new int[] {1, 2, 3, 4}));

        assertArrayEquals(new int[] {2, 1, 3, 4}, nextPermutation(new int[] {1, 4, 3, 2}));

        assertArrayEquals(new int[] {4, 3, 2, 1}, nextPermutation(new int[] {4, 3, 1, 2}));

        assertNull(nextPermutation(new int[] {4, 3, 2, 1}));
    }
}
