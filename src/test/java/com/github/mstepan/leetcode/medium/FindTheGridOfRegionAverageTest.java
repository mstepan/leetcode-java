package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.FindTheGridOfRegionAverage.resultGrid;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class FindTheGridOfRegionAverageTest {

    @Test
    void case1() {
        int[][] actual =
                resultGrid(
                        new int[][] {
                            {5, 6, 7, 10},
                            {8, 9, 10, 10},
                            {11, 12, 13, 10}
                        },
                        3);

        assertArrayEquals(
                new int[][] {
                    {9, 9, 9, 9},
                    {9, 9, 9, 9},
                    {9, 9, 9, 9}
                },
                actual);
    }

    @Test
    void case2() {
        int[][] actual =
                resultGrid(
                        new int[][] {
                            {10, 20, 30},
                            {15, 25, 35},
                            {20, 30, 40},
                            {25, 35, 45}
                        },
                        12);

        assertArrayEquals(
                new int[][] {
                    {25, 25, 25},
                    {27, 27, 27},
                    {27, 27, 27},
                    {30, 30, 30}
                },
                actual);
    }
}
