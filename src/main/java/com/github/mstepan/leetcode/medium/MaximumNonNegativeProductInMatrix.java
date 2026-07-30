package com.github.mstepan.leetcode.medium;

import java.util.Objects;
import java.util.Optional;

/**
 * 1594. Maximum Non Negative Product in a Matrix
 *
 * <p>https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/description/
 */
public class MaximumNonNegativeProductInMatrix {

    private static final long MODULO = 1_000_000_007L;

    /**
     * time: O(N*M)
     *
     * <p>Space: O(N*M)
     */
    public static int maxProductPath(int[][] grid) {
        Objects.requireNonNull(grid);

        final int rows = grid.length;
        final int cols = grid[0].length;

        CellSolution[][] res = new CellSolution[rows][cols];

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {

                final long curValue = grid[row][col];

                if (curValue == 0) {
                    res[row][col] = new CellSolution(Optional.of(0L), Optional.of(0L));
                    continue;
                }

                if (row == 0 && col == 0) {
                    if (curValue >= 0) {
                        res[0][0] = new CellSolution(Optional.empty(), Optional.of(curValue));
                    } else {
                        res[0][0] = new CellSolution(Optional.of(curValue), Optional.empty());
                    }

                } else {
                    Optional<Long> minNegative = Optional.empty();
                    Optional<Long> maxPositive = Optional.empty();

                    // not the 0-th column
                    if (col > 0) {
                        CellSolution left = res[row][col - 1];

                        minNegative = minNegative(minNegative, curValue, left);
                        maxPositive = maxPositive(maxPositive, curValue, left);
                    }

                    // not the 0-th row
                    if (row > 0) {
                        CellSolution top = res[row - 1][col];

                        minNegative = minNegative(minNegative, curValue, top);
                        maxPositive = maxPositive(maxPositive, curValue, top);
                    }
                    res[row][col] = new CellSolution(minNegative, maxPositive);
                }
            }
        }

        CellSolution finalResult = res[rows - 1][cols - 1];

        return finalResult.maxPositive.map(value -> (int) (value % MODULO)).orElse(-1);
    }

    private static Optional<Long> minNegative(
            Optional<Long> minNegative, long curValue, CellSolution adjCell) {

        Optional<Long> best = minNegative;

        if (adjCell.maxPositive.isPresent()) {
            long candidate1 = curValue * adjCell.maxPositive.get();

            if (candidate1 <= 0 && (best.isEmpty() || candidate1 < best.get())) {
                best = Optional.of(candidate1);
            }
        }

        if (adjCell.minNegative.isPresent()) {
            long candidate1 = curValue * adjCell.minNegative.get();

            if (candidate1 <= 0 && (best.isEmpty() || candidate1 < best.get())) {
                best = Optional.of(candidate1);
            }
        }

        return best;
    }

    private static Optional<Long> maxPositive(
            Optional<Long> maxPositive, long curValue, CellSolution adjCell) {

        Optional<Long> best = maxPositive;

        if (adjCell.maxPositive.isPresent()) {
            long candidate1 = curValue * adjCell.maxPositive.get();

            if (candidate1 >= 0 && (best.isEmpty() || candidate1 > best.get())) {
                best = Optional.of(candidate1);
            }
        }

        if (adjCell.minNegative.isPresent()) {
            long candidate1 = curValue * adjCell.minNegative.get();

            if (candidate1 >= 0 && (best.isEmpty() || candidate1 > best.get())) {
                best = Optional.of(candidate1);
            }
        }

        return best;
    }

    // Long type should be enough here to do not have overflow
    // max possible value = 4 ** 29 = 288230376151711744L
    //               Long.MAX_VALUE = 9223372036854775807L
    record CellSolution(Optional<Long> minNegative, Optional<Long> maxPositive) {}
}
