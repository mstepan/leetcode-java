package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringCompressionTest {

    @Test
    void normalCases() {
        assertCompressed("aabbccc", "a2b2c3");
        assertCompressed("abbbbbbbbbbbb", "ab12");
    }

    @Test
    void oneCharString() {
        assertCompressed("a", "a");
    }

    @Test
    void emptyString() {
        assertCompressed("", "");
    }

    @Test
    void distinctCharactersRemainUnchanged() {
        assertCompressed("abcdef", "abcdef");
    }

    @Test
    void alternatingAndRepeatedGroups() {
        assertCompressed("abbccccdde", "ab2c4d2e");
        assertCompressed("aaabccdddd", "a3bc2d4");
    }

    @Test
    void multiDigitCounts() {
        assertCompressed("aaaaaaaaaa", "a10");
        assertCompressed("b".repeat(100), "b100");
    }

    private static void assertCompressed(String initialStr, String compressed) {
        final char[] data = initialStr.toCharArray();
        int newLength = StringCompression.compress(data);
        assertEquals(new String(data, 0, newLength), compressed);
    }
}
