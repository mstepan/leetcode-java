package com.github.mstepan.leetcode.hard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ErectTheFenceTest {

    @Test
    void outerTreesAllCollinearCase() {
        int[][] actualRes =
                ErectTheFence.outerTrees(new int[][] {{3, 3}, {5, 3}, {7, 3}, {10, 3}, {12, 3}});

        assertThat(actualRes)
                .containsExactlyInAnyOrder(
                        new int[] {3, 3},
                        new int[] {5, 3},
                        new int[] {7, 3},
                        new int[] {10, 3},
                        new int[] {12, 3});
    }

    @Test
    void outerTreesCase1() {
        int[][] actualRes =
                ErectTheFence.outerTrees(
                        new int[][] {{1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}});

        assertThat(actualRes)
                .containsExactlyInAnyOrder(
                        new int[] {1, 1},
                        new int[] {2, 0},
                        new int[] {4, 2},
                        new int[] {3, 3},
                        new int[] {2, 4});
    }

    @Test
    void outerTreesCase2() {
        int[][] actualRes = ErectTheFence.outerTrees(new int[][] {{1, 2}, {2, 2}, {4, 2}});

        assertThat(actualRes)
                .hasSize(3)
                .containsExactlyInAnyOrder(new int[] {1, 2}, new int[] {2, 2}, new int[] {4, 2});
    }

    @Test
    void outerTreesCase3() {
        int[][] actualRes =
                ErectTheFence.outerTrees(
                        new int[][] {
                            {3, 0}, {4, 0}, {5, 0}, {6, 1}, {7, 2}, {7, 3}, {7, 4}, {6, 5}, {5, 5},
                            {4, 5}, {3, 5}, {2, 5}, {1, 4}, {1, 3}, {1, 2}, {2, 1}, {4, 2}, {0, 3}
                        });

        assertThat(actualRes)
                .containsExactlyInAnyOrder(
                        new int[] {4, 5},
                        new int[] {2, 5},
                        new int[] {6, 1},
                        new int[] {3, 5},
                        new int[] {2, 1},
                        new int[] {1, 4},
                        new int[] {1, 2},
                        new int[] {7, 4},
                        new int[] {7, 3},
                        new int[] {7, 2},
                        new int[] {3, 0},
                        new int[] {0, 3},
                        new int[] {5, 0},
                        new int[] {5, 5},
                        new int[] {4, 0},
                        new int[] {6, 5});
    }

    @Test
    void outerTreesCase4() {
        int[][] actualRes =
                ErectTheFence.outerTrees(
                        new int[][] {{0, 2}, {1, 1}, {2, 2}, {2, 4}, {4, 2}, {3, 3}});

        assertThat(actualRes)
                .containsExactlyInAnyOrder(
                        new int[] {0, 2},
                        new int[] {4, 2},
                        new int[] {3, 3},
                        new int[] {1, 1},
                        new int[] {2, 4});
    }
}
