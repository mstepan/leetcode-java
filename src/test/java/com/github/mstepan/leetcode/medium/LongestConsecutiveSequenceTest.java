package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.LongestConsecutiveSequence.longestConsecutive;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestConsecutiveSequenceTest {

    @Test
    void case1() {
        assertEquals(4, longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
    }

    @Test
    void case2() {
        assertEquals(9, longestConsecutive(new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

    @Test
    void case3() {
        assertEquals(3, longestConsecutive(new int[] {1, 0, 1, 2}));
    }
}
