package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindTheLengthOfTheLongestCommonPrefixTest {

    @Test
    void longestCommonPrefixCase1() {
        assertEquals(
                0,
                FindTheLengthOfTheLongestCommonPrefix.longestCommonPrefix(
                        new int[] {1, 2, 3}, new int[] {4, 4, 4, 4}));
    }

    @Test
    void longestCommonPrefixCase2() {
        assertEquals(
                3,
                FindTheLengthOfTheLongestCommonPrefix.longestCommonPrefix(
                        new int[] {1, 10, 100}, new int[] {1000}));
    }
}
