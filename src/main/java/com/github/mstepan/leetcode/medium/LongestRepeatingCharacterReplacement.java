package com.github.mstepan.leetcode.medium;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 424. Longest Repeating Character Replacement
 *
 * <p>https://leetcode.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharacterReplacement {

    /**
     * Time: O(N*26) ~ O(N)
     *
     * <p>Space: O(26) ~ O(1)
     */
    public static int characterReplacement(String str, int k) {
        Objects.requireNonNull(str);

        final char[] arr = str.toCharArray();

        Set<Character> uniqueChars = unique(arr);

        int maxSub = 0;

        for (char base : uniqueChars) {
            maxSub = Math.max(maxSub, findLongest(base, arr, k));
        }

        return maxSub;
    }

    private static int findLongest(char baseCh, char[] arr, int k) {

        int maxResult = 0;

        int baseCnt = 0;
        int otherCnt = 0;

        int left = 0;

        for (char c : arr) {
            if (c == baseCh) {
                ++baseCnt;
            } else {
                ++otherCnt;
            }

            while (otherCnt > k) {
                if (arr[left] == baseCh) {
                    --baseCnt;
                } else {
                    --otherCnt;
                }

                ++left;
            }

            maxResult = Math.max(maxResult, baseCnt + otherCnt);
        }

        return maxResult;
    }

    private static Set<Character> unique(char[] arr) {

        Set<Character> result = new HashSet<>();
        for (char c : arr) {
            result.add(c);
        }

        return result;
    }
}
