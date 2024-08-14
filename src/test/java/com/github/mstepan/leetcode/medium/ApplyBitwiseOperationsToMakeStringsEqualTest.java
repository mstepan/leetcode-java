package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ApplyBitwiseOperationsToMakeStringsEqualTest {

    @Test
    void makeStringsEqual() {
        assertTrue(new ApplyBitwiseOperationsToMakeStringsEqual().makeStringsEqual("0101", "0101"));
        assertTrue(new ApplyBitwiseOperationsToMakeStringsEqual().makeStringsEqual("1010", "0110"));
        assertTrue(
                new ApplyBitwiseOperationsToMakeStringsEqual().makeStringsEqual("00100", "00010"));

        assertFalse(new ApplyBitwiseOperationsToMakeStringsEqual().makeStringsEqual("11", "00"));
        assertFalse(
                new ApplyBitwiseOperationsToMakeStringsEqual().makeStringsEqual("00100", "00000"));
    }

    @Test
    void makeStringsEqualWithDifferentStringsLengthShouldFail() {
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        new ApplyBitwiseOperationsToMakeStringsEqual()
                                .makeStringsEqual("00", "0101"));
    }

    @Test
    void makeStringsEqualWithNotBinaryCharsShouldFail() {
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        new ApplyBitwiseOperationsToMakeStringsEqual()
                                .makeStringsEqual("00a1", "0101"));
    }

    @Test
    void makeStringsEqualWithNullStrShouldFail() {
        assertThrows(
                NullPointerException.class,
                () ->
                        new ApplyBitwiseOperationsToMakeStringsEqual()
                                .makeStringsEqual(null, "0101"));
    }

    @Test
    void makeStringsEqualWithNullTargetShouldFail() {
        assertThrows(
                NullPointerException.class,
                () ->
                        new ApplyBitwiseOperationsToMakeStringsEqual()
                                .makeStringsEqual("0101", null));
    }
}
