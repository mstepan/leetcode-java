package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FractionAdditionAndSubtractionTest {

    @Test
    void evaluateExpression() {
        assertEquals("0/1", new FractionAdditionAndSubtraction().fractionAddition("-1/2+1/2"));

        assertEquals("1/3", new FractionAdditionAndSubtraction().fractionAddition("-1/2+1/2+1/3"));

        assertEquals("-1/6", new FractionAdditionAndSubtraction().fractionAddition("1/3-1/2"));
    }
}
