package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinNumberOfOperationsToSatisfyConditionsTest {

    @Test
    void minimumOperations1() {
        int[][] grid = {{1, 0, 2}, {1, 0, 2}};
        assertEquals(0, new MinNumberOfOperationsToSatisfyConditions().minimumOperations(grid));
    }

    @Test
    void minimumOperations2() {
        int[][] grid = {{1, 1, 1}, {0, 0, 0}};
        assertEquals(3, new MinNumberOfOperationsToSatisfyConditions().minimumOperations(grid));
    }

    @Test
    void minimumOperations3() {
        int[][] grid = {{1}, {2}, {3}};
        assertEquals(2, new MinNumberOfOperationsToSatisfyConditions().minimumOperations(grid));
    }
}
