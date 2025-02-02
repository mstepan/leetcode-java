package com.github.mstepan.leetcode.other;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class CombinationsIteratorTest {

    @Test
    void combinationsIteratorNormalCase() {
        int[] arr = {1, 2, 3, 4};

        Iterator<int[]> it = new CombinationsIterator(arr, 2);

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {1, 2}, it.next());

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {1, 3}, it.next());

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {1, 4}, it.next());

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {2, 3}, it.next());

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {2, 4}, it.next());

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {3, 4}, it.next());

        assertFalse(it.hasNext());
    }

    @Test
    void combinationsIteratorSingleCombination() {
        int[] arr = {1, 2, 3, 4};

        Iterator<int[]> it = new CombinationsIterator(arr, 4);

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {1, 2, 3, 4}, it.next());

        assertFalse(it.hasNext());
    }

    @Test
    void combinationsIteratorOneElementCombination() {
        int[] arr = {1, 2, 3, 4};

        Iterator<int[]> it = new CombinationsIterator(arr, 1);

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {1}, it.next());

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {2}, it.next());

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {3}, it.next());

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {4}, it.next());

        assertFalse(it.hasNext());
    }

    @Test
    void combinationsIteratorOneElementArray() {
        int[] arr = {133};

        Iterator<int[]> it = new CombinationsIterator(arr, 1);

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {133}, it.next());

        assertFalse(it.hasNext());
    }
}
