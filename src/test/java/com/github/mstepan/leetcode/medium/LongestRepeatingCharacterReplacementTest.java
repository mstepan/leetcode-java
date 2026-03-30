package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.LongestRepeatingCharacterReplacement.characterReplacement;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestRepeatingCharacterReplacementTest {

    @Test
    void characterReplacementNormalCases() {
        assertEquals(4, characterReplacement("ABAB", 2));
        assertEquals(4, characterReplacement("AABABBA", 1));
    }

    @Test
    void characterReplacementNoSubstitution() {
        assertEquals(1, characterReplacement("ABAB", 0));
        assertEquals(2, characterReplacement("AABABBA", 0));
    }

    @Test
    void characterReplacementBiggerString() {
        assertEquals(
                11,
                characterReplacement(
                        "EOEMQLLQTRQDDCOERARHGAAARRBKCCMFTDAQOLOKARBIJBISTGNKBQGKKTALSQNFSABASNOPBMMGDIOETPTDICRBOMBAAHINTFLH",
                        7));
    }
}
