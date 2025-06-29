package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReverseIntegerTest {

    @Test
    void reversePositiveNumber() {
        assertEquals(321, ReverseInteger.reverse(123));
        assertEquals(21, ReverseInteger.reverse(120));
    }

    @Test
    void reverseNegativeNumber() {
        assertEquals(-321, ReverseInteger.reverse(-123));
        assertEquals(-21, ReverseInteger.reverse(-120));
    }

    @Test
    void reverseZero() {
        assertEquals(0, ReverseInteger.reverse(0));
    }

    @Test
    void reverseMaxInteger() {
        assertEquals(0, ReverseInteger.reverse(Integer.MAX_VALUE));
    }

    @Test
    void reverseMinInteger() {
        assertEquals(0, ReverseInteger.reverse(Integer.MIN_VALUE));
    }

    @Test
    void reverseWithOverflow() {
        assertEquals(0, ReverseInteger.reverse(1_234_567_899));
    }

    @Test
    void reversePolynomialNumbers() {
        assertEquals(123321, ReverseInteger.reverse(123321));
        assertEquals(9999, ReverseInteger.reverse(9999));
        assertEquals(-6776, ReverseInteger.reverse(-6776));
    }

    @Test
    void reverseSingleDigitNumbers() {
        assertEquals(1, ReverseInteger.reverse(1));
        assertEquals(9, ReverseInteger.reverse(9));
        assertEquals(-5, ReverseInteger.reverse(-5));
        assertEquals(-4, ReverseInteger.reverse(-4));
    }

    @Test
    void reverseBigPositiveNumber() {
        assertEquals(2_147_483_641, ReverseInteger.reverse(1_463_847_412));
    }
}
