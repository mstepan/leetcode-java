package com.github.mstepan.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemoveBoxesTest {

    @Test
    void case1() {
        assertEquals(23, RemoveBoxes.removeBoxes(new int[] {1, 3, 2, 2, 2, 3, 4, 3, 1}));
    }

    @Test
    void case2() {
        assertEquals(9, RemoveBoxes.removeBoxes(new int[] {1, 1, 1}));
    }

    @Test
    void case3() {
        assertEquals(1, RemoveBoxes.removeBoxes(new int[] {1}));
    }
}
