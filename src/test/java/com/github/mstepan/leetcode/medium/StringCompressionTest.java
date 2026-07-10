package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringCompressionTest {

    @Test
    void normalCases() {
        assertCompressed("aabbccc", "a2b2c3");

        assertCompressed("a", "a");

        assertCompressed("abbbbbbbbbbbb", "ab12");
    }

    private static void assertCompressed(String initialStr, String compressed) {
        final char[] data = initialStr.toCharArray();
        int newLength = StringCompression.compress(data);
        assertEquals(new String(data, 0, newLength), compressed);
    }
}
