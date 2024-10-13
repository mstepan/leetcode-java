package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MaximumProductAfterKIncrements.maximumProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MaximumProductAfterKIncrementsTest {

    @Test
    void case1() {
        assertEquals(20, maximumProduct(new int[] {0, 4}, 5));
    }

    @Test
    void case2() {
        assertEquals(216, maximumProduct(new int[] {6, 3, 3, 2}, 2));
    }

    @Test
    void overflowShouldThrowException() {
        IllegalStateException ex =
                assertThrows(
                        IllegalStateException.class,
                        () -> maximumProduct(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE}, 1));
        assertEquals("Integer overflow occurred", ex.getMessage());
    }

    @Test
    void emptyArrayShouldReturnZero() {
        assertEquals(0, maximumProduct(new int[] {}, 5));
    }

    @Test
    void nullArrayShouldThrowException() {
        NullPointerException ex =
                assertThrows(NullPointerException.class, () -> maximumProduct(null, 5));

        assertEquals("null 'nums' detected", ex.getMessage());
    }

    @Test
    void negativeKShouldThrowException() {
        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> maximumProduct(new int[] {1, 2, 3}, -1));

        assertEquals("'k' should be positive or zero: k = -1", ex.getMessage());
    }

    @Test
    void negativeValueInNumsArraysShouldThrowException() {
        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> maximumProduct(new int[] {1, 2, 3, -4}, 5));

        assertEquals("Negative value in 'nums' detected", ex.getMessage());
    }
}
