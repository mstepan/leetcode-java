package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.SumOfKMirrorNumbers.kMirror;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SumOfKMirrorNumbersTest {

    @Test
    void case1() {
        assertEquals(25, kMirror(2, 5));
    }

    @Test
    void case2() {
        assertEquals(499, kMirror(3, 7));
    }

    @Test
    void case3() {
        assertEquals(20_379_000, kMirror(7, 17));
    }

    @Test
    void checkNumbersWithOverflow() {
        assertEquals(6_849_225_412L, kMirror(5, 25));
    }

    @Test
    void singleElement() {
        assertEquals(1, kMirror(2, 1));
        assertEquals(1, kMirror(5, 1));
        assertEquals(1, kMirror(9, 1));
    }
}
