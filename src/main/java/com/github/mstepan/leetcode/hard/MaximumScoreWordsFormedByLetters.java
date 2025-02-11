package com.github.mstepan.leetcode.hard;

import java.util.Objects;

/**
 * 1255. Maximum Score Words Formed by Letters
 *
 * <p>https://leetcode.com/problems/maximum-score-words-formed-by-letters/
 */
public class MaximumScoreWordsFormedByLetters {

    /**
     * N = words.length, K = average words[i].length
     *
     * <p>time: O(2^N * N*K)
     *
     * <p>space: O(1)
     */
    public static int maxScoreWords(String[] words, char[] letters, int[] scores) {
        Objects.requireNonNull(words, "null 'words' array");
        Objects.requireNonNull(letters, "null 'letters' array'");
        Objects.requireNonNull(scores, "null 'scores' array'");

        if (words.length > 14) {
            throw new IllegalArgumentException("'words.length' should be less or equal to 14");
        }

        int[] actualLettersCount = count(letters);

        int maxScore = 0;

        for (int i = 1; i < (1 << words.length); i++) {
            int curCost = calculateCostForSubset(words, i, actualLettersCount, scores);
            maxScore = Math.max(maxScore, curCost);
        }

        return maxScore;
    }

    private static final int A = 'a';
    private static final int Z = 'z';

    private static final int LETTERS_COUNT = Z - A + 1;

    private static int[] count(char[] letters) {
        int[] freq = new int[LETTERS_COUNT];

        for (char ch : letters) {
            assert ch >= A && ch <= Z;
            freq[ch - A] += 1;
        }

        return freq;
    }

    private static int calculateCostForSubset(
            String[] words, int subsetMask, int[] actualLettersCount, int[] scores) {
        int[] requiredLettersCount = new int[actualLettersCount.length];

        for (int curMask = subsetMask, idx = 0;
                curMask != 0 && idx < words.length;
                curMask >>>= 1, ++idx) {

            if ((curMask & 1) != 0) {
                accumulateLetters(words[idx], requiredLettersCount);
            }
        }

        if (isCompatible(requiredLettersCount, actualLettersCount)) {
            return calculateCost(requiredLettersCount, scores);
        }

        return -1;
    }

    private static int calculateCost(int[] requiredLettersCount, int[] scores) {
        int cost = 0;

        for (int i = 0; i < requiredLettersCount.length; i++) {
            assert requiredLettersCount[i] < 100;
            assert scores[i] < 100;

            cost = cost + requiredLettersCount[i] * scores[i];
        }

        return cost;
    }

    private static void accumulateLetters(String word, int[] requiredLettersCount) {
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            assert ch >= A && ch <= Z;
            requiredLettersCount[ch - A] += 1;
        }
    }

    private static boolean isCompatible(int[] requiredLettersCount, int[] actualLettersCount) {
        assert requiredLettersCount.length == actualLettersCount.length;

        for (int i = 0; i < requiredLettersCount.length; ++i) {
            if (requiredLettersCount[i] > actualLettersCount[i]) {
                return false;
            }
        }

        return true;
    }
}
