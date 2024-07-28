package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 3192. Minimum Operations to Make Binary Array Elements Equal to One II
 *
 * <p>https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-ii/description/
 */
public class MinimumOperationsToMakeBinaryArrayElementsEqualToOne2 {

    /**
     * time: O(N)
     *
     * <p>space: O(1)
     */
    public int minOperations(int[] nums) {
        Objects.requireNonNull(nums, "null 'nums' detected");

        if (nums.length == 0) {
            return 0;
        }

        int lastDigit = nums[nums.length - 1];
        int idx = nums.length - 1;
        int swapsCnt = 0;

        while (idx != 0) {
            idx = extend(nums, idx, lastDigit);
            if (idx == 0) {
                break;
            }
            lastDigit = nums[idx - 1];
            idx -= 1;
            swapsCnt += 1;
        }

        if (lastDigit == 0) {
            swapsCnt += 1;
        }

        return swapsCnt;
    }

    private int extend(int[] nums, int idx, int lastDigit) {

        int newIdx = idx;

        while (newIdx >= 0 && nums[newIdx] == lastDigit) {
            newIdx -= 1;
        }

        return newIdx + 1;
    }
}
