package com.github.mstepan.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * 3043. Find the Length of the Longest Common Prefix
 *
 * <p>https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/description/
 */
public class FindTheLengthOfTheLongestCommonPrefix {

    /**
     * time: O(N*lgN)
     *
     * <p>space: O(N)
     *
     * <p>N = arr1.length + arr2.length
     */
    public static int longestCommonPrefix(int[] arr1, int[] arr2) {
        Objects.requireNonNull(arr1);
        Objects.requireNonNull(arr2);

        Element[] combinedArr = new Element[arr1.length + arr2.length];

        for (int i = 0; i < arr1.length; ++i) {
            combinedArr[i] = new Element(String.valueOf(arr1[i]), 0);
        }

        for (int i = 0; i < arr2.length; ++i) {
            combinedArr[arr1.length + i] = new Element(String.valueOf(arr2[i]), 1);
        }

        Arrays.sort(combinedArr, Element.VALUE_ASC);

        int longestCommonPrefixLength = 0;

        for (int i = 1; i < combinedArr.length; ++i) {
            Element first = combinedArr[i - 1];
            Element second = combinedArr[i];

            if (first.idx() != second.idx()) {
                longestCommonPrefixLength =
                        Math.max(
                                longestCommonPrefixLength,
                                commonPrefixLength(first.value(), second.value()));
            }
        }

        return longestCommonPrefixLength;
    }

    @SuppressWarnings("all")
    private static int commonPrefixLength(String first, String second) {
        assert first != null;
        assert second != null;

        if (first == second) {
            return first.length();
        }

        int prefixLength = 0;

        for (int i = 0; i < Math.min(first.length(), second.length()); ++i) {
            if (first.charAt(i) != second.charAt(i)) {
                break;
            }

            ++prefixLength;
        }

        return prefixLength;
    }

    record Element(String value, int idx) {

        static final Comparator<Element> VALUE_ASC = Comparator.comparing(Element::value);
    }
}
