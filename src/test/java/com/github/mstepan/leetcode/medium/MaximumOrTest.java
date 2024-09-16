package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumOrTest {

    @Test
    void maxOrCase1() {
        assertEquals(30, MaximumOr.maximumOr(new int[] {12, 9}, 1));
    }

    @Test
    void maxOrCase2() {
        assertEquals(35, MaximumOr.maximumOr(new int[] {8, 1, 2}, 2));
    }

    @Test
    void maxOrCase3() {
        assertEquals(
                96383,
                MaximumOr.maximumOr(
                        new int[] {
                            41, 79, 82, 27, 71, 62, 57, 67, 34, 8, 71, 2, 12, 93, 52, 91, 86, 81, 1,
                            79, 64, 43, 32, 94, 42, 91, 9, 25
                        },
                        10));
    }
}
