package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LastStoneWeight2Test {

    @Test
    void case1() {
        assertEquals(1, LastStoneWeight2.lastStoneWeightII(new int[] {2, 7, 4, 1, 8, 1}));
    }

    @Test
    void case2() {
        assertEquals(5, LastStoneWeight2.lastStoneWeightII(new int[] {31, 26, 33, 21, 40}));
    }

    @Test
    void singleElement() {
        assertEquals(7, LastStoneWeight2.lastStoneWeightII(new int[] {7}));
    }
}
