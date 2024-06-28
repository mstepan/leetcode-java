package com.github.mstepan.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 2370. Longest Ideal Subsequence
 *
 * <p>https://leetcode.com/problems/longest-ideal-subsequence/description/
 */
public class LongestIdealSubsequence {

    private static final char START_CH = 'a';
    private static final char END_CH = 'z';

    private static final int ALPHABET_SIZE = END_CH - START_CH + 1;

    /**
     * time: O(N*K) ~ 2.5M
     *
     * <p>space: O(26) ~ O(1)
     */
    public int longestIdealString(String str, int k) {
        Objects.requireNonNull(str);
        if (k > 25) {
            throw new IllegalArgumentException(
                    "'k' offset is too big should be in range [0..25] but found: " + k);
        }

        if (str.length() < 2) {
            return str.length();
        }

        int[] longestSoFar = new int[ALPHABET_SIZE];

        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);

            List<Character> neighbourChars = findNeighbour(ch, k);
            int maxCur = 1;

            for (char candidateCh : neighbourChars) {
                maxCur = Math.max(maxCur, longestSoFar[charIndex(candidateCh)] + 1);
            }

            longestSoFar[charIndex(ch)] = Math.max(maxCur, longestSoFar[charIndex(ch)]);
        }

        return Arrays.stream(longestSoFar).max().getAsInt();
    }

    private static int charIndex(char ch) {
        assert ch >= START_CH && ch <= END_CH;
        return ch - START_CH;
    }

    private List<Character> findNeighbour(char baseCh, int distance) {

        List<Character> neighbours = new ArrayList<>();

        neighbours.add(baseCh);

        for (int i = 0, biggerCh = baseCh + 1;
                i < distance && biggerCh <= END_CH;
                ++i, ++biggerCh) {
            neighbours.add((char) biggerCh);
        }

        for (int i = 0, smallerCh = baseCh - 1;
                i < distance && smallerCh >= START_CH;
                ++i, --smallerCh) {
            neighbours.add((char) smallerCh);
        }

        return neighbours;
    }
}
