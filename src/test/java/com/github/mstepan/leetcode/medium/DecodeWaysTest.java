package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.DecodeWays.numDecodings;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DecodeWaysTest {

    @Test
    void case1() {
        assertEquals(2, numDecodings("12"));
    }

    @Test
    void case2() {
        assertEquals(3, numDecodings("226"));
    }

    @Test
    void case3() {
        assertEquals(0, numDecodings("06"));
    }
}
