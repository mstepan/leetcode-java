package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 978. Longest Turbulent Subarray
 *
 * <p>https://leetcode.com/problems/longest-turbulent-subarray/
 */
public class LongestTurbulentSubarray {

    /**
     * time: O(N)
     *
     * <p>space: O(1)
     */
    public static int maxTurbulenceSize(int[] arr) {
        Objects.requireNonNull(arr);
        if (arr.length < 2) {
            return arr.length;
        }

        int maxSoFar = 1;
        int cur = 1;

        CmpRes lastCmp = null;

        for (int i = 1; i < arr.length; ++i) {
            CmpRes curCmp = compare(arr, i - 1, i);

            if (curCmp != CmpRes.EQUAL) {
                if (lastCmp == null || isOpposite(curCmp, lastCmp)) {
                    cur += 1;
                } else {
                    cur = 2;
                }
            } else {
                cur = 1;
            }

            maxSoFar = Math.max(maxSoFar, cur);

            lastCmp = curCmp;
        }

        return maxSoFar;
    }

    private static boolean isOpposite(CmpRes left, CmpRes right) {
        return (left == CmpRes.LESS && right == CmpRes.GREATER)
                || (left == CmpRes.GREATER && right == CmpRes.LESS);
    }

    private static CmpRes compare(int[] arr, int leftIdx, int rightIdx) {

        if (arr[rightIdx] > arr[leftIdx]) {
            return CmpRes.GREATER;
        }

        if (arr[rightIdx] < arr[leftIdx]) {
            return CmpRes.LESS;
        }

        return CmpRes.EQUAL;
    }

    enum CmpRes {
        LESS,
        GREATER,
        EQUAL
    }
}
