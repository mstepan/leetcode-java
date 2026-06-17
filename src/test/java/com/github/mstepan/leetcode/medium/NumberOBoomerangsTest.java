package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.NumberOBoomerangs.numberOfBoomerangs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberOBoomerangsTest {

    @Test
    void case1() {
        assertEquals(2, numberOfBoomerangs(new int[][] {{0, 0}, {1, 0}, {2, 0}}));
    }

    @Test
    void case2() {
        assertEquals(0, numberOfBoomerangs(new int[][] {{1, 1}}));
    }
}
