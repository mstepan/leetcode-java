package com.github.mstepan.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * 2280. Minimum Lines to Represent a Line Chart
 *
 * <p>https://leetcode.com/problems/minimum-lines-to-represent-a-line-chart/description/
 */
public class MinimumLinesToRepresentALineChart {

    /**
     * time: O(N*lgN)
     *
     * <p>space: O(1)
     */
    public static int minimumLines(int[][] stockPrices) {
        Objects.requireNonNull(stockPrices, "stockPrices must not be null");

        if (stockPrices.length < 2) {
            return 0;
        }

        if (stockPrices.length == 2) {
            return 1;
        }

        Arrays.sort(stockPrices, Comparator.comparingInt(arr -> arr[0]));

        int linesCount = 1;
        for (int i = 2; i < stockPrices.length; i++) {
            if (!isCollinear(stockPrices[i - 2], stockPrices[i - 1], stockPrices[i])) {
                ++linesCount;
            }
        }

        return linesCount;
    }

    private static boolean isCollinear(int[] first, int[] second, int[] third) {
        long x1 = first[0];
        long y1 = first[1];

        long x2 = second[0];
        long y2 = second[1];

        long x3 = third[0];
        long y3 = third[1];

        return x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) == 0L;
    }
}
