package com.github.mstepan.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        for (int i = 0; i < digits.length - 1; ++i) {
            int curDigit = digits[i];
            int maxDigitIdx = i;

            for (int j = i + 1; j < digits.length; ++j) {
                if (digits[j] > curDigit && digits[j] >= digits[maxDigitIdx]) {
                    maxDigitIdx = j;
                }
            }

            if (maxDigitIdx != i) {
                swap(digits, i, maxDigitIdx);
                break;
            }
        }

        return toNumber(digits);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] toDecimalDigits(int initialVal) {

        List<Integer> digits = new ArrayList<>(9);

        // 123 => [3, 2, 1]
        for (int val = initialVal; val > 0; val /= 10) {
            int digit = val % 10;
            digits.add(digit);
        }

        // [3, 2, 1] => [1, 2, 3]
        Collections.reverse(digits);

        int[] arr = new int[digits.size()];
        var it = digits.iterator();

        for (int i = 0; i < digits.size() && it.hasNext(); ++i) {
            arr[i] = it.next();
        }

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
}
