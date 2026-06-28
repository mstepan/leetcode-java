package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 3030. Find the Grid of Region Average
 *
 * <p>https://leetcode.com/problems/find-the-grid-of-region-average/
 */
public class FindTheGridOfRegionAverage {

    public static int[][] resultGrid(int[][] image, int threshold) {
        Objects.requireNonNull(image);
        if (threshold < 0) {
            throw new IllegalArgumentException("Negative threshold detected: " + threshold);
        }

        for (int[] singleRow : image) {
            Objects.requireNonNull(singleRow);
        }

        final int rows = image.length;
        final int cols = image[0].length;

        final RegionStat[][] stats = new RegionStat[rows][cols];

        for (int row = 0; row <= rows - 3; ++row) {
            for (int col = 0; col <= cols - 3; ++col) {
                ImageRegion imageRegion = new ImageRegion(image, row, col, row + 2, col + 2);

                if (imageRegion.isWithinThreshold(threshold)) {

                    for (int regioRow = row; regioRow <= row + 2; ++regioRow) {
                        for (int regionCol = col; regionCol <= col + 2; ++regionCol) {

                            if (stats[regioRow][regionCol] == null) {
                                stats[regioRow][regionCol] = new RegionStat();
                            }

                            stats[regioRow][regionCol].update(imageRegion.avg(), 1);
                        }
                    }
                }
            }
        }

        return toAvgGridResult(image, stats);
    }

    private record ImageRegion(int[][] image, int startRow, int startCol, int endRow, int endCol) {

        private static final int REGION_SIZE = 9;

        public int avg() {
            int totalSum = 0;

            for (int row = startRow; row <= endRow; ++row) {
                for (int col = startCol; col <= endCol; ++col) {
                    totalSum += image[row][col];
                }
            }

            // avg value rounded DOWN to int value
            return totalSum / REGION_SIZE;
        }

        public boolean isWithinThreshold(int threshold) {

            // check first row
            boolean firstRow =
                    checkCellsWithinThreshold(threshold, startRow, startCol, startRow, startCol + 1)
                            && checkCellsWithinThreshold(
                                    threshold, startRow, startCol + 1, startRow, startCol + 2);

            // check first column
            boolean firstCol =
                    checkCellsWithinThreshold(threshold, startRow, startCol, startRow + 1, startCol)
                            && checkCellsWithinThreshold(
                                    threshold, startRow + 1, startCol, startRow + 2, startCol);

            if (!(firstRow && firstCol)) {
                return false;
            }

            // check middle values
            for (int row = startRow + 1; row <= endRow; ++row) {
                for (int col = startCol + 1; col <= endCol; ++col) {

                    int cur = image[row][col];

                    int left = image[row][col - 1];
                    int up = image[row - 1][col];

                    boolean curDiagonalRes =
                            checkCellsWithinThreshold(threshold, cur, left)
                                    && checkCellsWithinThreshold(threshold, cur, up);

                    if (!curDiagonalRes) {
                        return false;
                    }
                }
            }

            return true;
        }

        private boolean checkCellsWithinThreshold(int threshold, int cell1Value, int cell2Value) {
            return Math.abs(cell1Value - cell2Value) <= threshold;
        }

        private boolean checkCellsWithinThreshold(
                int threshold, int row1, int col1, int row2, int col2) {
            int cur = image[row1][col1];
            int next = image[row2][col2];
            return Math.abs(cur - next) <= threshold;
        }

        @Override
        public String toString() {
            return String.format("start: %d,%d, end: %d,%d", startRow, startCol, endRow, endCol);
        }
    }

    private static final class RegionStat {
        int sum;
        int count;

        public void update(int avgRegionSum, int deltaCount) {
            sum += avgRegionSum;
            count += deltaCount;
        }

        public int avgRegionsValue() {
            return sum / count;
        }
    }

    private static int[][] toAvgGridResult(int[][] originalImage, RegionStat[][] stats) {
        assert originalImage != null;
        assert originalImage[0] != null;

        final int rows = originalImage.length;
        final int cols = originalImage[0].length;

        final int[][] result = new int[rows][cols];

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                RegionStat curStat = stats[row][col];

                if (curStat == null) {
                    result[row][col] = originalImage[row][col];
                } else {
                    result[row][col] = curStat.avgRegionsValue();
                }
            }
        }

        return result;
    }
}
