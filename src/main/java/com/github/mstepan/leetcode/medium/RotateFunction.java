package com.github.mstepan.leetcode.medium;

import java.util.Arrays;
import java.util.Objects;

/**
 * 396. Rotate Function
 *
 * <p>https://leetcode.com/problems/rotate-function/description/
 */
public class RotateFunction {

    /**
     * time: O(N)
     *
     * <p>space: O(1)
     */
    public static int maxRotateFunction(int[] nums) {
        Objects.requireNonNull(nums);

        int prevValue = calculateF(nums);
        int totalSum = Arrays.stream(nums).sum();

        int maxValue = prevValue;

        for (int rotationPoint = nums.length - 1; rotationPoint > 0; --rotationPoint) {
            int curValue =
                    prevValue
                            - (nums[rotationPoint] * (nums.length - 1))
                            + (totalSum - nums[rotationPoint]);

            maxValue = Math.max(maxValue, curValue);

            prevValue = curValue;
        }

        return maxValue;
    }

    private static int calculateF(int[] nums) {

        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            res += (i * nums[i]);
        }

        return res;
    }
}
