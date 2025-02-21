package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.StrangePrinter.strangePrinter;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StrangePrinterTest {

    @Test
    void case1() {
        assertEquals(2, strangePrinter("aaabbb"));
    }

    @Test
    void case2() {
        assertEquals(2, strangePrinter("aba"));
    }

    @Test
    void case3() {
        assertEquals(4, strangePrinter("bacaca"));
    }

    @Test
    void case4() {
        assertEquals(1, strangePrinter("aaaaa"));
    }

    @Test
    void case5() {
        assertEquals(2, strangePrinter("aaaaaccc"));
    }

    @Test
    void case6() {
        assertEquals(6, strangePrinter("leetcode"));
    }

    @Test
    void case7() {
        assertEquals(4, strangePrinter("aabcad"));
    }

    @Test
    void case8() {
        assertEquals(3, strangePrinter("aaabaac"));
    }

    @Test
    void case9() {
        assertEquals(7, strangePrinter("helloworld"));
    }

    @Test
    void case10() {
        assertEquals(4, strangePrinter("tbgtgb"));
    }

    @Test
    void case11() {
        assertEquals(3, strangePrinter("ddcdadd"));
    }

    @Test
    void biggerCase1() {
        assertEquals(19, strangePrinter("baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa"));
    }

    @Test
    void biggerCase2() {
        assertEquals(15, strangePrinter("dddccbdbababaddcbcaabdbdddcccddbbaabddb"));
    }

    @Test
    void cornerCase1() {
        assertEquals(4, strangePrinter("dcdbcb"));
    }

    @Test
    void cornerCase2() {
        assertEquals(17, strangePrinter("ccdcadbddbaddcbccdcdabcbcddbccdcbad"));
    }
}
