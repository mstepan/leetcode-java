package com.github.mstepan.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class BookmapTest {

    @Test
    void searchNormalCases() {
        int[] answers =
                Bookmap.findAnswerForQueries(
                        "ABBABAAB",
                        new Bookmap.Query[] {
                            new Bookmap.Query(1, 4, 4),
                            new Bookmap.Query(2, 6, 1),
                            new Bookmap.Query(3, 7, 5)
                        });

        assertArrayEquals(new int[] {3, 3, -1}, answers);
    }
}
