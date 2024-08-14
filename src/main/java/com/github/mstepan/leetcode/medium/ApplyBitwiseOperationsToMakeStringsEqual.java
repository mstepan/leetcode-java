package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 2546. Apply Bitwise Operations to Make Strings Equal
 *
 * <p>https://leetcode.com/problems/apply-bitwise-operations-to-make-strings-equal/description/
 */
public class ApplyBitwiseOperationsToMakeStringsEqual {

    /** time: O(N) Space: O(1) */
    public boolean makeStringsEqual(String cur, String target) {
        Objects.requireNonNull(cur);
        Objects.requireNonNull(target);

        if (cur.length() != target.length()) {
            throw new IllegalArgumentException(
                    "'cur' string length should be equals to 'target' string length, cur.length = "
                            + cur.length()
                            + ", target.length = "
                            + target.length());
        }

        int onesCnt = countOnes(cur);

        char[] arr = cur.toCharArray();

        for (int i = 0; i < arr.length; ++i) {
            char left = checkBinaryDigitChar(arr[i]);
            char right = checkBinaryDigitChar(target.charAt(i));

            if (left == '1' && right == '0') {
                if (onesCnt < 2) {
                    if (i + 1 < arr.length) {
                        arr[i + 1] = '1';
                        ++onesCnt;
                    } else {
                        return false;
                    }
                }
                --onesCnt;
            } else if (left == '0' && right == '1') {
                if (onesCnt == 0) {
                    return false;
                }
                ++onesCnt;
            }
        }

        return true;
    }

    private char checkBinaryDigitChar(char ch) {
        if (ch == '0' || ch == '1') {
            return ch;
        }
        throw new IllegalArgumentException("Not a binary digit detected '" + ch + "'");
    }

    private int countOnes(String str) {
        assert str != null;

        int onesCnt = 0;

        for (int i = 0; i < str.length(); ++i) {
            onesCnt = onesCnt + (str.charAt(i) == '1' ? 1 : 0);
        }

        return onesCnt;
    }
}
