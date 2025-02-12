package com.github.mstepan.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 3027. Find the Number of Ways to Place People II
 *
 * <p>https://leetcode.com/problems/find-the-number-of-ways-to-place-people-ii/description/
 */
public class FindTheNumberOfWaysToPlacePeople2 {

    /**
     * N = points.length (max value = 1000)
     *
     * <p>time: O(N^2 + NlgN)
     *
     * <p>space: O(N)
     */
    public static int numberOfPairs(int[][] points) {

        List<Point> pointsList =
                new ArrayList<>(
                        Arrays.stream(points).map(data -> new Point(data[0], data[1])).toList());

        pointsList.sort(Point.X_ASC_Y_DESC);

        int cnt = 0;

        for (int i = 0; i < pointsList.size() - 1; i++) {
            Point left = pointsList.get(i);

            int maxY = Integer.MIN_VALUE;

            for (int j = i + 1; j < pointsList.size(); j++) {
                Point right = pointsList.get(j);

                if (left.y >= right.y && maxY < right.y) {
                    ++cnt;
                    maxY = right.y;
                }
            }
        }

        return cnt;
    }

    record Point(int x, int y) {
        private static final Comparator<Point> X_ASC_Y_DESC =
                Comparator.comparingInt(Point::x)
                        .thenComparing(Comparator.comparingInt(Point::y).reversed());
    }
}
