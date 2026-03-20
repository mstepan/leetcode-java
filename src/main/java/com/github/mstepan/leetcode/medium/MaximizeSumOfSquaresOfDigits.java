package com.github.mstepan.leetcode.medium;

/**
 * 3723. Maximize Sum of Squares of Digits
 *
 * <p>https://leetcode.com/problems/maximize-sum-of-squares-of-digits/description/
 */
public class MaximizeSumOfSquaresOfDigits {

    /**
     * K = digitsCount
     *
     * <p>time: O(K)
     *
     * <p>spaceL O(K)
     */
    public static String maxSumOfSquares(int digitsCount, int sum) {
        assert digitsCount >= 1;
        assert sum >= 1;

        final int[] digits = new int[digitsCount];
        int leftSum = sum;

        for (int i = 0; i < digitsCount && leftSum > 0; i++) {
            final int curDigit = Math.min(9, leftSum);
            digits[i] = curDigit;
            leftSum -= curDigit;
        }

        if (leftSum > 0) {
            return "";
        }

        StringBuilder res = new StringBuilder();

        for (int singleDigit : digits) {
            res.append(singleDigit);
        }

        return res.toString();
    }
}
