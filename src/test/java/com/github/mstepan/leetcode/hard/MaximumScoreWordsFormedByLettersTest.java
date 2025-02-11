package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.MaximumScoreWordsFormedByLetters.maxScoreWords;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumScoreWordsFormedByLettersTest {

    @Test
    void case1() {
        assertEquals(
                23,
                maxScoreWords(
                        new String[] {"dog", "cat", "dad", "good"},
                        new char[] {'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'},
                        new int[] {
                            1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0
                        }));
    }

    @Test
    void case2() {
        assertEquals(
                27,
                maxScoreWords(
                        new String[] {"xxxz", "ax", "bx", "cx"},
                        new char[] {'z', 'a', 'b', 'c', 'x', 'x', 'x'},
                        new int[] {
                            4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5,
                            0, 10
                        }));
    }

    @Test
    void case3() {
        assertEquals(
                0,
                maxScoreWords(
                        new String[] {"leetcode"},
                        new char[] {'l', 'e', 't', 'c', 'o', 'd'},
                        new int[] {
                            0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0,
                            0, 0
                        }));
    }
}
