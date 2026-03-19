package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.NumberOfWaysToReachPositionAfterExactlyKsteps.numberOfWays;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberOfWaysToReachPositionAfterExactlyKstepsTest {

    @Test
    void case1() {
        assertEquals(3, numberOfWays(1, 2, 3));
    }

    @Test
    void case2() {
        assertEquals(0, numberOfWays(2, 5, 10));
    }
}
