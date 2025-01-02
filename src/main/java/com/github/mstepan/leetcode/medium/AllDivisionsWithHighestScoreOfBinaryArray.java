package com.github.mstepan.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 2155. All Divisions With the Highest Score of a Binary Array
 *
 * <p>https://leetcode.com/problems/all-divisions-with-the-highest-score-of-a-binary-array/description/
 */
public class AllDivisionsWithHighestScoreOfBinaryArray {

    /**
     * time: O(N)
     *
     * <p>space: O(1)
     */
    public static List<Integer> maxScoreIndices(int[] arr) {
        checkIsBinaryArray(arr);

        final int zerosCount = countValues(arr, 0);
        final int onesCount = countValues(arr, 1);

        int bestScore = Math.max(zerosCount, onesCount);
        List<Integer> indexes = new ArrayList<>();

        if (bestScore == onesCount) {
            indexes.add(0);
        }

        int leftZeros = 0;
        int rightOnes = onesCount;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == 0) {
                ++leftZeros;

            } else {
                --rightOnes;
            }

            int curScore = leftZeros + rightOnes;

            if (curScore == bestScore) {
                indexes.add(i);
            } else if (curScore > bestScore) {
                bestScore = curScore;
                indexes.clear();
                indexes.add(i);
            }
        }

        if (bestScore == zerosCount) {
            indexes.add(arr.length);
        }

        return indexes;
    }

    private static int countValues(int[] arr, int expectedValue) {

        int cnt = 0;

        for (int val : arr) {
            cnt = cnt + (val == expectedValue ? 1 : 0);
        }

        return cnt;
    }

    private static void checkIsBinaryArray(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("null 'arr' detected");
        }

        for (int val : arr) {
            if (!(val == 0 || val == 1)) {
                throw new IllegalArgumentException("Not a binary array");
            }
        }
    }
}
