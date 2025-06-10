package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.ArithmeticSlices.numberOfArithmeticSlices;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ArithmeticSlicesTest {

    @Test
    void checkArrayOfIntWithMinusOneAndZero() {
        assertEquals(1, numberOfArithmeticSlices(new int[] {-1, 0, 1, 0}));
    }

    @Test
    void checkArrayOfIntWithOneAndZero() {
        assertEquals(0, numberOfArithmeticSlices(new int[] {1, 0, 1, 0}));
    }

    @Test
    void checkEmptyArrayOfInt() {
        assertEquals(0, numberOfArithmeticSlices(new int[] {}));
    }
}
