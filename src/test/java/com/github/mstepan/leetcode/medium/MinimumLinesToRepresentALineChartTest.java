package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MinimumLinesToRepresentALineChart.minimumLines;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumLinesToRepresentALineChartTest {

    @Test
    void case1() {
        assertEquals(
                3,
                minimumLines(
                        new int[][] {
                            {1, 7}, {2, 6}, {3, 5}, {4, 4},
                            {5, 4}, {6, 3}, {7, 2}, {8, 1}
                        }));
    }

    @Test
    void case2() {
        assertEquals(1, minimumLines(new int[][] {{3, 4}, {1, 2}, {7, 8}, {2, 3}}));
    }

    @Test
    void noPointsNoLines() {
        assertEquals(0, minimumLines(new int[][] {}));
    }

    @Test
    void onePointZeroLines() {
        assertEquals(0, minimumLines(new int[][] {{3, 4}}));
    }

    @Test
    void twoPointExactlyOneLine() {
        assertEquals(1, minimumLines(new int[][] {{3, 4}, {10, 26}}));

        assertEquals(1, minimumLines(new int[][] {{0, 0}, {14, 7}}));
    }

    @Test
    void caseFromPrompt() {
        assertEquals(
                29,
                minimumLines(
                        new int[][] {
                            {72, 98}, {62, 27}, {32, 7}, {71, 4}, {25, 19}, {91, 30}, {52, 73},
                            {10, 9}, {99, 71}, {47, 22}, {19, 30}, {80, 63}, {18, 15}, {48, 17},
                            {77, 16}, {46, 27}, {66, 87}, {55, 84}, {65, 38}, {30, 9}, {50, 42},
                            {100, 60}, {75, 73}, {98, 53}, {22, 80}, {41, 61}, {37, 47}, {95, 8},
                            {51, 81}, {78, 79}, {57, 95}
                        }));
    }
}
