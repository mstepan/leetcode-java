package com.github.mstepan.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BookmapTest {

    @Test
    void exampleCase1() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "ABBABAAB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(1, 4, 4),
                        });

        assertArrayEquals(new int[] {3}, answers);
    }

    @Test
    void exampleCase2() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "ABBABAAB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(2, 6, 1),
                        });

        assertArrayEquals(new int[] {3}, answers);
    }

    @Test
    void exampleCase3() {
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

    @Test
    void alternatingStringCoversAllKPositions() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "ABABAB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(1, 6, 1),
                            new Bookmap.Query(1, 6, 2),
                            new Bookmap.Query(1, 6, 3),
                            new Bookmap.Query(1, 6, 4),
                            new Bookmap.Query(1, 6, 5),
                            new Bookmap.Query(1, 6, 6),
                        });

        assertArrayEquals(new int[] {2, 1, 4, 3, 6, 5}, answers);
    }

    @Test
    void queryWithKAtSubrangeBoundaries() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "BAABABB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(2, 7, 1), new Bookmap.Query(2, 7, 6),
                        });

        assertArrayEquals(new int[] {3, 4}, answers);
    }

    @Test
    void returnsMinusOneWhenExpectedOppositeCharacterCountNotPresent() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "AABAAA",
                        new Bookmap.Query[] {
                            new Bookmap.Query(1, 6, 6), new Bookmap.Query(2, 6, 4),
                        });

        assertArrayEquals(new int[] {-1, -1}, answers);
    }

    @Test
    void emptyQueriesReturnsEmptyAnswers() {
        int[] answers = Bookmap.findAnswerForQueries("ABBABAAB", new Bookmap.Query[0]);

        assertArrayEquals(new int[0], answers);
    }

    @Test
    void throwsForNullString() {
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        Bookmap.findAnswerForQueries(
                                null, new Bookmap.Query[] {new Bookmap.Query(1, 1, 1)}));
    }

    @Test
    void throwsForNullQueriesArray() {
        assertThrows(
                IllegalArgumentException.class, () -> Bookmap.findAnswerForQueries("AB", null));
    }

    @Test
    void throwsForInvalidCharactersInString() {
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        Bookmap.findAnswerForQueries(
                                "ABC",
                                new Bookmap.Query[] {
                                    new Bookmap.Query(1, 3, 1),
                                }));
    }

    @Test
    void throwsWhenQueryIsOutsideStringRange() {
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        Bookmap.findAnswerForQueries(
                                "ABAB",
                                new Bookmap.Query[] {
                                    new Bookmap.Query(1, 5, 1),
                                }));
    }

    @Test
    void queryConstructorValidationRejectsInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> new Bookmap.Query(0, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Bookmap.Query(2, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Bookmap.Query(1, 3, 4));
    }
}
