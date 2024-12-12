package com.github.mstepan.leetcode.medium;

/**
 * 3128. Right Triangles
 *
 * <p>https://leetcode.com/problems/right-triangles/description/
 */
public class RightTriangles {

    /**
     * N = 1000, M = 1000
     *
     * <p>time: O(N*M)
     *
     * <p>space: O(N+M)
     */
    public static long numberOfRightTriangles(int[][] grid) {
        checkIsMatrix(grid);

        if (grid.length == 0) {
            return 0L;
        }

        final int rows = grid.length;
        final int cols = grid[0].length;

        int[] rowsOnes = new int[rows];
        int[] colsOnes = new int[cols];

        // count 1s for every row and column and store inside 'rowsOnes' and 'colsOnes' arrays
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                checkBinaryDigit(grid[row][col]);

                if (grid[row][col] == 1) {
                    ++rowsOnes[row];
                    ++colsOnes[col];
                }
            }
        }

        long totalTrianglesCount = 0L;
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (grid[row][col] == 1) {
                    assert rowsOnes[row] > 0;
                    assert colsOnes[col] > 0;

                    int possibleRightTrianglesCount = (rowsOnes[row] - 1) * (colsOnes[col] - 1);
                    totalTrianglesCount += possibleRightTrianglesCount;
                }
            }
        }

        return totalTrianglesCount;
    }

    private static void checkIsMatrix(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("'grid' is null");
        }

        if (grid.length == 0) {
            return;
        }

        int colsCount = -1;

        for (int[] singleRow : grid) {
            if (singleRow == null) {
                throw new IllegalArgumentException("'singleRow' is null");
            }

            if (colsCount == -1) {
                colsCount = singleRow.length;
            } else {
                if (colsCount != singleRow.length) {
                    throw new IllegalArgumentException("'grid' contains row of different length");
                }
            }
        }
    }

    private static void checkBinaryDigit(int value) {
        if (!(value == 0 || value == 1)) {
            throw new IllegalArgumentException("'value' is not 0 or 1");
        }
    }
}
