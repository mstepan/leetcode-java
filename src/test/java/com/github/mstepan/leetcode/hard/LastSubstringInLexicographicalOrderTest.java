package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.LastSubstringInLexicographicalOrder.lastSubstring;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LastSubstringInLexicographicalOrderTest {

    @Test
    void simpleCases() {
        assertEquals("bab", lastSubstring("abab"));
        assertEquals("tcode", lastSubstring("leetcode"));
    }

    @Test
    void moreComplexCase() {
        assertEquals("tthiscodete", lastSubstring("testthiscodete"));
    }

    @Test
    void sameCharStr() {
        assertEquals("aaaaaaaaaaaaaaaaaaaaaa", lastSubstring("aaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void emptyString() {
        assertEquals("", lastSubstring(""));
    }

    @Test
    void singleCharStr() {
        assertEquals("a", lastSubstring("a"));
    }
}
