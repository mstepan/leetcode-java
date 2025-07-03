package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 1138. Alphabet Board Path
 *
 * <p>https://leetcode.com/problems/alphabet-board-path/
 */
public class AlphabetBoardPath {

    /**
     * time: O(K)
     *
     * <p>space: O(8K) ~ O(K)
     *
     * <p>K = target.length()
     */
    public static String alphabetBoardPath(String target) {
        Objects.requireNonNull(target);

        StringBuilder res = new StringBuilder();

        Cell last = charLocation('a');

        for (int i = 0; i < target.length(); ++i) {
            char ch = target.charAt(i);
            Cell curLocation = charLocation(ch);

            String path = calculatePath(last, curLocation, ch == 'z');
            res.append(path).append("!");
            last = curLocation;
        }

        return res.toString();
    }

    record Cell(int row, int col) {}

    private static Cell charLocation(char ch) {
        final int colsCount = 5;

        int idx = ch - 'a';
        return new Cell(idx / colsCount, idx % colsCount);
    }

    private static String calculatePath(Cell from, Cell to, boolean lastRow) {
        assert from != null;
        assert to != null;

        StringBuilder res = new StringBuilder();

        // For the last cell we should move col-wise first otherwise will
        // jump out of border cells.
        if (lastRow) {
            appendColPath(res, from, to);
            appendRowPath(res, from, to);
        } else {
            appendRowPath(res, from, to);
            appendColPath(res, from, to);
        }

        return res.toString();
    }

    private static void appendRowPath(StringBuilder res, Cell from, Cell to) {
        int rowOffset = from.row - to.row;
        if (rowOffset != 0) {
            res.append(String.valueOf(rowOffset < 0 ? 'D' : 'U').repeat(Math.abs(rowOffset)));
        }
    }

    private static void appendColPath(StringBuilder res, Cell from, Cell to) {
        int colOffset = from.col - to.col;
        if (colOffset != 0) {
            res.append(String.valueOf(colOffset < 0 ? 'R' : 'L').repeat(Math.abs(colOffset)));
        }
    }
}
