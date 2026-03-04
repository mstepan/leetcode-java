package com.github.mstepan.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class BookmapTest {

    @Test
    void normalCase1() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "ABBABAAB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(1, 4, 4),
                        });

        assertArrayEquals(new int[] {3}, answers);
    }

    @Test
    void normalCase2() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "ABBABAAB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(2, 6, 1),
                        });

        assertArrayEquals(new int[] {3}, answers);
    }

    @Test
    void normalCase3() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "ABBABAAB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(3, 7, 5),
                        });

        assertArrayEquals(new int[] {-1}, answers);
    }

    @Test
    void fullRangeMirrorMatching() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "ABBABAAB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(1, 8, 1), new Bookmap.Query(1, 8, 8),
                        });

        assertArrayEquals(new int[] {2, 7}, answers);
    }

    @Test
    void subRangeMatchingUsesRelativePositions() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "ABBABAAB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(4, 7, 2), new Bookmap.Query(4, 7, 4),
                        });

        assertArrayEquals(new int[] {1, -1}, answers);
    }

    @Test
    void singleCharacterSubstringHasNoMatch() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "ABBABAAB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(6, 6, 1),
                        });

        assertArrayEquals(new int[] {-1}, answers);
    }

    @Test
    void allCharactersSameAlwaysNoCounterpart() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "AAAA",
                        new Bookmap.Query[] {
                            new Bookmap.Query(1, 4, 1), new Bookmap.Query(1, 4, 4),
                        });

        assertArrayEquals(new int[] {-1, -1}, answers);
    }

    @Test
    void multipleQueriesInSingleCall() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "BAAABB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(2, 5, 1),
                            new Bookmap.Query(2, 5, 4),
                            new Bookmap.Query(2, 5, 3),
                        });

        assertArrayEquals(new int[] {4, 1, -1}, answers);
    }
}
