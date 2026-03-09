package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.RotateFunction.maxRotateFunction;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RotateFunctionTest {

    @Test
    void case1() {
        assertEquals(26, maxRotateFunction(new int[] {4, 3, 2, 6}));
    }

    @Test
    void case2() {
        assertEquals(0, maxRotateFunction(new int[] {100}));
    }
}
