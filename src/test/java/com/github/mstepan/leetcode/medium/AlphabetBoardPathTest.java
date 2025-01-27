package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.AlphabetBoardPath.alphabetBoardPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AlphabetBoardPathTest {

    @Test
    void case1() {
        assertEquals("DDR!UURRR!!DDD!", alphabetBoardPath("leet"));
    }

    @Test
    void case2() {
        assertEquals("RR!DDRR!UUL!R!", alphabetBoardPath("code"));
    }

    @Test
    void wordUsingLastCellAsStartAndEndChars() {
        assertEquals("DDDDD!UUUUURRR!LLLDDDDD!", alphabetBoardPath("zdz"));
    }
}
