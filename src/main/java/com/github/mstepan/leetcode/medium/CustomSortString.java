package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 791. Custom Sort String
 *
 * <p>https://leetcode.com/problems/custom-sort-string/description/
 */
public class CustomSortString {

    /**
     * time: O(N)
     *
     * <p>space: O(N)
     */
    public static String customSortString(String order, String str) {
        Objects.requireNonNull(order);
        Objects.requireNonNull(str);

        int[] freq = new int['z' - 'a' + 1];

        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            assert ch >= 'a' && ch <= 'z';
            freq[ch - 'a'] += 1;
        }

        StringBuilder res = new StringBuilder(str.length());

        for (int i = 0; i < order.length(); ++i) {
            char ch = order.charAt(i);
            assert ch >= 'a' && ch <= 'z';
            int idx = ch - 'a';

            if (freq[idx] > 0) {
                appendNChars(res, ch, freq[idx]);
                freq[idx] = 0;
            }
        }

        for (int i = 0; i < freq.length; ++i) {
            if (freq[i] > 0) {
                char ch = (char) (i + 'a');
                appendNChars(res, ch, freq[i]);
                freq[i] = 0;
            }
        }

        return res.toString();
    }

    private static void appendNChars(StringBuilder buf, char ch, int count) {
        assert count > 0;
        for (int i = 0; i < count; ++i) {
            buf.append(ch);
        }
    }
}
