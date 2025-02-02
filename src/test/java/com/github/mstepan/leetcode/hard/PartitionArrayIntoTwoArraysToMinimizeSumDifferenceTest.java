package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.PartitionArrayIntoTwoArraysToMinimizeSumDifference.minimumDifference;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class PartitionArrayIntoTwoArraysToMinimizeSumDifferenceTest {

    @Test
    void case1() {
        assertEquals(2, minimumDifference(new int[] {3, 9, 7, 3}));
    }

    @Test
    void case2() {
        assertEquals(72, minimumDifference(new int[] {-36, 36}));
    }

    @Test
    void case3() {
        assertEquals(0, minimumDifference(new int[] {2, -1, 0, 4, -2, -9}));
    }

    @Test
    void caseWithBiggerArray() {
        assertEquals(
                1,
                minimumDifference(
                        new int[] {
                            7772197, 4460211, -7641449, -8856364, 546755, -3673029, 527497,
                            -9392076, 3130315, -5309187, -4781283, 5919119, 3093450, 1132720,
                            6380128, -3954678, -1651499, -7944388, -3056827, 1610628, 7711173,
                            6595873, 302974, 7656726, -2572679, 0, 2121026, -5743797, -8897395,
                            -9699694
                        }));
    }

    @Test
    void combinationsIteratorNormalCase() {
        int[] arr = {1, 2, 3, 4};

        Iterator<int[]> it =
                new PartitionArrayIntoTwoArraysToMinimizeSumDifference.CombinationsIterator(arr, 2);

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

        Iterator<int[]> it =
                new PartitionArrayIntoTwoArraysToMinimizeSumDifference.CombinationsIterator(arr, 4);

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {1, 2, 3, 4}, it.next());

        assertFalse(it.hasNext());
    }

    @Test
    void combinationsIteratorOneElementCombination() {
        int[] arr = {1, 2, 3, 4};

        Iterator<int[]> it =
                new PartitionArrayIntoTwoArraysToMinimizeSumDifference.CombinationsIterator(arr, 1);

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

        Iterator<int[]> it =
                new PartitionArrayIntoTwoArraysToMinimizeSumDifference.CombinationsIterator(arr, 1);

        assertTrue(it.hasNext());
        assertArrayEquals(new int[] {133}, it.next());

        assertFalse(it.hasNext());
    }
}
