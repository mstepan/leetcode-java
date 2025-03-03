package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.SmallestSubstringWithIdenticalCharacters2.minLength;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SmallestSubstringWithIdenticalCharacters2Test {

    @Test
    void emptyStringCase() {
        assertEquals(0, minLength("", 2));
    }

    @Test
    void case1() {
        assertEquals(2, minLength("000001", 1));
    }

    @Test
    void case2() {
        assertEquals(1, minLength("0000", 2));
    }

    @Test
    void case3() {
        assertEquals(1, minLength("0101", 0));
    }

    @Test
    void case4() {
        assertEquals(2, minLength("00", 0));
    }

    @Test
    void case5() {
        assertEquals(2, minLength("0110", 1));
    }

    @Test
    void case6() {
        assertEquals(1, minLength("1011", 1));
    }

    @Test
    void case7() {
        assertEquals(1, minLength("0010", 1));
    }

    @Test
    void case8() {
        assertEquals(2, minLength("00011", 1));
    }
}
