package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LargestNumberTest {

    @Test
    public void case1() {
        assertEquals("210", LargestNumber.largestNumber(new int[] {10, 2}));
    }

    @Test
    public void case2() {
        assertEquals("9534330", LargestNumber.largestNumber(new int[] {3, 30, 34, 5, 9}));
    }

    @Test
    public void case3() {
        assertEquals("1113111311", LargestNumber.largestNumber(new int[] {111311, 1113}));
    }

    @Test
    public void case4() {
        assertEquals("0", LargestNumber.largestNumber(new int[] {0, 0}));
    }
}
