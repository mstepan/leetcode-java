package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MaximizeSumOfSquaresOfDigits.maxSumOfSquares;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximizeSumOfSquaresOfDigitsTest {

    @Test
    void case1() {
        assertEquals("30", maxSumOfSquares(2, 3));
    }

    @Test
    void case2() {
        assertEquals("98", maxSumOfSquares(2, 17));
    }

    @Test
    void case3() {
        assertEquals("", maxSumOfSquares(1, 10));
    }

    @Test
    void case4() {
        assertEquals("9", maxSumOfSquares(1, 9));
    }

    @Test
    void case5() {
        assertEquals("900", maxSumOfSquares(3, 9));
    }

    @Test
    void case6() {
        assertEquals("999", maxSumOfSquares(3, 27));
    }

    @Test
    void case7() {
        assertEquals("991", maxSumOfSquares(3, 19));
    }

    @Test
    void case8() {
        assertEquals("", maxSumOfSquares(2, 19));
    }

    @Test
    void case9() {
        assertEquals("50000", maxSumOfSquares(5, 5));
    }
}
