package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 2081. Sum of k-Mirror Numbers
 *
 * <p>https://leetcode.com/problems/sum-of-k-mirror-numbers/description/
 */
public class SumOfKMirrorNumbers {

    public static long kMirror(int k, int n) {
        assert k >= 2 && k <= 9;
        assert n >= 1 && n <= 30;

        Queue<String> numbers = new ArrayDeque<>();

        for (int digit = 1; digit < k; ++digit) {
            numbers.add(String.valueOf(digit));
        }

        int leftToFind = n;
        long sum = 0L;

        while (!numbers.isEmpty() && leftToFind > 0) {
            String singleNumber = numbers.poll();

            long decimalNumber = toDecimal(singleNumber, k);
            if (isPalindrome(decimalNumber)) {
                sum += decimalNumber;
                --leftToFind;
            }

            Collection<String> nextNumbers = nextNumbers(singleNumber, k);

            numbers.addAll(nextNumbers);
        }

        assert !numbers.isEmpty();
        assert leftToFind == 0;

        return sum;
    }

    private static Collection<String> nextNumbers(String number, int base) {

        int midIndex = number.length() / 2;

        // even length
        if ((number.length() & 1) == 0) {

            String left = number.substring(0, midIndex);
            String right = number.substring(midIndex);

            List<String> nextNumbers = new ArrayList<>(base);

            for (int digit = 0; digit < base; ++digit) {
                String nextValue = left + digit + right;
                nextNumbers.add(nextValue);
            }

            return nextNumbers;

        } else {
            // odd length

            char midCh = number.charAt(midIndex);

            String nextValue = number.substring(0, midIndex) + midCh + number.substring(midIndex);

            return List.of(nextValue);
        }
    }

    private static boolean isPalindrome(long number) {
        String numberStr = String.valueOf(number);

        for (int left = 0, right = numberStr.length() - 1; left < right; ++left, --right) {
            if (numberStr.charAt(left) != numberStr.charAt(right)) {
                return false;
            }
        }
        return true;
    }

    private static long toDecimal(String number, int base) {
        long decimal = 0L;

        for (int i = 0; i < number.length(); ++i) {
            decimal = decimal * base + (number.charAt(i) - '0');
        }

        return decimal;
    }
}
