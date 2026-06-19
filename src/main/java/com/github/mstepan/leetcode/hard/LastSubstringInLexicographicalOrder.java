package com.github.mstepan.leetcode.hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * 1163. Last Substring in Lexicographical Order
 *
 * <p>https://leetcode.com/problems/last-substring-in-lexicographical-order/description/
 */
public class LastSubstringInLexicographicalOrder {

    /**
     * time: O(N*lgN*K)
     *
     * <p>space: O(N)
     */
    public static String lastSubstring(String str) {
        Objects.requireNonNull(str, "null 'str' passed as a parameter");

        if (str.length() < 2) {
            return str;
        }

        Suffix[] allSuffixes = new Suffix[str.length()];

        for (int i = 0; i < str.length(); ++i) {
            allSuffixes[i] = new Suffix(i, str);
        }

        Arrays.sort(allSuffixes, Suffix.SUFFIX_STR_ASC);

        return allSuffixes[allSuffixes.length - 1].suffixStr();
    }

    record Suffix(int from, String baseStr) {

        @SuppressWarnings("StringEquality")
        private static final Comparator<Suffix> SUFFIX_STR_ASC =
                (first, second) -> {
                    assert first.baseStr() == second.baseStr();

                    final String str = first.baseStr();
                    int firstIdx = first.from();
                    int secondIdx = second.from();

                    while (firstIdx < str.length() && secondIdx < str.length()) {

                        char firstCh = str.charAt(firstIdx);
                        char secondCh = str.charAt(secondIdx);

                        int cmp = Character.compare(firstCh, secondCh);

                        if (cmp != 0) {
                            return cmp;
                        }

                        ++firstIdx;
                        ++secondIdx;
                    }

                    if (firstIdx < str.length()) {
                        return 1;
                    } else if (secondIdx < str.length()) {
                        return -1;
                    }

                    return 0;
                };

        public String suffixStr() {
            return baseStr.substring(from);
        }
    }
}
