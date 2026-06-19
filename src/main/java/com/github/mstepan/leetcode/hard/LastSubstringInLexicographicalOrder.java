package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 1163. Last Substring in Lexicographical Order
 *
 * <p>https://leetcode.com/problems/last-substring-in-lexicographical-order/description/
 */
public class LastSubstringInLexicographicalOrder {

    /**
     * time: O(N)
     *
     * <p>space: O(N)
     */
    public static String lastSubstring(String str) {
        Objects.requireNonNull(str, "null 'str' passed as a parameter");

        if (str.length() < 2) {
            return str;
        }

        Set<Integer> maxCharSuffixesIndexes = findMaxCharOffsets(str);

        List<Suffix> curLevel = Suffix.suffixesFromStartIndexes(maxCharSuffixesIndexes, str);

        while (curLevel.size() > 1) {
            final Set<Integer> skipNext = new HashSet<>();

            final List<Suffix> nextLevel = new ArrayList<>();
            char maxCh = Character.MIN_VALUE;

            for (Suffix singleSuffix : curLevel) {
                if (skipNext.contains(singleSuffix.from)) {
                    continue;
                }

                final char curCh = singleSuffix.curChar();
                final int curIdx = singleSuffix.idx;

                singleSuffix.moveNextChar();

                if (curCh >= maxCh) {

                    if (curCh > maxCh) {
                        nextLevel.clear();
                        maxCh = curCh;
                    }

                    nextLevel.add(singleSuffix);

                    // check if we hit another suffix that need to be skipped from processing next
                    if (maxCharSuffixesIndexes.contains(curIdx)) {
                        skipNext.add(curIdx);
                    }
                }
            }

            curLevel = nextLevel;
        }

        return curLevel.getFirst().suffixStr();
    }

    private static Set<Integer> findMaxCharOffsets(String str) {
        assert str != null;
        Set<Integer> maxCharSuffixesIndexes = new HashSet<>(str.length());
        char maxCh = Character.MIN_VALUE;

        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);

            if (ch == maxCh) {
                maxCharSuffixesIndexes.add(i);
            } else if (ch > maxCh) {
                maxCharSuffixesIndexes.clear();
                maxCharSuffixesIndexes.add(i);

                maxCh = ch;
            }
        }

        return maxCharSuffixesIndexes;
    }

    private static class Suffix {

        final int from;
        final String baseStr;
        int idx;

        public Suffix(int from, String baseStr) {
            this.from = from;
            this.baseStr = baseStr;
            this.idx = from;
        }

        public static List<Suffix> suffixesFromStartIndexes(
                Set<Integer> maxCharSuffixesIndexes, String baseStr) {

            List<Suffix> result = new ArrayList<>(maxCharSuffixesIndexes.size());

            for (int singleOffset : maxCharSuffixesIndexes) {
                result.add(new Suffix(singleOffset, baseStr));
            }

            return result;
        }

        public String suffixStr() {
            return baseStr.substring(from);
        }

        public char curChar() {
            if (idx >= baseStr.length()) {
                return Character.MIN_VALUE;
            }

            return baseStr.charAt(idx);
        }

        @Override
        public String toString() {
            return suffixStr();
        }

        public void moveNextChar() {
            ++idx;
        }
    }
}
