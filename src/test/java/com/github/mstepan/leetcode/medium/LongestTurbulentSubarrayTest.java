package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.LongestTurbulentSubarray.maxTurbulenceSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestTurbulentSubarrayTest {

    @Test
    void maxTurbulenceSizeCase1() {
        assertEquals(5, maxTurbulenceSize(new int[] {9, 4, 2, 10, 7, 8, 8, 1, 9}));
    }

    @Test
    void maxTurbulenceSizeCase2() {
        assertEquals(2, maxTurbulenceSize(new int[] {4, 8, 12, 16}));
    }

    @Test
    void maxTurbulenceSizeCase3() {
        assertEquals(1, maxTurbulenceSize(new int[] {100}));
    }

    @Test
    void maxTurbulenceSizeCase4() {
        assertEquals(2, maxTurbulenceSize(new int[] {1, 1, 2}));
    }
}
