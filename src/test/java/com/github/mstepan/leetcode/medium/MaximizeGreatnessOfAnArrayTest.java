package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MaximizeGreatnessOfAnArray.maximizeGreatness;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximizeGreatnessOfAnArrayTest {

    @Test
    void case1() {
        assertEquals(4, maximizeGreatness(new int[] {1, 3, 5, 2, 1, 3, 1}));
    }

    @Test
    void case2() {
        assertEquals(3, maximizeGreatness(new int[] {1, 2, 3, 4}));
    }
}
