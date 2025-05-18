package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.IntegerToEnglishWords.numberToWords;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IntegerToEnglishWordsTest {

    @Test
    void complexCase1() {
        assertEquals(
                "Two Billion One Hundred Twenty Three Million Five Hundred Seven Thousand One Hundred One",
                numberToWords(2_123_507_101));
    }

    @Test
    void oneMillionCase() {
        assertEquals("One Million", numberToWords(1000000));
    }

    @Test
    void oneMillionAndTenCase() {
        assertEquals("One Million Ten", numberToWords(1000010));
    }

    @Test
    void complexCase2() {
        assertEquals("One Hundred One", numberToWords(101));
    }

    @Test
    void singleDigitCases() {
        assertEquals("Zero", numberToWords(0));
        assertEquals("Five", numberToWords(5));
        assertEquals("Eight", numberToWords(8));
    }

    @Test
    void twoDigitsCases() {
        assertEquals("Ten", numberToWords(10));
        assertEquals("Fifteen", numberToWords(15));
        assertEquals("Twenty", numberToWords(20));
        assertEquals("Twenty Five", numberToWords(25));
        assertEquals("Ninety Nine", numberToWords(99));
    }
}
