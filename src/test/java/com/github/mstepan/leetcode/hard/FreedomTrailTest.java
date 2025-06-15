package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.FreedomTrail.findRotateSteps;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FreedomTrailTest {

    @Test
    void findRotateStepsCase1() {
        assertEquals(4, findRotateSteps("godding", "gd"));
    }

    @Test
    void findRotateStepsCase2() {
        assertEquals(13, findRotateSteps("godding", "godding"));
    }

    @Test
    void findRotateStepsCase3() {
        assertEquals(11, findRotateSteps("iotfo", "fioot"));
    }
}
