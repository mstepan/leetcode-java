package com.github.mstepan.leetcode.medium;

import java.util.*;

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
     * <p>space: O(1)
     */
    public static long numberOfRightTriangles(int[][] grid) {
        checkIsMatrix(grid);

        if (grid.length == 0) {
            return 0L;
        }

        final int rows = grid.length;
        final int cols = grid[0].length;

        Set<Triangle> handledTriangles = new HashSet<>();

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {

                checkBinaryDigit(grid[row][col]);

                if (grid[row][col] == 1) {
                    // case 1
                    if (grid[row][prevCol(grid, col)] == 1
                            && grid[nextRow(grid, row)][prevCol(grid, col)] == 1) {
                        if (allCellsUnique(
                                row,
                                col,
                                row,
                                prevCol(grid, col),
                                nextRow(grid, row),
                                prevCol(grid, col))) {

                            handledTriangles.add(
                                    Triangle.fromRowsAndCols(
                                            row,
                                            col,
                                            row,
                                            prevCol(grid, col),
                                            nextRow(grid, row),
                                            prevCol(grid, col)));
                        }
                    }

                    // case 2
                    if (grid[row][nextCol(grid, col)] == 1
                            && grid[nextRow(grid, row)][nextCol(grid, col)] == 1) {

                        if (allCellsUnique(
                                row,
                                col,
                                row,
                                nextCol(grid, col),
                                nextRow(grid, row),
                                nextCol(grid, col))) {

                            handledTriangles.add(
                                    Triangle.fromRowsAndCols(
                                            row,
                                            col,
                                            row,
                                            nextCol(grid, col),
                                            nextRow(grid, row),
                                            nextCol(grid, col)));
                        }
                    }

                    // case 3
                    if (grid[nextRow(grid, row)][col] == 1
                            && grid[nextRow(grid, row)][prevCol(grid, col)] == 1) {
                        if (allCellsUnique(
                                row,
                                col,
                                nextRow(grid, row),
                                col,
                                nextRow(grid, row),
                                prevCol(grid, col))) {

                            handledTriangles.add(
                                    Triangle.fromRowsAndCols(
                                            row,
                                            col,
                                            nextRow(grid, row),
                                            col,
                                            nextRow(grid, row),
                                            prevCol(grid, col)));
                        }
                    }

                    // case 4
                    if (grid[nextRow(grid, row)][col] == 1
                            && grid[nextRow(grid, row)][nextCol(grid, col)] == 1) {
                        if (allCellsUnique(
                                row,
                                col,
                                nextRow(grid, row),
                                col,
                                nextRow(grid, row),
                                nextCol(grid, col))) {

                            handledTriangles.add(
                                    Triangle.fromRowsAndCols(
                                            row,
                                            col,
                                            nextRow(grid, row),
                                            col,
                                            nextRow(grid, row),
                                            nextCol(grid, col)));
                        }
                    }
                }
            }
        }

        return handledTriangles.size();
    }

    private static class Cell implements Comparable<Cell> {
        final int row;
        final int col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Cell other) {
            int cmp = Integer.compare(row, other.row);

            if (cmp != 0) {
                return cmp;
            }

            return Integer.compare(col, other.col);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return row == cell.row && col == cell.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private static class Triangle {
        final List<Cell> cells;

        public Triangle(List<Cell> cells) {
            List<Cell> sortedCells = new ArrayList<>(cells);
            Collections.sort(sortedCells);
            this.cells = Collections.unmodifiableList(sortedCells);
        }

        public static Triangle fromRowsAndCols(
                int row1, int col1, int row2, int col2, int row3, int col3) {
            return new Triangle(
                    List.of(new Cell(row1, col1), new Cell(row2, col2), new Cell(row3, col3)));
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Triangle triangle = (Triangle) o;
            return Objects.equals(cells, triangle.cells);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(cells);
        }
    }

    private static boolean allCellsUnique(
            int row1, int col1, int row2, int col2, int row3, int col3) {
        return isDifferentCell(row1, col1, row2, col2)
                && isDifferentCell(row1, col1, row3, col3)
                && isDifferentCell(row2, col2, row3, col3);
    }

    private static boolean isDifferentCell(int row1, int col1, int row2, int col2) {
        return row1 != row2 || col1 != col2;
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

    private static int nextRow(int[][] grid, int row) {
        return (row + 1) % grid.length;
    }

    //    private static int prevRow(int[][] grid, int row) {
    //        return (row == 0) ? grid.length - 1 : row - 1;
    //    }

    private static int nextCol(int[][] grid, int col) {
        return (col + 1) % grid[0].length;
    }

    private static int prevCol(int[][] grid, int col) {
        return (col == 0) ? grid[0].length - 1 : col - 1;
    }
}
