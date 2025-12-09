package com.github.mstepan.leetcode.medium;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Subsets2Test {

    @Test
    void case1() {
        assertThat(Subsets2.subsetsWithDupParallel(new int[] {1, 2, 2}))
                .containsExactlyInAnyOrder(
                        List.of(),
                        List.of(1),
                        List.of(2),
                        List.of(1, 2),
                        List.of(2, 2),
                        List.of(1, 2, 2));
    }

    @Test
    void case2() {
        assertThat(Subsets2.subsetsWithDupParallel(new int[] {0}))
                .containsExactlyInAnyOrder(List.of(), List.of(0));
    }

    @Test
    void case3() {
        assertThat(Subsets2.subsetsWithDupParallel(new int[] {4, 4, 4, 1, 4}))
                .containsExactlyInAnyOrder(
                        List.of(),
                        List.of(1),
                        List.of(1, 4),
                        List.of(1, 4, 4),
                        List.of(1, 4, 4, 4),
                        List.of(1, 4, 4, 4, 4),
                        List.of(4),
                        List.of(4, 4),
                        List.of(4, 4, 4),
                        List.of(4, 4, 4, 4));
    }

    @Test
    void caseWithEmptyArray() {
        assertThat(Subsets2.subsetsWithDupParallel(new int[] {}))
                .containsExactlyInAnyOrder(List.of());
    }
}
