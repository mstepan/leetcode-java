package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.MaximumValueAfterInsertion.maxValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MaximumValueAfterInsertionTest {

    @Test
    void case1() {
        assertEquals("999", maxValue("99", 9));
    }

    @Test
    void case2() {
        assertEquals("-123", maxValue("-13", 2));
    }

    @Test
    void case3() {
        assertEquals("763", maxValue("73", 6));
    }

    @Test
    void case4() {
        assertEquals("-1323", maxValue("-132", 3));
    }

    @Test
    void case5() {
        assertEquals("43731", maxValue("4371", 3));
    }

    @Test
    void nullStringShouldThrowException() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> maxValue(null, 6));
        assertEquals("Null or empty 'base' string detected: 'null'", ex.getMessage());
    }

    @Test
    void emptyStringShouldThrowException() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> maxValue("", 6));
        assertEquals("Null or empty 'base' string detected: ''", ex.getMessage());
    }

    @Test
    void stringWithPlusFromBeginningShouldThrowException() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> maxValue("+13", 6));
        assertEquals("Not a digit at index 0 for string '+13'", ex.getMessage());
    }

    @Test
    void invalidDigitForInsertThrowException() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> maxValue("13", 11));
        assertEquals("Not a decimal digit: 11", ex.getMessage());
    }
}
