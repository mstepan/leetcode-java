package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.SumOfSquareNumbers.judgeSquareSum;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SumOfSquareNumbersTest {

    @Test
    void zero() {
        assertTrue(judgeSquareSum(0));
    }

    @Test
    void perfectSquare() {
        assertTrue(judgeSquareSum(4)); // 0^2 + 2^2
        assertTrue(judgeSquareSum(1)); // 1^2 + 0^2
        assertTrue(judgeSquareSum(9)); // 0^2 + 3^2
    }

    @Test
    void sumOfTwoSquares() {
        assertTrue(judgeSquareSum(5)); // 1^2 + 2^2
        assertTrue(judgeSquareSum(2)); // 1^2 + 1^2
        assertTrue(judgeSquareSum(10)); // 1^2 + 3^2
    }

    @Test
    void notSumOfTwoSquares() {
        assertFalse(judgeSquareSum(3));
        assertFalse(judgeSquareSum(7));
        assertFalse(judgeSquareSum(11));
    }

    @Test
    void largeNumber() {
        assertTrue(judgeSquareSum(1000000)); // 1000^2 + 0^2
        assertFalse(judgeSquareSum(999999));
    }

    @Test
    void negativeInput() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> judgeSquareSum(-1));
        assertTrue(ex.getMessage().contains("Negative value detected"));
    }
}
