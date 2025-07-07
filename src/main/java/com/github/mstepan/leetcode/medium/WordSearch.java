package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 79. Word Search
 *
 * <p>https://leetcode.com/problems/word-search/
 */
public class WordSearch {

    /**
     * N = board rows M = board cols
     *
     * <p>time: O(N^2*M^2) = 6^2 * 6^2 = 36 * 36 = 1296
     *
     * <p>space: O(N*M)
     */
    public static boolean exist(char[][] board, String word) {
        Objects.requireNonNull(board);
        Objects.requireNonNull(word);

        final int rows = board.length;
        final int cols = board[0].length;

        if (word.length() > rows * cols) {
            return false;
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (searchRec(board, i, j, word, 0, 0L)) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
     * Recusrively search the word using DFS approach using function call stack for backtracking.
     */
    private static boolean searchRec(
            char[][] board, int row, int col, String word, int idx, long visited) {

        if (idx >= word.length()) {
            return true;
        }

        final int rows = board.length;
        final int cols = board[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            // out of board boundaries
            return false;
        }

        long boardIdx = row * cols + col;

        if ((visited & (1 << boardIdx)) != 0) {
            // already visited on a board
            return false;
        }

        if (word.charAt(idx) != board[row][col]) {
            return false;
        }

        long newVisited = visited | (1 << boardIdx);

        return searchRec(board, row - 1, col, word, idx + 1, newVisited)
                || searchRec(board, row + 1, col, word, idx + 1, newVisited)
                || searchRec(board, row, col - 1, word, idx + 1, newVisited)
                || searchRec(board, row, col + 1, word, idx + 1, newVisited);
    }
}
