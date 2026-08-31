package com.github.mstepan.leetcode.medium;

/**
 * 670. Maximum Swap
 *
 * <p>https://leetcode.com/problems/maximum-swap/description/
 */
public class MaximumSwap {

    /**
     * time: O(1)
     *
     * <p>space: O(1)
     */
    public static int maximumSwap(int val) {
        if (val < 0 || val > 100_000_000) {
            throw new IllegalArgumentException("val = " + val + ", should be in range [0...10^8]");
        }

        if (val >= 0 && val < 10) {
            return val;
        }

        int[] digits = toDecimalDigits(val);

        int maxDigitIdx = digits.length - 1;
        int[] swapPos = new int[] {digits.length - 1, digits.length - 1};

        // single pass solution
        for (int i = digits.length - 2; i >= 0; --i) {
            int curDigit = digits[i];

            if (curDigit > digits[maxDigitIdx]) {
                maxDigitIdx = i;
            } else if (curDigit < digits[maxDigitIdx]) {
                swapPos = new int[] {i, maxDigitIdx};
            }
        }

        swap(digits, swapPos[0], swapPos[1]);

        return toNumber(digits);
    }

    private static int[] toDecimalDigits(int initialVal) {

        int digitsCnt = countDigits(initialVal);

        int[] arr = new int[digitsCnt];

        for (int i = 0, val = initialVal; i < arr.length && val > 0; ++i, val /= 10) {
            arr[i] = val % 10;
        }

        reverseArray(arr);

        return arr;
    }

    //  [1, 2, 3] => 123
    private static int toNumber(int[] digits) {
        int res = 0;

        for (int val : digits) {
            res = res * 10 + val;
        }

        return res;
    }

    private static int countDigits(int initialVal) {
        int digitsCnt = 0;
        for (int val = initialVal; val > 0; val /= 10) {
            ++digitsCnt;
        }

        return digitsCnt;
    }

    private static void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            swap(arr, left, right);
            ++left;
            --right;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
