package com.github.mstepan.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 1814. Count Nice Pairs in an Array
 *
 * <p>https://leetcode.com/problems/count-nice-pairs-in-an-array/description/
 */
public class CountNicePairsInArray {

    private static final long MOD = 1_000_000_007L;

    /** time: O(N) space: O(N) */
    public static int countNicePairs(int[] nums) {
        Objects.requireNonNull(nums);
        Map<Long, Integer> diff = new HashMap<>();

        for (int val : nums) {
            long reverseVal = reverse(val);
            diff.compute(val - reverseVal, (key, count) -> (count == null) ? 1 : count + 1);
        }

        long pairsCount = 0L;

        for (long cnt : diff.values()) {
            long temp = ((cnt - 1) * cnt / 2) % MOD;
            pairsCount = (pairsCount + temp) % MOD;
        }

        return Math.toIntExact(pairsCount);
    }

    private static long reverse(int val) {
        assert val >= 0;

        int left = val;
        long revValue = 0L;

        while (left != 0) {
            int lastDigit = left % 10;

            revValue = revValue * 10 + lastDigit;
            left /= 10;
        }

        return revValue;
    }
}
