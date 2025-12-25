package com.github.mstepan.leetcode.hard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimilarStringGroupsTest {

    @Test
    void case1() {
        assertEquals(
                2,
                SimilarStringGroups.numSimilarGroups(
                        new String[] {"tars", "rats", "arts", "star"}));
    }

    @Test
    void case2() {
        assertEquals(1, SimilarStringGroups.numSimilarGroups(new String[] {"omv", "ovm"}));
    }

    @Test
    void case3() {
        assertEquals(
                2,
                SimilarStringGroups.numSimilarGroups(
                        new String[] {
                            "ajdidocuyh",
                            "djdyaohuic",
                            "ddjyhuicoa",
                            "djdhaoyuic",
                            "ddjoiuycha",
                            "ddhoiuycja",
                            "ajdydocuih",
                            "ddjiouycha",
                            "ajdydohuic",
                            "ddjyouicha"
                        }));
    }

    @Test
    void caseWithSimilarWords() {
        assertEquals(1, SimilarStringGroups.numSimilarGroups(new String[] {"abc", "abc"}));
    }

    @Test
    void emptyWords() {
        assertEquals(0, SimilarStringGroups.numSimilarGroups(new String[] {}));
    }
}
