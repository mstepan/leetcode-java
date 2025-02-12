package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.FindTheNumberOfWaysToPlacePeople2.numberOfPairs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindTheNumberOfWaysToPlacePeople2Test {

    @Test
    void case1() {
        assertEquals(0, numberOfPairs(new int[][] {{1, 1}, {2, 2}, {3, 3}}));
    }

    @Test
    void case2() {
        assertEquals(2, numberOfPairs(new int[][] {{6, 2}, {4, 4}, {2, 6}}));
    }

    @Test
    void case3() {
        assertEquals(2, numberOfPairs(new int[][] {{3, 1}, {1, 3}, {1, 1}}));
    }
}
