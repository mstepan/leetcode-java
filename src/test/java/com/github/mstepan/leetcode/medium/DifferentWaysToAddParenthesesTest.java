package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

public class DifferentWaysToAddParenthesesTest {

    @Test
    void diffWaysToCompute1() {
        List<Integer> result = new DifferentWaysToAddParentheses().diffWaysToCompute("2*3-4*5");
        assertEquals(List.of(-34, -10, -14, -10, 10), result);
    }

    @Test
    void diffWaysToCompute2() {
        List<Integer> result = new DifferentWaysToAddParentheses().diffWaysToCompute("2*3-4+5");
        assertEquals(List.of(-12, 8, -3, 3, 7), result);
    }

    @Test
    void diffWaysToComputeIncorrectOperationShouldFail() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new DifferentWaysToAddParentheses().diffWaysToCompute("2*3-4/5");
                });
    }
}
