package com.github.mstepan.leetcode.medium;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

/**
 * 1594. Maximum Non Negative Product in a Matrix
 *
 * <p>https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/description/
 */
public class MaximumNonNegativeProductInMatrix {

    private static final BigInteger MODULO = BigInteger.valueOf(1_000_000_007L);

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

                final BigInteger curValue = BigInteger.valueOf(grid[row][col]);

                if (curValue.signum() == 0) {
                    res[row][col] =
                            new CellSolution(
                                    Optional.of(BigInteger.ZERO), Optional.of(BigInteger.ZERO));
                    continue;
                }

                if (row == 0 && col == 0) {
                    if (curValue.signum() >= 0) {
                        res[0][0] = new CellSolution(Optional.empty(), Optional.of(curValue));
                    } else {
                        res[0][0] = new CellSolution(Optional.of(curValue), Optional.empty());
                    }

                } else {
                    Optional<BigInteger> minNegative = Optional.empty();
                    Optional<BigInteger> maxPositive = Optional.empty();

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

        return finalResult.maxPositive.map(value -> value.mod(MODULO).intValue()).orElse(-1);
    }

    private static Optional<BigInteger> minNegative(
            Optional<BigInteger> minNegative, BigInteger curValue, CellSolution adjCell) {

        Optional<BigInteger> best = minNegative;

        if (adjCell.maxPositive.isPresent()) {
            BigInteger candidate1 = curValue.multiply(adjCell.maxPositive.get());

            if (candidate1.signum() <= 0
                    && (best.isEmpty() || candidate1.compareTo(best.get()) < 0)) {
                best = Optional.of(candidate1);
            }
        }

        if (adjCell.minNegative.isPresent()) {
            BigInteger candidate1 = curValue.multiply(adjCell.minNegative.get());

            if (candidate1.signum() <= 0
                    && (best.isEmpty() || candidate1.compareTo(best.get()) < 0)) {
                best = Optional.of(candidate1);
            }
        }

        return best;
    }

    private static Optional<BigInteger> maxPositive(
            Optional<BigInteger> maxPositive, BigInteger curValue, CellSolution adjCell) {

        Optional<BigInteger> best = maxPositive;

        if (adjCell.maxPositive.isPresent()) {
            BigInteger candidate1 = curValue.multiply(adjCell.maxPositive.get());

            if (candidate1.signum() >= 0
                    && (best.isEmpty() || candidate1.compareTo(best.get()) > 0)) {
                best = Optional.of(candidate1);
            }
        }

        if (adjCell.minNegative.isPresent()) {
            BigInteger candidate1 = curValue.multiply(adjCell.minNegative.get());

            if (candidate1.signum() >= 0
                    && (best.isEmpty() || candidate1.compareTo(best.get()) > 0)) {
                best = Optional.of(candidate1);
            }
        }

        return best;
    }

    record CellSolution(Optional<BigInteger> minNegative, Optional<BigInteger> maxPositive) {}
}
