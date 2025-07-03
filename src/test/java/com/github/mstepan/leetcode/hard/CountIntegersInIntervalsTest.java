package com.github.mstepan.leetcode.hard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** Unit tests for CountIntervals class. */
public class CountIntegersInIntervalsTest {

    @Test
    public void testEmptyIntervals() {
        CountIntervals intervals = new CountIntervals();
        assertEquals(0, intervals.count(), "Count should be 0 for empty intervals");
    }

    @Test
    public void testSingleInterval() {
        CountIntervals intervals = new CountIntervals();
        intervals.add(1, 5);
        assertEquals(5, intervals.count(), "Count should be 5 for interval [1,5]");
    }

    @Test
    public void testNonOverlappingIntervals() {
        CountIntervals intervals = new CountIntervals();
        intervals.add(1, 5);
        intervals.add(10, 15);
        assertEquals(
                11,
                intervals.count(),
                "Count should be 11 for non-overlapping intervals [1,5] and [10,15]");
    }

    @Test
    public void testOverlappingIntervals() {
        CountIntervals intervals = new CountIntervals();
        intervals.add(1, 5);
        intervals.add(3, 7);
        assertEquals(
                7,
                intervals.count(),
                "Count should be 7 for overlapping intervals [1,5] and [3,7]");
    }

    @Test
    public void testFullyCoveredInterval() {
        CountIntervals intervals = new CountIntervals();
        intervals.add(1, 10);
        intervals.add(2, 5);
        assertEquals(
                10, intervals.count(), "Count should be 10 as [2,5] is fully covered by [1,10]");
    }

    @Test
    public void testMultipleIntervalsWithOverlap() {
        CountIntervals intervals = new CountIntervals();
        intervals.add(1, 5);
        intervals.add(5, 10);
        intervals.add(8, 12);
        intervals.add(15, 20);

        assertEquals(
                18,
                intervals.count(),
                "Count should be 18 for intervals [1,5], [5,10], [8,12], [15,20]");
    }

    @Test
    public void testLargeIntervals() {
        CountIntervals intervals = new CountIntervals();
        intervals.add(1, 1000000000);
        assertEquals(
                1000000000, intervals.count(), "Count should handle large intervals correctly");
    }

    @Test
    public void testInvalidInterval() {
        CountIntervals intervals = new CountIntervals();
        assertThrows(
                IllegalArgumentException.class,
                () -> intervals.add(5, 1),
                "Should throw exception for invalid interval where from > to");
    }
}
