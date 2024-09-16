package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SumOfNumbersWithUnitsDigitKTest {

    @Test
    void minimumNumbersSolutionExistsCase1() {
        assertEquals(2, SumOfNumbersWithUnitsDigitK.minimumNumbers(58, 9));

        assertEquals(0, SumOfNumbersWithUnitsDigitK.minimumNumbers(0, 7));
    }

    @Test
    void minimumNumbersSolutionExistsCase2() {
        assertEquals(1, SumOfNumbersWithUnitsDigitK.minimumNumbers(40, 0));
    }

    @Test
    void minimumNumbersNoSolution() {
        assertEquals(-1, SumOfNumbersWithUnitsDigitK.minimumNumbers(37, 2));
    }

    @Test
    void minimumNumbersNoSolutionDeepRecursion() {
        assertEquals(-1, SumOfNumbersWithUnitsDigitK.minimumNumbers(4, 0));
    }
}
