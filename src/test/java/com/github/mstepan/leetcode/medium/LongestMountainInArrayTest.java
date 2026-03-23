package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.LongestMountainInArray.longestMountain;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestMountainInArrayTest {

    @Test
    public void normalWithSolution() {
        assertEquals(5, longestMountain(new int[] {2, 1, 4, 7, 3, 2, 5}));
    }

    @Test
    public void normalCaseNoSolution() {
        assertEquals(0, longestMountain(new int[] {2, 2, 2}));
    }

    @Test
    public void leetCodeExampleWithPlateauInsideReturnsZero() {
        assertEquals(5, longestMountain(new int[] {2, 1, 4, 7, 3, 2, 5, 5}));
    }

    @Test
    public void wholeArrayIsMountain() {
        assertEquals(6, longestMountain(new int[] {1, 3, 5, 4, 2, 1}));
    }

    @Test
    public void mountainAtArrayBeginning() {
        assertEquals(5, longestMountain(new int[] {0, 2, 3, 2, 1, 2, 3}));
    }

    @Test
    public void mountainAtArrayEnd() {
        assertEquals(5, longestMountain(new int[] {3, 2, 1, 2, 4, 3, 1}));
    }

    @Test
    public void twoMountainsLongestIsSelected() {
        assertEquals(6, longestMountain(new int[] {1, 2, 1, 2, 3, 4, 2, 1}));
    }

    @Test
    public void equalValuesAtPeakBreakMountain() {
        assertEquals(3, longestMountain(new int[] {1, 2, 2, 1, 2, 1}));
    }

    @Test
    public void multipleEqualValuesInDescendingPart() {
        assertEquals(3, longestMountain(new int[] {1, 3, 2, 2, 1}));
    }

    @Test
    public void minimalValidMountainLengthThree() {
        assertEquals(3, longestMountain(new int[] {1, 2, 1}));
    }

    @Test
    public void twoElementsCannotFormMountain() {
        assertEquals(0, longestMountain(new int[] {1, 0}));
    }

    @Test
    public void alternatingUpsAndDownsKeepsLongestMountain() {
        assertEquals(3, longestMountain(new int[] {1, 3, 2, 4, 3, 5, 4}));
    }

    @Test
    public void increasingOnlyArrayReturnsZero() {
        assertEquals(0, longestMountain(new int[] {1, 4, 8, 10, 12}));
    }

    @Test
    public void decreasingOnlyArrayReturnsZero() {
        assertEquals(0, longestMountain(new int[] {12, 10, 8, 4, 2}));
    }

    @Test
    public void emptyArrayReturnsZero() {
        assertEquals(0, longestMountain(new int[] {}));
    }

    @Test
    public void arrayWithOIneElementReturnZero() {
        assertEquals(0, longestMountain(new int[] {5}));
    }

    @Test
    public void repeatedPrefixNoMountain() {
        assertEquals(0, longestMountain(new int[] {3, 3, 1}));
    }
}
