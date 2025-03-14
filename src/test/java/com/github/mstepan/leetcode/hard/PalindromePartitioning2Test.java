package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.PalindromePartitioning2.minCut;
import static com.github.mstepan.leetcode.hard.PalindromePartitioning2.palindromesTable;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PalindromePartitioning2Test {

    @Test
    void minCutNormalCase() {
        assertEquals(1, minCut("bbabbaca"));
    }

    @Test
    void case1() {
        assertEquals(1, minCut("aab"));
    }

    @Test
    void case2() {
        assertEquals(0, minCut("a"));
    }

    @Test
    void case3() {
        assertEquals(0, minCut("bab"));
    }

    @Test
    void case4() {
        assertEquals(0, minCut(""));
    }

    @Test
    void palindromesTableNormalCase() {
        char[] arr = "bbabbaca".toCharArray();
        boolean[][] sol = palindromesTable(arr);

        assertNotNull(sol);

        assertMatrixEquals(
                new boolean[][] {
                    {true, true, false, false, true, false, false, false},
                    {true, true, false, true, false, false, false, false},
                    {true, true, true, false, false, true, false, false},
                    {true, true, true, true, true, false, false, false},
                    {true, true, true, true, true, false, false, false},
                    {true, true, true, true, true, true, false, true},
                    {true, true, true, true, true, true, true, false},
                    {true, true, true, true, true, true, true, true},
                },
                sol);
    }

    private void assertMatrixEquals(boolean[][] expected, boolean[][] actual) {

        for (int row = 0; row < expected.length; row++) {
            for (int col = 0; col < expected[row].length; col++) {
                assertEquals(
                        expected[row][col],
                        actual[row][col],
                        "Matrix are different at row = %d, col = %d".formatted(row, col));
            }
        }
    }
}
