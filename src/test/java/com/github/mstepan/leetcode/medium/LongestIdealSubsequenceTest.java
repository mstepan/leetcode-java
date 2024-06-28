package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestIdealSubsequenceTest {

    @Test
    void longestIdealString1() {
        assertEquals(4, new LongestIdealSubsequence().longestIdealString("acfgbd", 2));
    }

    @Test
    void longestIdealString2() {
        assertEquals(4, new LongestIdealSubsequence().longestIdealString("abcd", 3));
    }

    @Test
    void longestIdealString3() {
        assertEquals(9, new LongestIdealSubsequence().longestIdealString("slddedwfmo", 16));
    }
}
