package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 675. Cut Off Trees for Golf Event -> Time Limit Exceeded for the last test case.
 *
 * <p>https://leetcode.com/problems/cut-off-trees-for-golf-event/description/
 */
public class CutOffTreesForGolfEvent {

    /**
     * N = max(rows, cols)
     *
     * <p>time: O(N^4) space: O(N^2)
     */
    public static int cutOffTree(List<List<Integer>> forest) {
        Objects.requireNonNull(forest);
        int[][] m = toMatrix(forest);

        final int rows = m.length;
        final int cols = m[0].length;

        List<Cell> cellsWithTree = new ArrayList<>(rows);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (m[row][col] > 1) {
                    cellsWithTree.add(new Cell(m[row][col], row, col));
                }
            }
        }

        cellsWithTree.sort(Cell.HEIGHT_ASC);

        int stepsCnt = 0;
        Cell last = new Cell(m[0][0], 0, 0);

        for (Cell next : cellsWithTree) {
            int pathLength = shortestPathLength(m, last, next);

            if (pathLength == Integer.MAX_VALUE) {
                return -1;
            }

            stepsCnt += pathLength;
            last = next;
        }

        return stepsCnt;
    }

    private static int[][] toMatrix(List<List<Integer>> forest) {
        int rows = forest.size();

        assert rows > 0;
        int cols = forest.get(0).size();

        int[][] matrix = new int[rows][cols];

        int rowIdx = 0;

        for (List<Integer> singleRow : forest) {

            int colIdx = 0;
            for (Integer singleColVal : singleRow) {
                matrix[rowIdx][colIdx] = singleColVal;

                ++colIdx;
            }

            ++rowIdx;
        }

        return matrix;
    }

    /** Uses standard BFS to find the shortest path length between 'from' and 'to' cells. */
    private static int shortestPathLength(int[][] m, Cell from, Cell to) {

        if (m[from.row][from.col] == 0 || m[to.row][to.col] == 0) {
            return Integer.MAX_VALUE;
        }

        if (from.isSameCell(to)) {
            return 0;
        }
        final int rows = m.length;
        final int cols = m[0].length;

        boolean[][] marked = new boolean[rows][cols];
        marked[from.row][from.col] = true;

        Queue<Path> q = new ArrayDeque<>();
        q.add(new Path(from));
        int curLayerSize = 1;

        int pathLength = 0;

        while (!q.isEmpty()) {

            int nextLayerSize = 0;
            for(int i = 0; i < curLayerSize; ++i){
                Path curPath = q.poll();
                Cell cur = curPath.last;

                for (Cell next : nextCells(cur, rows, cols)) {
                    if (next.isSameCell(to)) {
                        return pathLength + 1;
                    }

                    if (m[next.row][next.col] != 0 && !marked[next.row][next.col]) {
                        marked[next.row][next.col] = true;
                        q.add(new Path(next));
                        ++nextLayerSize;
                    }
                }
            }

            ++pathLength;
            curLayerSize = nextLayerSize;

        }

        return Integer.MAX_VALUE;
    }

    private static List<Cell> nextCells(Cell cur, int rows, int cols) {

        int[][] offsets = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };


        List<Cell> res = new ArrayList<>();

        for (int[] singleOffset : offsets) {
            int newRow = cur.row + singleOffset[0];
            int newCol = cur.col + singleOffset[1];

            if (newRow >= 0
                    && newRow < rows
                    && newCol >= 0
                    && newCol < cols) {
                res.add(new Cell(-1, newRow, newCol));
            }
        }

        return res;
    }

    record Cell(int height, int row, int col) {
        private static final Comparator<Cell> HEIGHT_ASC = Comparator.comparingInt(Cell::height);

        public boolean isSameCell(Cell other) {
            return row == other.row && col == other.col;
        }
    }

    record Path(Cell last) {}
}
