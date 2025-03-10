package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.ParsingBooleanExpression.parseBoolExpr;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParsingBooleanExpressionTest {

    @Test
    void case1() {
        assertFalse(parseBoolExpr("&(|(f))"));
    }

    @Test
    void case2() {
        assertTrue(parseBoolExpr("|(f,f,f,t)"));
    }

    @Test
    void case3() {
        assertTrue(parseBoolExpr("!(&(f,t))"));
    }
}
