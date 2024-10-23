package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.CountNicePairsInArray.countNicePairs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountNicePairsInArrayTest {

    @Test
    void case1() {
        assertEquals(2, countNicePairs(new int[] {42, 11, 1, 97}));
    }

    @Test
    void case2() {
        assertEquals(4, countNicePairs(new int[] {13, 10, 35, 24, 76}));
    }
}
