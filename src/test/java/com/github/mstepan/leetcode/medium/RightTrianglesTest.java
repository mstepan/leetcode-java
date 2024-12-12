package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.RightTriangles.numberOfRightTriangles;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RightTrianglesTest {

    @Test
    void case1() {

        int[][] grid =
                new int[][] {
                    {0, 1, 0},
                    {0, 1, 1},
                    {0, 1, 0}
                };

        assertEquals(2, numberOfRightTriangles(grid));
    }

    @Test
    void case2() {

        int[][] grid =
                new int[][] {
                    {1, 0, 0, 0},
                    {0, 1, 0, 1},
                    {1, 0, 0, 0}
                };

        assertEquals(0, numberOfRightTriangles(grid));
    }

    @Test
    void case3() {

        int[][] grid =
                new int[][] {
                    {1, 0, 1},
                    {1, 0, 0},
                    {1, 0, 0}
                };

        assertEquals(2, numberOfRightTriangles(grid));
    }

    @Test
    void singleCellCase() {

        int[][] grid = new int[][] {{1}};

        assertEquals(0, numberOfRightTriangles(grid));
    }

    @Test
    void twoCellsAsColumn() {

        int[][] grid = new int[][] {{1}, {1}};

        assertEquals(0, numberOfRightTriangles(grid));
    }

    @Test
    void twoCellsAsRow() {

        int[][] grid = new int[][] {{1, 1}};

        assertEquals(0, numberOfRightTriangles(grid));
    }

    @Test
    void caseWithOverlapping() {

        int[][] grid =
                new int[][] {
                    {0, 0},
                    {0, 1},
                    {1, 1}
                };

        assertEquals(1, numberOfRightTriangles(grid));
    }

    @Test
    void caseWithRightTriangleNotAdjacentCells() {

        int[][] grid =
                new int[][] {
                    {0, 0},
                    {1, 1},
                    {1, 0},
                    {0, 1}
                };

        assertEquals(2, numberOfRightTriangles(grid));
    }
}
