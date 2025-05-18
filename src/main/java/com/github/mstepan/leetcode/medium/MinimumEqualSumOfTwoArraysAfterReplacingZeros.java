package com.github.mstepan.leetcode.medium;

import java.util.Arrays;

/**
 * 2918. Minimum Equal Sum of Two Arrays After Replacing Zeros
 *
 * <p>https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/?difficulty=HARD
 */
public class MinimumEqualSumOfTwoArraysAfterReplacingZeros {

    /**
     * time: O(N)
     *
     * <p>space: O(1)
     */
    public static long minSum(int[] nums1, int[] nums2) {

        int zerosCnt1 = countZeros(nums1);
        int zerosCnt2 = countZeros(nums2);

        // calculate array sum + add '1' for all zeros as minimum possible value
        // use 'asLongStream' to do not have integer overflow
        long sum1 = Arrays.stream(nums1).asLongStream().sum() + zerosCnt1;
        long sum2 = Arrays.stream(nums2).asLongStream().sum() + zerosCnt2;

        if (sum1 < sum2) {
            if (zerosCnt1 == 0) {
                return -1;
            }
            return sum2;
        }

        if (sum2 < sum1) {
            if (zerosCnt2 == 0) {
                return -1;
            }
            return sum1;
        }

        return sum1;
    }

    private static int countZeros(int[] nums1) {
        int cnt = 0;

        for (int val : nums1) {
            cnt += (val == 0) ? 1 : 0;
        }

        return cnt;
    }
}
