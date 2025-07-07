package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.WordSearch.exist;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WordSearchTest {

    @Test
    void searchCase1() {
        assertTrue(
                exist(
                        new char[][] {
                            {'A', 'B', 'C', 'E'},
                            {'S', 'F', 'C', 'S'},
                            {'A', 'D', 'E', 'E'}
                        },
                        "ABCCED"));
    }

    @Test
    void searchCase2() {
        assertTrue(
                exist(
                        new char[][] {
                            {'A', 'B', 'C', 'E'},
                            {'S', 'F', 'C', 'S'},
                            {'A', 'D', 'E', 'E'}
                        },
                        "SEE"));
    }

    @Test
    void searchCase3() {
        assertFalse(
                exist(
                        new char[][] {
                            {'A', 'B', 'C', 'E'},
                            {'S', 'F', 'C', 'S'},
                            {'A', 'D', 'E', 'E'}
                        },
                        "ABCB"));
    }
}
