package com.github.mstepan.leetcode.medium;

import java.util.Arrays;
import java.util.Objects;

/**
 * 81. Search in Rotated Sorted Array II
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
 */
public final class SearchInRotatedSortedArray2 {

    /**
     * time: O(lgN), could be O(N) if all elements are the same
     *
     * <p>space: O(1)
     */
    public static boolean search(int[] nums, int target) {
        Objects.requireNonNull(nums);

        int smallestElemIdx = findSmallestElementIndex(nums);

        int targetIdx = Arrays.binarySearch(nums, 0, smallestElemIdx, target);

        if (targetIdx < 0) {
            targetIdx = Arrays.binarySearch(nums, smallestElemIdx, nums.length, target);
        }

        return targetIdx >= 0;
    }

    private static int findSmallestElementIndex(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == arr[hi]) {
                if (hasSameElements(arr, mid, hi)) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            } else if (arr[mid] > arr[hi]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    private static boolean hasSameElements(int[] arr, int from, int to) {
        for (int i = from + 1; i <= to; ++i) {
            if (arr[i] != arr[from]) {
                return false;
            }
        }

        return true;
    }
}
