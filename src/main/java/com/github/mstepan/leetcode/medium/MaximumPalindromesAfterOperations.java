package com.github.mstepan.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 3035. Maximum Palindromes After Operations
 *
 * <p>https://leetcode.com/problems/maximum-palindromes-after-operations/description/
 */
public class MaximumPalindromesAfterOperations {

    /**
     * N = words.length K = 26
     *
     * <p>time: O(N*lgN)
     *
     * <p>space: O(K)
     */
    public int maxPalindromesAfterOperations(String[] words) {
        int[] cnt = new int['z' - 'a' + 1];

        int totalLength = 0;

        for (String singleWord : words) {
            for (int i = 0; i < singleWord.length(); ++i) {
                char ch = singleWord.charAt(i);
                assert ch >= 'a' && ch <= 'z';
                cnt[ch - 'a'] += 1;
                ++totalLength;
            }
        }

        // VERY important to sort by length in ASC order, otherwise greedy
        // approach won't work for all the test cases.
        Arrays.sort(words, LENGTH_ASC_CMP);

        int palindromesCnt = 0;

        int oddCharsCnt = countOdd(cnt);
        int eventCharsCount = totalLength - oddCharsCnt;

        for (String singleWord : words) {
            int curLength = singleWord.length();

            // event str length
            if (isEven(curLength)) {
                if (eventCharsCount >= curLength) {
                    ++palindromesCnt;
                    eventCharsCount -= curLength;
                }
            }
            // odd str length
            else {
                if (oddCharsCnt > 0 && eventCharsCount >= curLength - 1) {
                    ++palindromesCnt;
                    oddCharsCnt -= 1;
                    eventCharsCount -= (curLength - 1);
                } else if (oddCharsCnt == 0 && eventCharsCount >= curLength) {
                    ++palindromesCnt;
                    eventCharsCount -= curLength;
                    oddCharsCnt += 1;
                }
            }
        }

        return palindromesCnt;
    }

    private int countOdd(int[] cnt) {
        int oddCnt = 0;
        for (int val : cnt) {
            oddCnt += (isOdd(val) ? 1 : 0);
        }
        return oddCnt;
    }

    static boolean isOdd(int val) {
        return (val & 1) != 0;
    }

    static boolean isEven(int val) {
        return !isOdd(val);
    }

    private static final Comparator<String> LENGTH_ASC_CMP =
            Comparator.comparingInt(String::length);
}
