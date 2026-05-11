package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MinimumAsciiDeleteSumForTwoStrings.minimumDeleteSum;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumAsciiDeleteSumForTwoStringsTest {

    @Test
    void case1() {
        assertEquals(231, minimumDeleteSum("sea", "eat"));
    }

    @Test
    void case2() {
        assertEquals(403, minimumDeleteSum("delete", "leet"));
    }
}
