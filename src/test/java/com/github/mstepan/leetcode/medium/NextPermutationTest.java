package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.LargestTimeForGivenDigits.nextPermutation;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;

class NextPermutationTest {

    @Test
    void case1() {
        int[] arr = new int[] {1, 2, 3};
        nextPermutation(arr);
        assertThat(arr).contains(1, 3, 2);
    }

    @Test
    void case2() {
        int[] arr = new int[] {3, 2, 1};
        nextPermutation(arr);
        assertThat(arr).contains(1, 2, 3);
    }

    @Test
    void case3() {
        int[] arr = new int[] {1, 1, 5};
        nextPermutation(arr);
        assertThat(arr).contains(1, 5, 1);
    }
}
