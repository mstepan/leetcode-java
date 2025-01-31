package com.github.mstepan.leetcode.hard;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 675. Cut Off Trees for Golf Event. Concurrent version.
 *
 * <p>Concurrency is different from parallelism but in java everything that is concurrent called
 * parallel (such as 'Arrays.parallelSort') so to be consistent with java naming the class called
 * appropriately. )))
 *
 * <p>https://leetcode.com/problems/cut-off-trees-for-golf-event/description/
 */
public class CutOffTreesForGolfEventParallel {

    public static int cutOffTree(List<List<Integer>> forest) {
        Objects.requireNonNull(forest);
        int[][] m = CutOffTreesForGolfEvent.toMatrix(forest);

        final int rows = m.length;
        final int cols = m[0].length;

        List<CutOffTreesForGolfEvent.Cell> cellsWithTree = new ArrayList<>(rows);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (m[row][col] > 1) {
                    cellsWithTree.add(new CutOffTreesForGolfEvent.Cell(m[row][col], row, col));
                }
            }
        }

        cellsWithTree.sort(CutOffTreesForGolfEvent.Cell.HEIGHT_ASC);
        cellsWithTree.add(0, new CutOffTreesForGolfEvent.Cell(m[0][0], 0, 0));

        ForkJoinPool pool = new ForkJoinPool();
        try {
            int stepsCnt =
                    pool.invoke(
                            new ShortestPathLengthCalculatorTask(
                                    m, cellsWithTree, 0, cellsWithTree.size() - 1));

            return stepsCnt == Integer.MAX_VALUE ? -1 : stepsCnt;
        } finally {
            pool.shutdownNow();
        }
    }

    private static final class ShortestPathLengthCalculatorTask extends RecursiveTask<Integer> {

        private final int[][] m;
        private final List<CutOffTreesForGolfEvent.Cell> cellsWithTree;
        private final int from;
        private final int to;

        public ShortestPathLengthCalculatorTask(
                int[][] m, List<CutOffTreesForGolfEvent.Cell> cellsWithTree, int from, int to) {
            this.m = m;
            this.cellsWithTree = cellsWithTree;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Integer compute() {

            int elemsCnt = to - from + 1;

            if (elemsCnt < 2) {
                return 0;
            }

            if (elemsCnt == 2) {
                return CutOffTreesForGolfEvent.shortestPathLength(
                        m, cellsWithTree.get(from), cellsWithTree.get(to));
            }

            int middle = from + (to - from) / 2;

            ShortestPathLengthCalculatorTask left =
                    new ShortestPathLengthCalculatorTask(m, cellsWithTree, from, middle);

            ShortestPathLengthCalculatorTask right =
                    new ShortestPathLengthCalculatorTask(m, cellsWithTree, middle + 1, to);

            int middleStepsCnt =
                    CutOffTreesForGolfEvent.shortestPathLength(
                            m, cellsWithTree.get(middle), cellsWithTree.get(middle + 1));

            int leftSteps = left.fork().join();
            int rightSteps = right.fork().join();

            if (leftSteps == Integer.MAX_VALUE
                    || rightSteps == Integer.MAX_VALUE
                    || middleStepsCnt == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            return leftSteps + rightSteps + middleStepsCnt;
        }
    }
}
