package com.github.mstepan.leetcode.hard;

import java.util.Objects;

/**
 * 3399. Smallest Substring With Identical Characters II
 * https://leetcode.com/problems/smallest-substring-with-identical-characters-ii/description/
 */
public class SmallestSubstringWithIdenticalCharacters2 {

    /**
     * Binary search the answer.
     *
     * <p>time: O(N*lgN)
     *
     * <p>space: O(1)
     */
    public static int minLength(String str, int numOps) {
        Objects.requireNonNull(str);
        assert numOps >= 0;

        if (str.isEmpty()) {
            return 0;
        }

        if (numOps >= str.length() / 2) {
            return 1;
        }

        int from = 1;
        int to = str.length();
        int minFoundLength = to;

        while (from <= to) {
            int mid = from + (to - from) / 2;
            if (hasSolutionWithLength(str, mid, numOps)) {
                minFoundLength = mid;
                to = mid - 1;
            } else {
                from = mid + 1;
            }
        }

        return minFoundLength;
    }

    private static boolean hasSolutionWithLength(String str, int maxAllowedLength, int modCnt) {
        if (maxAllowedLength == 1) {
            // For the string with maxAllowedLength == 1 we can just check if alternating chars
            // string can be created
            // starting from '1' or '0' as initial character.
            return canBeAlternatedStringThatStartsWith('0', str, modCnt)
                    || canBeAlternatedStringThatStartsWith('1', str, modCnt);
        }

        char prev = str.charAt(0);
        int runLength = 1;
        int leftModCnt = modCnt;

        for (int i = 1; i < str.length(); ++i) {
            char cur = str.charAt(i);

            if (prev == cur) {
                ++runLength;

                if (runLength > maxAllowedLength) {
                    if (leftModCnt == 0) {
                        return false;
                    }
                    --leftModCnt;

                    if (i + 1 < str.length()) {
                        char next = str.charAt(i + 1);

                        if (cur == next) {
                            runLength = 1;
                            prev = invert(cur);
                        } else {
                            runLength = 1;
                        }
                    }
                }
            } else {
                runLength = 1;
                prev = cur;
            }
        }

        return true;
    }

    private static boolean canBeAlternatedStringThatStartsWith(
            char startCh, String str, int modCnt) {
        int leftModCnt = modCnt;

        if (str.charAt(0) != startCh) {
            if (modCnt == 0) {
                return false;
            }
            --leftModCnt;
        }

        char prev = startCh;

        for (int i = 1; i < str.length(); ++i) {
            char ch = str.charAt(i);

            if (prev == ch) {
                if (leftModCnt == 0) {
                    return false;
                }

                --leftModCnt;
                prev = invert(ch);
            } else {
                prev = ch;
            }
        }

        return true;
    }

    private static char invert(char ch) {
        return ch == '0' ? '1' : '0';
    }
}
