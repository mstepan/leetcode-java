package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.ConstructLexicographicallyLargestValidSequence.constructDistancedSequence;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ConstructLexicographicallyLargestValidSequenceTest {

    @Test
    void shouldConstructCorrectSequenceForNEquals3() {
        int[] expected = {3, 1, 2, 3, 2};
        int[] actual = constructDistancedSequence(3);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldConstructCorrectSequenceForNEquals5() {
        int[] expected = {5, 3, 1, 4, 3, 5, 2, 4, 2};
        int[] actual = constructDistancedSequence(5);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldConstructCorrectSequenceForNEquals10() {
        int[] expected = {10, 8, 6, 9, 3, 1, 7, 3, 6, 8, 10, 5, 9, 7, 4, 2, 5, 2, 4};
        int[] actual = constructDistancedSequence(10);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldConstructCorrectSequenceForNEquals14() {
        int[] expected = {14, 12, 13, 9, 7, 11, 4, 1, 10, 8, 4, 7, 9, 12, 14, 13, 11, 8, 10, 6, 3, 5, 2, 3, 2, 6, 5};
        int[] actual = constructDistancedSequence(14);
        assertArrayEquals(expected, actual);
    }
}
