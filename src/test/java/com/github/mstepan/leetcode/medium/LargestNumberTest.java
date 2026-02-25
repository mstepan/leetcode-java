package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LargestNumberTest {

    @Test
    public void normalCases() {
        assertEquals("210", LargestNumber.largestNumber(new int[] {10, 2}));
        assertEquals("9534330", LargestNumber.largestNumber(new int[] {3, 30, 34, 5, 9}));
        assertEquals("1113111311", LargestNumber.largestNumber(new int[] {111311, 1113}));
    }

    @Test
    public void trickyComparatorCases() {
        // numbers with common prefixes and different lengths
        assertEquals("12121", LargestNumber.largestNumber(new int[] {12, 121}));

        // ensure concatenation ordering works beyond simple pairs
        assertEquals("6054854654", LargestNumber.largestNumber(new int[] {54, 546, 548, 60}));

        // verify stability when multiple permutations produce same prefix
        assertEquals(
                "999999999999999999999999997",
                LargestNumber.largestNumber(new int[] {999999997, 999999999, 999999999}));

        // long array mixing small and large values
        assertEquals(
                "953433233232130",
                LargestNumber.largestNumber(new int[] {3, 30, 34, 5, 9, 32, 321, 323}));
    }

    @Test
    public void onlyZerosInArray() {
        assertEquals("0", LargestNumber.largestNumber(new int[] {0}));

        assertEquals("0", LargestNumber.largestNumber(new int[] {0, 0}));

        assertEquals("0", LargestNumber.largestNumber(new int[] {0, 0, 0, 0, 0}));
    }

    @Test
    public void oneSingleValue() {
        assertEquals("5", LargestNumber.largestNumber(new int[] {5}));

        assertEquals("123", LargestNumber.largestNumber(new int[] {123}));

        assertEquals("1", LargestNumber.largestNumber(new int[] {1}));
    }

    @Test
    public void zerosWithNonZeroValues() {
        assertEquals("100000", LargestNumber.largestNumber(new int[] {0, 0, 1000}));

        assertEquals("100000000", LargestNumber.largestNumber(new int[] {0, 0, 0, 0, 10000}));

        assertEquals("22100", LargestNumber.largestNumber(new int[] {0, 0, 21, 2}));
    }

    @Test
    public void emptyArray() {
        assertThrows(
                IllegalArgumentException.class, () -> LargestNumber.largestNumber(new int[] {}));
    }

    @Test
    public void nullArray() {
        assertThrows(IllegalArgumentException.class, () -> LargestNumber.largestNumber(null));
    }
}
