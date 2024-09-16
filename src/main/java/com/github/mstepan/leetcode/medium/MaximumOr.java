package com.github.mstepan.leetcode.medium;

/**
 * 2680. Maximum OR
 *
 * <p>https://leetcode.com/problems/maximum-or/
 */
public class MaximumOr {

    /**
     * N = nums.length
     *
     * <p>time: O(N)
     *
     * <p>spaceL O(N)
     */
    public static long maximumOr(int[] nums, int k) {
        assert nums != null;
        assert k >= 0;

        long[] arr = new long[nums.length];

        for (int i = 0; i < nums.length; ++i) {
            arr[i] = nums[i];
        }

        long bestSoFar = 0L;
        long[] prefix = prefixOr(arr);
        long[] suffix = suffixOr(arr);

        for (int i = 0; i < arr.length; ++i) {
            long leftOr = (i > 0) ? prefix[i - 1] : 0L;
            long rightOr = (i < arr.length - 1) ? suffix[i + 1] : 0L;

            long cur = (arr[i] << k) | leftOr | rightOr;
            bestSoFar = Math.max(bestSoFar, cur);
        }

        return bestSoFar;
    }

    private static long[] suffixOr(long[] arr) {
        assert arr != null;

        long[] res = new long[arr.length];
        res[res.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; --i) {
            res[i] = arr[i] | res[i + 1];
        }

        return res;
    }

    private static long[] prefixOr(long[] arr) {
        assert arr != null;

        long[] res = new long[arr.length];
        res[0] = arr[0];

        for (int i = 1; i < arr.length; ++i) {
            res[i] = res[i - 1] | arr[i];
        }

        return res;
    }
}
