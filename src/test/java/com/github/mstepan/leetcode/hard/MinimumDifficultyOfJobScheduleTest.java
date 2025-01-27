package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.MinimumDifficultyOfJobSchedule.minDifficulty;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumDifficultyOfJobScheduleTest {

    @Test
    void case1() {
        assertEquals(7, minDifficulty(new int[] {6, 5, 4, 3, 2, 1}, 2));
    }

    @Test
    void case2() {
        assertEquals(-1, minDifficulty(new int[] {9, 9, 9}, 4));
    }

    @Test
    void case3() {
        assertEquals(3, minDifficulty(new int[] {1, 1, 1}, 3));
    }
}
