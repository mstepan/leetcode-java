package com.github.mstepan.leetcode.hard;

import java.util.Objects;

/**
 * 1163. Last Substring in Lexicographical Order
 *
 * <p>https://leetcode.com/problems/last-substring-in-lexicographical-order/description/
 */
public class LastSubstringInLexicographicalOrder {

    /**
     * time: O(N)
     *
     * <p>space: O(1)
     */
    public static String lastSubstring(String str) {
        Objects.requireNonNull(str, "null 'str' passed as a parameter");

        final int strLength = str.length();

        if (strLength < 2) {
            return str;
        }

        int best = 0;
        int candidate = 1;
        int offset = 0;

        while (candidate + offset < strLength) {
            final char bestCh = str.charAt(best + offset);
            final char candidateCh = str.charAt(candidate + offset);

            if (bestCh == candidateCh) {
                ++offset;
            } else if (bestCh < candidateCh) {
                final int nextCandidate = Math.max(candidate + 1, best + offset + 1);
                best = candidate;
                candidate = nextCandidate;
                offset = 0;
            } else {
                candidate += offset + 1;
                offset = 0;
            }
        }

        return str.substring(best);
    }
}
