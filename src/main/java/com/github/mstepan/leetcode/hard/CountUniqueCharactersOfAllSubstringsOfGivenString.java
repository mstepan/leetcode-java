package com.github.mstepan.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 828. Count Unique Characters of All Substrings of a Given String
 *
 * <p>https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/description/
 */
public class CountUniqueCharactersOfAllSubstringsOfGivenString {

    /**
     * time: O(N)
     *
     * <p>space: O(N)
     */
    public int uniqueLetterString(String str) {
        Objects.requireNonNull(str);

        if (str.length() < 2) {
            return str.length();
        }

        Map<Character, List<Integer>> positions = new HashMap<>();
        for (int i = 0; i < str.length(); ++i) {
            final int chPos = i;
            char ch = str.charAt(i);
            positions.compute(
                    ch,
                    (key, prevPositions) -> {
                        if (prevPositions == null) {
                            List<Integer> newPositions = new ArrayList<>();
                            newPositions.add(chPos);
                            return newPositions;
                        }

                        prevPositions.add(chPos);
                        return prevPositions;
                    });
        }

        int totalCnt = 0;

        for (Map.Entry<Character, List<Integer>> entry : positions.entrySet()) {

            //            char ch = entry.getKey();
            List<Integer> charPositions = entry.getValue();

            for (int i = 0; i < charPositions.size(); ++i) {

                int left = i > 0 ? (charPositions.get(i - 1) + 1) : 0;

                int idx = charPositions.get(i);

                int right =
                        i < charPositions.size() - 1
                                ? (charPositions.get(i + 1) - 1)
                                : str.length() - 1;

                int curCnt = (idx - left + 1) * (right - idx + 1);

                totalCnt += curCnt;

                //                System.out.printf("%c: +%d%n", ch, curCnt);
            }
        }

        return totalCnt;
    }
}
