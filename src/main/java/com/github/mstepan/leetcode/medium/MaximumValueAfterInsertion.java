package com.github.mstepan.leetcode.medium;

/**
 * 1881. Maximum Value after Insertion
 *
 * <p>https://leetcode.com/problems/maximum-value-after-insertion/
 */
public class MaximumValueAfterInsertion {

    /** time: O(N) space: O(N) */
    public static String maxValue(String base, int digit) {
        checkIsValidDigitString(base);
        checkIsDigit(digit);

        char digitAsCh = Character.forDigit(digit, 10);
        StringBuilder res = new StringBuilder(base.length() + 1);

        if (base.charAt(0) == '-') {
            // negative value
            int idx = 1;
            res.append('-');

            while (idx < base.length() && base.charAt(idx) <= digitAsCh) {
                res.append(base.charAt(idx));
                ++idx;
            }

            res.append(digitAsCh);
            res.append(base, idx, base.length());
        } else {
            // positive value
            int idx = 0;

            while (idx < base.length() && base.charAt(idx) >= digitAsCh) {
                res.append(base.charAt(idx));
                ++idx;
            }
            res.append(digitAsCh);
            res.append(base, idx, base.length());
        }

        return res.toString();
    }

    private static void checkIsDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Not a decimal digit: " + digit);
        }
    }

    private static void checkIsValidDigitString(String base) {
        if (base == null || base.isEmpty()) {
            throw new IllegalArgumentException(
                    "Null or empty 'base' string detected: '" + base + "'");
        }
        int idx = 0;

        if (base.charAt(0) == '-') {
            ++idx;
        }

        for (; idx < base.length(); ++idx) {
            if (!Character.isDigit(base.charAt(idx))) {
                throw new IllegalArgumentException(
                        "Not a digit at index " + idx + " for string '" + base + "'");
            }
        }
    }
}
