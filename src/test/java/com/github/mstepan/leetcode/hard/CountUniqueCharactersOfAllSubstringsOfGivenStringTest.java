package com.github.mstepan.leetcode.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountUniqueCharactersOfAllSubstringsOfGivenStringTest {

    @Test
    void uniqueLetterString1() {
        assertEquals(
                10,
                new CountUniqueCharactersOfAllSubstringsOfGivenString().uniqueLetterString("ABC"));
    }

    @Test
    void uniqueLetterString2() {
        assertEquals(
                8,
                new CountUniqueCharactersOfAllSubstringsOfGivenString().uniqueLetterString("ABA"));
    }

    @Test
    void uniqueLetterString3() {
        assertEquals(
                92,
                new CountUniqueCharactersOfAllSubstringsOfGivenString()
                        .uniqueLetterString("LEETCODE"));
    }
}
