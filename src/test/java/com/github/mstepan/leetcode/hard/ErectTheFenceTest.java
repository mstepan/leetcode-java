package com.github.mstepan.leetcode.hard;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ErectTheFenceTest {

    @Test
    void outerTreesAllCollinearCase() {
        int[][] actualRes =
                ErectTheFence.outerTrees(new int[][] {{3, 3}, {5, 3}, {7, 3}, {10, 3}, {12, 3}});

        assertPointsExactlyInAnyOrder(
                actualRes, new int[][] {{3, 3}, {5, 3}, {7, 3}, {10, 3}, {12, 3}});
    }

    @Test
    void outerTreesCase1() {
        int[][] actualRes =
                ErectTheFence.outerTrees(
                        new int[][] {{1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}});

        assertPointsExactlyInAnyOrder(
                actualRes, new int[][] {{1, 1}, {2, 0}, {4, 2}, {3, 3}, {2, 4}});
    }

    @Test
    void outerTreesCase2() {
        int[][] actualRes = ErectTheFence.outerTrees(new int[][] {{1, 2}, {2, 2}, {4, 2}});

        assertPointsExactlyInAnyOrder(actualRes, new int[][] {{1, 2}, {2, 2}, {4, 2}});
    }

    @Test
    void outerTreesCase3() {
        int[][] actualRes =
                ErectTheFence.outerTrees(
                        new int[][] {
                            {3, 0}, {4, 0}, {5, 0}, {6, 1}, {7, 2}, {7, 3}, {7, 4}, {6, 5}, {5, 5},
                            {4, 5}, {3, 5}, {2, 5}, {1, 4}, {1, 3}, {1, 2}, {2, 1}, {4, 2}, {0, 3}
                        });

        assertPointsExactlyInAnyOrder(
                actualRes,
                new int[][] {
                    {4, 5}, {2, 5}, {6, 1}, {3, 5},
                    {2, 1}, {1, 4}, {1, 2}, {7, 4},
                    {7, 3}, {7, 2}, {3, 0}, {0, 3},
                    {5, 0}, {5, 5}, {4, 0}, {6, 5}
                });
    }

    @Test
    void outerTreesCase4() {
        int[][] actualRes =
                ErectTheFence.outerTrees(
                        new int[][] {{0, 2}, {1, 1}, {2, 2}, {2, 4}, {4, 2}, {3, 3}});

        assertPointsExactlyInAnyOrder(
                actualRes, new int[][] {{0, 2}, {4, 2}, {3, 3}, {1, 1}, {2, 4}});
    }

    private static void assertPointsExactlyInAnyOrder(int[][] actual, int[][] expected) {
        assertThat(actual.length).isEqualTo(expected.length);
        assertThat(toSortedPoints(actual)).containsExactlyElementsOf(toSortedPoints(expected));
    }

    private static List<String> toSortedPoints(int[][] points) {
        return Arrays.stream(points).map(point -> point[0] + "," + point[1]).sorted().toList();
    }
}
