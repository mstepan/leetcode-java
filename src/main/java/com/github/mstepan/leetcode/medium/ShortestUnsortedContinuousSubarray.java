package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 581. Shortest Unsorted Continuous Subarray
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 */
public class ShortestUnsortedContinuousSubarray {

    /**
     * time: O(N)
     *
     * <p>space: O(1)
     */
    public static int findUnsortedSubarray(int[] nums) {
        Objects.requireNonNull(nums, "null 'nums' detected");

        int left = 1;

        for (; left < nums.length; ++left) {
            if (nums[left - 1] > nums[left]) {
                break;
            }
        }

        // fully sorted array, return 0
        if (left >= nums.length) {
            return 0;
        }

        int minIdx = findMinIdxToRight(nums, left);
        int firstBiggerIdx = findFirstBiggerIdx(nums, minIdx);

        int right = nums.length - 2;
        for (; right >= 0; --right) {
            if (nums[right] > nums[right + 1]) {
                break;
            }
        }

        int maxIdx = findMaxIdxToLeft(nums, right);
        int firstSmallerIdx = findFirstSmallerIdx(nums, maxIdx);

        return (firstSmallerIdx - firstBiggerIdx) + 1;
    }

    private static int findFirstSmallerIdx(int[] nums, int idx) {
        for (int i = nums.length - 1; i > idx; --i) {
            if (nums[i] < nums[idx]) {
                return i;
            }
        }

        return idx;
    }

    private static int findMaxIdxToLeft(int[] nums, int idx) {
        int maxIdx = idx;

        for (int i = idx - 1; i >= 0; --i) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        return maxIdx;
    }

    private static int findFirstBiggerIdx(int[] nums, int idx) {

        for (int i = 0; i < idx; ++i) {
            if (nums[i] > nums[idx]) {
                return i;
            }
        }

        return idx;
    }

    private static int findMinIdxToRight(int[] nums, int idx) {

        int minIdx = idx;

        for (int i = idx + 1; i < nums.length; ++i) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
        }

        return minIdx;
    }
}
