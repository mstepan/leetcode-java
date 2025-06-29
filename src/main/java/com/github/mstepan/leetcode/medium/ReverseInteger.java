package com.github.mstepan.leetcode.medium;

/**
 * 7. Reverse Integer
 *
 * <p>https://leetcode.com/problems/reverse-integer/description/
 */
public class ReverseInteger {

    private static final int MAX_DIV_10 = Integer.MAX_VALUE / 10;

    /**
     * Reverse digits of an integer.
     *
     * @param value - integer to reverse
     * @return reversed integer or 0 if the reversed integer overflows.
     */
    public static int reverse(int value) {
        if (value == Integer.MAX_VALUE || value == Integer.MIN_VALUE || value == 0) {
            return 0;
        }

        int left = Math.abs(value);

        int res = 0;

        while (left > 0) {
            int digit = left % 10;
            left /= 10;

            // overflow will occur if multiplied by 10
            if (res > MAX_DIV_10) {
                return 0;
            }

            res = res * 10 + digit;

            // overflow occurred if the result is negative
            if (res < 0) {
                return 0;
            }
        }

        // Integer.MIN_VALUE absolute value is bigger than Integer.MAX_VALUE, so it's safe to
        // convert positive value to negative
        return value < 0 ? -res : res;
    }
}
