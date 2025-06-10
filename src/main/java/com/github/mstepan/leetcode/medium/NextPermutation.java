package com.github.mstepan.leetcode.medium;

/**
 * 31. Next Permutation
 *
 * <p>https://leetcode.com/problems/next-permutation/
 */
public class NextPermutation {

    /** time: O(N) space: O(1), in-place */
    public static void nextPermutation(int[] arr) {
        for (int i = arr.length - 1; i > 0; --i) {

            // found place for next permutation
            if (arr[i - 1] < arr[i]) {

                int swapIdx = i - 1;

                int biggerValueIdx = findBiggerFromTheEnd(arr, arr[swapIdx]);

                assert biggerValueIdx >= 0 : "can't find bigger value from end of array";
                swap(arr, swapIdx, biggerValueIdx);
                reverseSubarray(arr, i, arr.length - 1);

                return;
            }
        }

        // Can't find next permutation, b/c it doesn't exist at all, so switching to first one.
        reverseSubarray(arr, 0, arr.length - 1);
    }

    private static void reverseSubarray(int[] arr, int from, int to) {
        assert arr != null;
        assert from >= 0 && from < arr.length;
        assert to >= 0 && to < arr.length;
        assert from <= to;

        int left = from;
        int right = to;

        while (left < right) {
            swap(arr, left, right);
            ++left;
            --right;
        }
    }

    private static void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    private static int findBiggerFromTheEnd(int[] arr, int val) {

        for (int i = arr.length - 1; i >= 0; --i) {
            if (arr[i] > val) {
                return i;
            }
        }

        return -1;
    }
}
