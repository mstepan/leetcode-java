package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class LetterCasePermutationTest {

    @Test
    void letterCasePermutation1() {
        assertEquals(
                List.of("a1b2", "A1b2", "a1B2", "A1B2"),
                new LetterCasePermutation().letterCasePermutation("a1b2"));
    }

    @Test
    void letterCasePermutation2() {
        assertEquals(
                List.of("3z4", "3Z4"), new LetterCasePermutation().letterCasePermutation("3z4"));
    }
}
