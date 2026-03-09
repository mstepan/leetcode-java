package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MaximumCandiesAllocatedToKChildren.maximumCandies;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MaximumCandiesAllocatedToKChildrenTest {

    @Test
    void case1() {
        assertEquals(5, maximumCandies(new int[] {5, 8, 6}, 3L));
    }

    @Test
    void case2() {
        assertEquals(0, maximumCandies(new int[] {2, 5}, 11L));
    }

    @Test
    void case3() {
        assertEquals(3, maximumCandies(new int[] {4, 7, 5}, 4L));
    }

    @Test
    void zeroChildrenCountZeroCandies() {
        assertEquals(0, maximumCandies(new int[] {2, 5, 7}, 0L));
    }

    @Test
    void zeroCandiesForEmptyArray() {
        assertEquals(0, maximumCandies(new int[] {}, 7L));
    }

    @Test
    void nullArrayShouldFail() {
        assertThrows(NullPointerException.class, () -> maximumCandies(null, 5L));
    }

    @Test
    void negativeChildrenCountShouldFail() {
        assertThrows(
                IllegalArgumentException.class, () -> maximumCandies(new int[] {1, 2, 3}, -1L));
    }

    @Test
    void oneChildGetsMaxPile() {
        assertEquals(11, maximumCandies(new int[] {4, 11, 7}, 1L));
    }

    @Test
    void exactSplitAcrossPiles() {
        assertEquals(4, maximumCandies(new int[] {8, 8, 8}, 6L));
    }

    @Test
    void notEnoughForTwoCandiesPerChildFallsBackToOne() {
        assertEquals(1, maximumCandies(new int[] {2, 2, 2}, 5L));
    }

    @Test
    void hugeChildrenCountStillReturnsZero() {
        assertEquals(0, maximumCandies(new int[] {1, 1, 1}, 10_000_000_000L));
    }
}
