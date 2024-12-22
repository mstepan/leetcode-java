package com.github.mstepan.leetcode.medium;

import java.util.Arrays;

/**
 * 949. Largest Time for Given Digits
 *
 * <p>https://leetcode.com/problems/largest-time-for-given-digits/description/
 */
public class LargestTimeForGivenDigits {

    /**
     * time: O(4!) ~ O(1)
     *
     * <p>space: O(1)
     */
    public static String largestTimeFromDigits(int[] arr) {
        checkValidFourDigitsArray(arr);

        Arrays.sort(arr);
        Time biggest = null;

        for (int[] curDigits = arr; curDigits != null; curDigits = nextPermutation(curDigits)) {
            Time curTime = Time.fromDigits(curDigits);

            if (curTime.isValid() && (biggest == null || curTime.compareTo(biggest) > 0)) {
                biggest = curTime;
            }
        }

        return biggest == null ? "" : biggest.formatToString();
    }

    private static void checkValidFourDigitsArray(int[] digits) {
        if (digits == null) {
            throw new IllegalArgumentException("null 'digits' array detected");
        }

        if (digits.length != 4) {
            throw new IllegalArgumentException(
                    "Incorrect digits array length, expected 4 but found " + digits.length);
        }

        for (int singleDigit : digits) {
            if (singleDigit < 0 || singleDigit > 9) {
                throw new IllegalArgumentException(
                        "'digits' array contains invalid decimal digit: " + singleDigit);
            }
        }
    }

    record Time(int hours, int minutes) implements Comparable<Time> {
        public static Time fromDigits(int[] digits) {
            assert digits != null && digits.length == 4;

            int hours = digits[0] * 10 + digits[1];
            int minutes = digits[2] * 10 + digits[3];
            return new Time(hours, minutes);
        }

        public boolean isValid() {
            return (hours >= 0 && hours <= 23) && (minutes >= 0 && minutes <= 59);
        }

        @Override
        public int compareTo(Time other) {

            int cmp = Integer.compare(hours, other.hours);

            if (cmp != 0) {
                return cmp;
            }

            return Integer.compare(minutes, other.minutes);
        }

        public String formatToString() {
            return "%02d:%02d".formatted(hours, minutes);
        }
    }

    /**
     * Generates next lexicographical permutation of an array in-place. Returns reference to
     * in-place permuted array, just to simplify for loop iteration.
     */
    static int[] nextPermutation(int[] digits) {
        int idx = digits.length - 2;

        while (idx >= 0) {
            if (digits[idx] < digits[idx + 1]) {
                break;
            }

            --idx;
        }

        if (idx == -1) {
            return null;
        }

        for (int i = digits.length - 1; i > idx; --i) {
            if (digits[i] > digits[idx]) {
                swap(digits, idx, i);
                reverse(digits, idx + 1, digits.length - 1);

                return digits;
            }
        }

        throw new IllegalStateException("Incorrect state for nextPermutation detected");
    }

    private static void reverse(int[] digits, int from, int to) {
        assert from >= 0 && from < digits.length;
        assert to >= 0 && to < digits.length;

        for (int left = from, right = to; left < right; ++left, --right) {
            swap(digits, left, right);
        }
    }

    private static void swap(int[] digits, int from, int to) {
        assert from >= 0 && from < digits.length;
        assert to >= 0 && to < digits.length;

        int temp = digits[from];
        digits[from] = digits[to];
        digits[to] = temp;
    }
}
