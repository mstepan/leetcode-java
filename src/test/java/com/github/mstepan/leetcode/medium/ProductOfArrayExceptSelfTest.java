package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ProductOfArrayExceptSelfTest {

    @Test
    void productExceptSelf() {
        int[] res = new ProductOfArrayExceptSelf().productExceptSelf(new int[] {1, 2, 3, 4});
        assertArrayEquals(new int[] {24, 12, 8, 6}, res);
    }

    @Test
    void productExceptSelfEmptyArray() {
        int[] res = new ProductOfArrayExceptSelf().productExceptSelf(new int[] {});
        assertArrayEquals(new int[] {}, res);
    }

    @Test
    void productExceptSelfOneElementArray() {
        int[] res = new ProductOfArrayExceptSelf().productExceptSelf(new int[] {10});
        assertArrayEquals(new int[] {0}, res);
    }
}
