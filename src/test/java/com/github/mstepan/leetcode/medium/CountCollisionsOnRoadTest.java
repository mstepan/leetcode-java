package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.CountCollisionsOnRoad.countCollisions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountCollisionsOnRoadTest {

    @Test
    void case1() {
        assertEquals(5, countCollisions("RLRSLL"));
    }

    @Test
    void case2() {
        assertEquals(0, countCollisions("LLRR"));
    }

    @Test
    void moreComplexCase() {
        assertEquals(20, countCollisions("SSRSSRLLRSLLRSRSSRLRRRRLLRRLSSRR"));
    }
}
