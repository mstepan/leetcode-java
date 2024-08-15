package com.github.mstepan.leetcode.medium;

import java.util.Arrays;
import java.util.Objects;

/** 2708. Maximum Strength of a Group https://leetcode.com/problems/maximum-strength-of-a-group/ */
public class MaximumStrengthOfGroup {

    /** time: O(N) space: O(1) */
    public long maxStrength(int[] nums) {
        Objects.requireNonNull(nums);

        if (nums.length == 0) {
            return 0;
        }

        if (nums.length < 2) {
            return nums[0];
        }

        Arrays.sort(nums);

        long positiveProduct = 1L;
        for (int i = nums.length - 1; i >= 0 && nums[i] > 0; --i) {
            positiveProduct *= nums[i];
        }

        long negativeMaxProduct = nums[0] < 0 ? nums[0] : 1L;
        long curNegativeProduct = 1L;

        for (int i = 0; i < nums.length && nums[i] <= 0; ++i) {
            curNegativeProduct *= nums[i];
            negativeMaxProduct = Math.max(negativeMaxProduct, curNegativeProduct);
        }

        if (hasPositive(nums)) {
            // has at least 1 positive value
            return Math.max(positiveProduct, positiveProduct * negativeMaxProduct);
        } else if (hasNegative(nums)) {
            // all values negative or 0
            return negativeMaxProduct;
        } else {
            // all values are 0
            return 0;
        }
    }

    private boolean hasNegative(int[] nums) {
        for (int val : nums) {
            if (val < 0) {
                return true;
            }
        }

        return false;
    }

    private boolean hasPositive(int[] nums) {
        for (int val : nums) {
            if (val > 0) {
                return true;
            }
        }

        return false;
    }
}
