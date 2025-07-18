package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 91. Decode Ways
 *
 * <p>https://leetcode.com/problems/decode-ways/description/
 */
public class DecodeWays {

    public static int numDecodings(String str) {
        Objects.requireNonNull(str);

        char[] arr = str.toCharArray();
        int[] sol = new int[arr.length];

        for (int i = 0; i < sol.length; ++i) {
            int decodeWaysCnt = 0;
            int digit = arr[i] - '0';

            if (digit != 0) {
                decodeWaysCnt += (i - 1 >= 0) ? sol[i - 1] : 1;
            }

            if (i != 0) {
                int prevDigit = arr[i - 1] - '0';
                if (prevDigit != 0) {
                    int num = prevDigit * 10 + digit;

                    if (num > 0 && num < 27) {
                        decodeWaysCnt += (i - 2 >= 0) ? sol[i - 2] : 1;
                    }
                }
            }

            sol[i] = decodeWaysCnt;
        }

        return sol[sol.length - 1];
    }
}
