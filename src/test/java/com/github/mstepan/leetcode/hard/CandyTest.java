package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.Candy.candy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CandyTest {

    @Test
    void candyCase1() {
        assertEquals(5, candy(new int[] {1, 0, 2}));
    }

    @Test
    void candyCase2() {
        assertEquals(4, candy(new int[] {1, 2, 2}));
    }

    @Test
    void candyNormalCase() {
        assertEquals(25, candy(new int[] {5, 7, 10, 10, 10, 20, 20, 20, 6, 3, 2, 2, 2, 1}));
    }
}
