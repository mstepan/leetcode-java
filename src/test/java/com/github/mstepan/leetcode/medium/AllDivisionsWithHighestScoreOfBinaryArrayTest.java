package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.AllDivisionsWithHighestScoreOfBinaryArray.maxScoreIndices;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class AllDivisionsWithHighestScoreOfBinaryArrayTest {

    @Test
    void case1() {
        assertEquals(List.of(2, 4), maxScoreIndices(new int[] {0, 0, 1, 0}));
    }

    @Test
    void case2() {
        assertEquals(List.of(3), maxScoreIndices(new int[] {0, 0, 0}));
    }

    @Test
    void case3() {
        assertEquals(List.of(0), maxScoreIndices(new int[] {1, 1}));
    }
}
