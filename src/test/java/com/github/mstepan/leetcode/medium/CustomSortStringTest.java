package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.CustomSortString.customSortString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomSortStringTest {

    @Test
    void customSortStringCase1() {
        assertEquals("cbad", customSortString("cba", "abcd"));
    }

    @Test
    void customSortStringCase2() {
        assertEquals("bcad", customSortString("bcafg", "abcd"));
    }
}
