package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.CountSubarraysWithFixedBounds.countSubarrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountSubarraysWithFixedBoundsTest {

    @Test
    void case1() {
        assertEquals(2, countSubarrays(new int[] {1, 3, 5, 2, 7, 5}, 1, 5));
    }

    @Test
    void case2() {
        assertEquals(10, countSubarrays(new int[] {1, 1, 1, 1}, 1, 1));
    }

    @Test
    void case3() {
        assertEquals(0, countSubarrays(new int[] {1, 2}, 2, 1));
    }

    @Test
    void case4() {
        assertEquals(
                81,
                countSubarrays(
                        new int[] {
                            35, 398, 945, 945, 820, 945, 35, 945, 171, 945, 35, 109, 790, 441, 552
                        },
                        35,
                        945));
    }

    @Test
    void case5() {
        assertEquals(10, countSubarrays(new int[] {35, 39, 94, 94, 82, 94, 35}, 35, 94));
    }
}
