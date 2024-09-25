package com.github.mstepan.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 2452. Words Within Two Edits of Dictionary
 *
 * <p>https://leetcode.com/problems/words-within-two-edits-of-dictionary/
 */
public class WordsWithinTwoEditsOfDictionary {

    /**
     * time: O(N^3)
     *
     * <p>space: O(N)
     */
    public static List<String> twoEditWords(String[] queries, String[] dictionary) {
        Objects.requireNonNull(queries);
        Objects.requireNonNull(dictionary);

        List<String> res = new ArrayList<>();

        for (String singleQuery : queries) {
            for (String word : dictionary) {
                if (hammingDistance(singleQuery, word) <= 2) {
                    res.add(singleQuery);
                    break;
                }
            }
        }

        return res;
    }

    private static int hammingDistance(String w1, String w2) {
        assert w1 != null;
        assert w2 != null;
        assert w1.length() == w2.length();

        int distance = 0;
        for (int i = 0; i < w1.length(); ++i) {
            char ch1 = w1.charAt(i);
            char ch2 = w2.charAt(i);

            distance += (ch1 == ch2 ? 0 : 1);
        }

        return distance;
    }
}
