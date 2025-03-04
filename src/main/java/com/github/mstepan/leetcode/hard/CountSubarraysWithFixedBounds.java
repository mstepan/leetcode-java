package com.github.mstepan.leetcode.hard;

import java.util.Objects;

/**
 * 2444. Count Subarrays With Fixed Bounds
 *
 * <p>https://leetcode.com/problems/count-subarrays-with-fixed-bounds/description/
 */
public class CountSubarraysWithFixedBounds {

    /**
     * time: O(N)
     *
     * <p>space: O(1)
     */
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        Objects.requireNonNull(nums);

        long cnt = 0L;

        int lastMinIdx = -1;
        int lastMaxIdx = -1;
        int left = 0;

        for (int i = 0; i < nums.length; ++i) {
            final int val = nums[i];

            if (val < minK || val > maxK) {
                left = i + 1;
                lastMinIdx = -1;
                lastMaxIdx = -1;
                continue;
            }

            if (val == minK) {
                lastMinIdx = i;
            }
            if (val == maxK) {
                lastMaxIdx = i;
            }

            if (lastMinIdx >= 0 && lastMaxIdx >= 0) {
                cnt += (Math.min(lastMinIdx, lastMaxIdx) - left + 1);
            }
        }

        return cnt;
    }
}
