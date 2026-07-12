package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.IntegerWithMultiSumOIfTwoCubes.findGoodNumbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IntegerWithMultiSumOIfTwoCubesTest {

    @Test
    void case1() {
        assertArrayEquals(new int[] {1729, 4104}, findGoodNumbers(4104));
    }

    @Test
    void case2() {
        assertArrayEquals(new int[] {}, findGoodNumbers(578));
    }

    @Test
    void upperBoundary() {
        long start = System.nanoTime();
        int[] res = findGoodNumbers(1_000_000_000);
        long end = System.nanoTime();

        long timeInMs = (end - start) / 1_000_000;

        assertTrue(timeInMs < 500);

        assertNotNull(res);
        assertEquals(1562, res.length);
    }
}
