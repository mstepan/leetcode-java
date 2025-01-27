package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 675. Cut Off Trees for Golf Event
 *
 * <p>https://leetcode.com/problems/cut-off-trees-for-golf-event/description/
 */
public class CutOffTreesForGolfEvent {

    /**
     * N = max(forest.size(), forest.get(0).size())
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

    /** Uses BFS to find the shortest path length between 'from' and 'to' cells. */
    private static int shortestPathLength(int[][] m, Cell from, Cell to) {

        if (m[from.row][from.col] == 0 || m[to.row][to.col] == 0) {
            return Integer.MAX_VALUE;
        }

        if (from.equals(to)) {
            return 0;
        }

        Set<Cell> marked = new HashSet<>();
        marked.add(from);

        Queue<Path> q = new ArrayDeque<>();
        q.add(new Path(from, 0));

        while (!q.isEmpty()) {
            Path curPath = q.poll();
            Cell cur = curPath.last;

            for (Cell next : nextCells(m, cur)) {
                if (next.equals(to)) {
                    return curPath.length + 1;
                }

                if (m[next.row][next.col] != 0 && !marked.contains(next)) {
                    marked.add(next);
                    q.add(new Path(next, curPath.length + 1));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static List<Cell> nextCells(int[][] m, Cell cur) {

        int[][] offsets = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        final int rows = m.length;

        assert m.length > 0;
        final int cols = m[0].length;

        List<Cell> res = new ArrayList<>();

        for (int[] singleOffset : offsets) {
            int newRow = cur.row + singleOffset[0];
            int newCol = cur.col + singleOffset[1];

            if (newRow >= 0
                    && newRow < rows
                    && newCol >= 0
                    && newCol < cols
                    && m[newRow][newCol] != 0) {
                res.add(new Cell(m[newRow][newCol], newRow, newCol));
            }
        }

        return res;
    }

    record Cell(int height, int row, int col) {
        private static final Comparator<Cell> HEIGHT_ASC = Comparator.comparingInt(Cell::height);
    }

    record Path(Cell last, int length) {}
}
