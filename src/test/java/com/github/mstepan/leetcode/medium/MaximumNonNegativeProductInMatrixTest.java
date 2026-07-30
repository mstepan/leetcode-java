package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MaximumNonNegativeProductInMatrix.maxProductPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumNonNegativeProductInMatrixTest {

    @Test
    void case1() {

        System.out.println(288230376151711744L);
        System.out.println(Long.MAX_VALUE);

        assertEquals(-1, maxProductPath(new int[][] {{-1, -2, -3}, {-2, -3, -3}, {-3, -3, -2}}));
    }

    @Test
    void case2() {
        assertEquals(8, maxProductPath(new int[][] {{1, -2, 1}, {1, -2, 1}, {3, -4, 1}}));
    }

    @Test
    void case3() {
        assertEquals(0, maxProductPath(new int[][] {{1, 3}, {0, -4}}));
    }

    @Test
    void case4() {
        assertEquals(0, maxProductPath(new int[][] {{-1, 3, 0}, {3, -2, 3}, {-1, 1, -4}}));
    }

    @Test
    void handlesProductsLargerThanAnInt() {
        assertEquals(
                19_215_865,
                maxProductPath(
                        new int[][] {
                            {2, 1, 3, 0, -3, 3, -4, 4, 0, -4},
                            {-4, -3, 2, 2, 3, -3, 1, -1, 1, -2},
                            {-2, 0, -4, 2, 4, -3, -4, -1, 3, 4},
                            {-1, 0, 1, 0, -3, 3, -2, -3, 1, 0},
                            {0, -1, -2, 0, -3, -4, 0, 3, -2, -2},
                            {-4, -2, 0, -1, 0, -3, 0, 4, 0, -3},
                            {-3, -4, 2, 1, 0, -4, 2, -4, -1, -3},
                            {3, -2, 0, -4, 1, 0, 1, -3, -1, -1},
                            {3, -4, 0, 2, 0, -2, 2, -4, -2, 4},
                            {0, 4, 0, -3, -4, 3, 3, -1, -2, -2}
                        }));
    }
}
