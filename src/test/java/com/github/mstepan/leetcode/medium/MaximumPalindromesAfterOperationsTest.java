package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumPalindromesAfterOperationsTest {

    @Test
    void simpleCase1() {
        assertEquals(
                3,
                new MaximumPalindromesAfterOperations()
                        .maxPalindromesAfterOperations(new String[] {"abbb", "ba", "aa"}));
    }

    @Test
    void simpleCase2() {
        assertEquals(
                2,
                new MaximumPalindromesAfterOperations()
                        .maxPalindromesAfterOperations(new String[] {"abc", "ab"}));
    }

    @Test
    void simpleCase3() {
        assertEquals(
                1,
                new MaximumPalindromesAfterOperations()
                        .maxPalindromesAfterOperations(new String[] {"cd", "ef", "a"}));
    }

    @Test
    void simpleCase4() {
        assertEquals(
                2,
                new MaximumPalindromesAfterOperations()
                        .maxPalindromesAfterOperations(new String[] {"aab", "bca"}));
    }

    @Test
    void simpleCase5() {
        assertEquals(
                1,
                new MaximumPalindromesAfterOperations()
                        .maxPalindromesAfterOperations(new String[] {"aagha", "bc"}));
    }

    @Test
    void simpleCase6() {
        assertEquals(
                3,
                new MaximumPalindromesAfterOperations()
                        .maxPalindromesAfterOperations(new String[] {"aa", "bg", "g"}));
    }

    @Test
    void simpleCase7() {
        assertEquals(
                3,
                new MaximumPalindromesAfterOperations()
                        .maxPalindromesAfterOperations(new String[] {"a", "a", "caa"}));
    }

    @Test
    void simpleCase8() {
        assertEquals(
                3,
                new MaximumPalindromesAfterOperations()
                        .maxPalindromesAfterOperations(new String[] {"baa", "aa", "aca"}));
    }
}
