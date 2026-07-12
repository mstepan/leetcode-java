package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.IntegerWithMultiSumOIfTwoCubes.findGoodIntegers;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class IntegerWithMultiSumOIfTwoCubesTest {

    @Test
    void case1() {
        assertEquals(List.of(1729, 4104), findGoodIntegers(4104));
    }

    @Test
    void case2() {
        assertEquals(List.of(), findGoodIntegers(578));
    }

    @Test
    void caseWithSortingRequired() {
        assertEquals(List.of(1_729, 4_104, 13_832, 20_683, 32_832), findGoodIntegers(33_281));
    }

    @Test
    void upperBoundary() {
        long start = System.nanoTime();
        List<Integer> res = findGoodIntegers(1_000_000_000);
        long end = System.nanoTime();

        long timeInMs = (end - start) / 1_000_000;

        assertTrue(timeInMs < 500);

        assertNotNull(res);
        assertEquals(1554, res.size());
    }
}
